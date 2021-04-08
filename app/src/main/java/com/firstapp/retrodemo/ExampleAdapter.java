package com.firstapp.retrodemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.retrodemo.model.FilterName;
import com.firstapp.retrodemo.model.RegionDatum;
import com.firstapp.retrodemo.model.State;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<com.firstapp.retrodemo.ExampleAdapter.ExampleViewHolder> {

    private ArrayList<RegionDatum> mExampleList;
    Context context;



    public ExampleAdapter(Context context, ArrayList<RegionDatum> exampleList) {
        this.mExampleList=exampleList;
        this.context = (Context) context;
    }
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        return new ExampleViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        RegionDatum currentItem = mExampleList.get(position);
        String region=currentItem.getRegion();
        String totalInfected= String.valueOf(currentItem.getTotalInfected());
        String newInfected= String.valueOf(currentItem.getNewInfected());

        holder.region.setText("State: "+region);
        holder.totalInfected.setText("Total infected: "+totalInfected);
        holder.newInfected.setText("New infected: "+newInfected);

        // Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
        // Picasso.get().load(imageUrl).fit().into(holder.mImageView);
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView region;
        public TextView totalInfected;
        public TextView newInfected;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            region=itemView.findViewById(R.id.region);
            totalInfected = itemView.findViewById(R.id.totalInfected);
            newInfected = itemView.findViewById(R.id.newInfected);


        }

    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mExampleList = mExampleList;
                } else {
                    List<RegionDatum> filteredList = new ArrayList<>();
                    for (RegionDatum row : mExampleList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getRegion().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    mExampleList = (ArrayList<RegionDatum>) filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mExampleList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mExampleList = (ArrayList<RegionDatum>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(RegionDatum regionDatum);
    }

}
