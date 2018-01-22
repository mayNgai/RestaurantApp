package com.may.stream.restaurant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.may.stream.restaurant.R;
import com.may.stream.restaurant.view.fragment.AddItemFragmentFirst;
import com.may.stream.restaurant.model.TblAddItems;
import com.may.stream.restaurant.view.activity.AddItemActivity;

import java.util.List;

/**
 * Created by may on 1/10/2018.
 */

public class AddItemAdapter extends RecyclerView.Adapter<AddItemAdapter.ViewHolder>{
    private Context mContext;
    private List<TblAddItems> arrayList;
    private AddItemFragmentFirst fragmentFirst;
    private AddItemActivity mView;
    public AddItemAdapter(Context context, List<TblAddItems> _arrayList){
        this.arrayList = _arrayList;
        this.mContext = context;
        fragmentFirst = new AddItemFragmentFirst();
        mView = new AddItemActivity();
    }
    @Override
    public AddItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_add_item, parent, false);
        return new AddItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AddItemAdapter.ViewHolder holder, int i) {
        holder.title.setText(arrayList.get(i).getName());
//        if(arrayList.get(i).getValue()!= null && arrayList.get(i).getValue().length()>0){
//            holder.value.setVisibility(View.VISIBLE);
//            holder.value.setText(arrayList.get(i).getValue());
//        }else
//            holder.value.setVisibility(View.GONE);
        String url2 = "";//GlobalVar.url_up_pic + arrayList.get(i).getName();
//        Picasso.with(mContext)
//                .load(url2)
//                .error(R.drawable.no_images)
//                .resize(100, 100)
//                .centerCrop()
//                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title,value;
        private LinearLayout linear_list;
        private ImageView thumbnail;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            value = (TextView)v.findViewById(R.id.value);
            linear_list = (LinearLayout) v.findViewById(R.id.linear_list);
            thumbnail = (ImageView)v.findViewById(R.id.thumbnail);
        }
    }
}
