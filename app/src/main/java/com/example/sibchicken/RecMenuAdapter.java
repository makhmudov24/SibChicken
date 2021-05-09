package com.example.sibchicken;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sibchicken.tables.Menu1Week;

import java.util.List;

public class RecMenuAdapter extends RecyclerView.Adapter<RecMenuAdapter.ViewHolder> {

    private final List<Menu1Week> mValues;

    private final int TYPE_ITEM1 = 0;
    private final int TYPE_ITEM2 = 1;
    private final int TYPE_ITEM3 = 2;

    public RecMenuAdapter(List<Menu1Week> list) {
        Log.i("STAAAAAAART", " RecMenuAdapter");

        this.mValues = list;
//        Log.i("STAAAAAAART", String.valueOf(mValues.size()));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case TYPE_ITEM1:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.menu_body, parent, false);
                return new ViewHolder(view);
            case TYPE_ITEM2:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.menu_head, parent, false);
                return new ViewHolder(view);
            case TYPE_ITEM3:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.menu_foot, parent, false);
                return new ViewHolder(view);
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int type = getItemViewType(position);

        switch (type) {
            case TYPE_ITEM1:
            case TYPE_ITEM3:
                holder.name.setText(mValues.get(position).getName());
                holder.weight.setText(mValues.get(position).getWeight());
                holder.price.setText(mValues.get(position).getPrice());
                break;
            case TYPE_ITEM2:
                holder.category.setText(mValues.get(position).getFoodCategory());
                holder.name.setText(mValues.get(position).getName());
                holder.weight.setText(mValues.get(position).getWeight());
                holder.price.setText(mValues.get(position).getPrice());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {

        String categories = mValues.get(position).getFoodCategory();

        for (int i = 0; i < MainActivity.getCategories().size(); i++) {
            if (MainActivity.getCategories().get(i).getNextCategories() == position) {
                return TYPE_ITEM2;
            }
        }

        if ((position + 1 == getItemCount()) || (!categories.equals(mValues.get(position + 1).getFoodCategory()))) {
            return TYPE_ITEM3;
        }

        // По default'y возвращаем основной view.
        return TYPE_ITEM1;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View mView;
        public TextView name;
        public TextView weight;
        public TextView price;
        public TextView category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            weight = mView.findViewById(R.id.weight);
            price = mView.findViewById(R.id.price);
            category = mView.findViewById(R.id.category);
            name = mView.findViewById(R.id.name);

        }
    }
}
