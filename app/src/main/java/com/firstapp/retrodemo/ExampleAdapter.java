package com.firstapp.retrodemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.retrodemo.model.RegionDatum;

import java.util.ArrayList;
import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<com.firstapp.retrodemo.ExampleAdapter.ExampleViewHolder> {

    private ArrayList<RegionDatum> mExampleList;
    private List<RegionDatum> exampleListFull;
    Context context;



    public ExampleAdapter(Context context, ArrayList<RegionDatum> exampleList) {
        this.mExampleList=exampleList;
        this.context = (Context) context;
        exampleListFull = new ArrayList<>(exampleList);
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
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<RegionDatum> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (RegionDatum item : exampleListFull) {
                    if (item.getRegion().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mExampleList.clear();
            mExampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    }


