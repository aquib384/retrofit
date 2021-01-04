package com.firstapp.retrodemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.retrodemo.model.FilterName;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<com.firstapp.retrodemo.ExampleAdapter.ExampleViewHolder> {

    private ArrayList<FilterName> mExampleList;
    Context context;



    public ExampleAdapter(MainActivity context, ArrayList<FilterName> exampleList) {
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
        FilterName currentItem = mExampleList.get(position);
        String id=currentItem.getId();
        String filter_group_id=currentItem.getFilterGroupId();
        String name=currentItem.getName();
        String image=currentItem.getImage();
        String imageBus=currentItem.getBusImage();
        holder.ids.setText(id);
        holder.filter_group_ids.setText((CharSequence) filter_group_id);
        holder.names.setText((CharSequence) name);
        Picasso.get().load(image).fit().into(holder.images);
        Picasso.get().load(imageBus).fit().into(holder.bus_images);
//


        // Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
        // Picasso.get().load(imageUrl).fit().into(holder.mImageView);
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView ids;
        public TextView filter_group_ids;
        public TextView names;
        public ImageView images;
        public ImageView bus_images;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            ids=itemView.findViewById(R.id.id);
            filter_group_ids = itemView.findViewById(R.id.filtergrpid);
            names = itemView.findViewById(R.id.name);
            images = itemView.findViewById(R.id.image);
            bus_images=itemView.findViewById(R.id.imageBus);


        }
    }
}
