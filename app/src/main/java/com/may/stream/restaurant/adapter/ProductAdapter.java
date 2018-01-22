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
import com.may.stream.restaurant.model.TblProducts;
import com.may.stream.restaurant.view.activity.AddItemActivity;
import com.may.stream.restaurant.view.fragment.AddItemFragmentFirst;

import java.util.List;

/**
 * Created by may on 1/19/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private Context mContext;
    private List<TblProducts> arrayList;
    private AddItemFragmentFirst fragmentFirst;
    private AddItemActivity mView;
    public ProductAdapter(Context context, List<TblProducts> _arrayList){
        this.arrayList = _arrayList;
        this.mContext = context;
        fragmentFirst = new AddItemFragmentFirst();
        mView = new AddItemActivity();
    }
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_add_item, parent, false);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int i) {
        holder.title.setText(arrayList.get(i).getProduct_name());
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
