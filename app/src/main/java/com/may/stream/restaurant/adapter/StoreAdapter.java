package com.may.stream.restaurant.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.may.stream.restaurant.R;
import com.may.stream.restaurant.model.TblStore;
import com.may.stream.restaurant.view.activity.AddItemActivity;

import java.util.List;

/**
 * Created by may on 1/10/2018.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder>{
    private static Context mcontext;
    private List<TblStore> arrayList;

    public StoreAdapter(Context context,List<TblStore> _arrayList){
        this.arrayList = _arrayList;
        this.mcontext = context;
    }

    @Override
    public StoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_store, parent, false);
        return new StoreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoreAdapter.ViewHolder holder, final int i) {
        holder.title.setText(arrayList.get(i).getStore_name());
        if(i==0)
            holder.thumbnail.setImageResource(R.drawable.ic_view_list_black_24dp);
        else if(i==1)
            holder.thumbnail.setImageResource(R.drawable.ic_subject_black_24dp);
        else if(i==2)
            holder.thumbnail.setImageResource(R.drawable.ic_local_offer_black_24dp);
        else if(i==3)
            holder.thumbnail.setImageResource(R.drawable.ic_restaurant_menu_black_24dp);
        else if(i==4)
            holder.thumbnail.setImageResource(R.drawable.ic_face_black_24dp);

        holder.linear_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, AddItemActivity.class);
                intent.putExtra("list",arrayList.get(i));
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private LinearLayout linear_list;
        private ImageView thumbnail;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            linear_list = (LinearLayout) v.findViewById(R.id.linear_list);
            thumbnail = (ImageView)v.findViewById(R.id.thumbnail);
        }
    }
}
