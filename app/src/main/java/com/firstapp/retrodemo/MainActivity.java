package com.firstapp.retrodemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.firstapp.retrodemo.model.RegionDatum;
import com.firstapp.retrodemo.model.State;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.cert.CertificateException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String KEY_TIME = "time";
    public static final String FIRST_TIME = "firsttime";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    androidx.appcompat.widget.SearchView searchView;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<RegionDatum> mExampleList;
    DataBaseHelper db;
    ArrayList<RegionDatum> exampleList;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mExampleList = new ArrayList<RegionDatum>();
        pref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = pref.edit();
        exampleList = new ArrayList<>();
        whiteNotificationBar(mRecyclerView);
        db = new DataBaseHelper(this);

        checkSession();


    }

    private void getTimeDifference() {
        Date date=new Date(pref.getString(KEY_TIME,"jj"));

        Date currentTime = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime);
        boolean result= date.after(currentTime);
        if (result==false){
            editor.clear();
            getData();
        }
        else{
            Timer timer = new Timer ();
            TimerTask hourlyTask = new TimerTask () {
                @Override
                public void run () {
                    // your code here...
                }
            };

// schedule the task to run starting now and then every hour...
            timer.schedule (hourlyTask, 0l, 1000*60*60);


        }


        }





    private boolean isFirstSession() {
        return pref.getBoolean(FIRST_TIME,false);
    }

    private void checkSession() {
        if (isFirstSession()){
            getLocalResponse();

        }

        else{
            getSession();
            getData();
        }

    }




    private void getSession() {
        Date currentTime = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime);
        calendar.add(Calendar.HOUR, 2);
        String val = calendar.getTime().toString();
        editor.putBoolean(FIRST_TIME,true);
        editor.putString(KEY_TIME, val);
        editor.commit();
    }

    private void getLocalResponse() {

        exampleList= db.getData();
        mExampleAdapter = new ExampleAdapter(MainActivity.this, exampleList);
        mRecyclerView.setAdapter(mExampleAdapter);
        mExampleAdapter.notifyDataSetChanged();
        Toast.makeText(this,"From local database",Toast.LENGTH_SHORT).show();
        getTimeDifference();

    }

    private void getData() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.apify.com/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getUnsafeOkHttpClient().build())
                .build();
        CovidApi covidApi = retrofit.create(CovidApi.class);


        Call<State> call = covidApi.getUser();
        call.enqueue(new Callback<State>() {
            @Override
            public void onResponse(Call<State> call, Response<State> response) {
                State user = response.body();
                String region;
                int totalInfected;
                int newInfected;
                ProgressDialog pd=new ProgressDialog(MainActivity.this);
                pd.setMessage("Fetching from Api...");
                pd.show();


                if (response.isSuccessful()) {
                    pd.dismiss();
                    Toast.makeText(MainActivity.this, "From Api", Toast.LENGTH_SHORT).show();

                    for (int i = 0; i < response.body().getRegionData().size(); i++) {
                        Log.d("Aquibtest", String.valueOf(user.getRegionData().get(i).getNewInfected()));

                        region = String.valueOf(user.getRegionData().get(i).getRegion());
                        totalInfected = Integer.parseInt(String.valueOf(user.getRegionData().get(i).getTotalInfected()));
                        newInfected = Integer.parseInt(String.valueOf(user.getRegionData().get(i).getNewInfected()));



                        mExampleList.add(new RegionDatum(region,totalInfected,newInfected));
                        db.addData(region,totalInfected,newInfected);
                    }


                    mExampleAdapter = new ExampleAdapter(MainActivity.this, mExampleList);
                    mRecyclerView.setAdapter(mExampleAdapter);
                    mExampleAdapter.notifyDataSetChanged();

                }


            }


            @Override
            public void onFailure(Call<State> call, Throwable t) {
                System.out.print(t.getMessage());

            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getSession();
        }
    }




    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (androidx.appcompat.widget.SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
       searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               // filter recycler view when query submitted
               mExampleAdapter.getFilter().filter(query);
               return false;
           }

           @Override
           public boolean onQueryTextChange(String query) {

               mExampleAdapter.getFilter().filter(query);
               return false;
           }

       });
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }





}
