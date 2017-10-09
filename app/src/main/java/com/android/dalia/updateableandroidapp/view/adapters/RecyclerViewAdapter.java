package com.android.dalia.updateableandroidapp.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.dalia.updateableandroidapp.R;
import com.android.dalia.updateableandroidapp.model.dto.ItemModel;

import java.util.List;

/**
 * Created by Dalia on 9/28/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<ItemModel> itemModelList;
    private View.OnLongClickListener longClickListener;

    public RecyclerViewAdapter(List<ItemModel> itemModelList, View.OnLongClickListener longClickListener) {
        this.itemModelList = itemModelList;
        this.longClickListener = longClickListener;
    }

    public RecyclerViewAdapter(List<ItemModel> itemModelList) {
        this.itemModelList = itemModelList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_borrowed, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ItemModel itemModel = itemModelList.get(position);

        holder.itemTextView.setText(itemModel.getItemName());
        holder.nameTextView.setText(itemModel.getPersonName());

        holder.dateTextView.setText(itemModel.getBorrowDate().toLocaleString().substring(0, 12));

        holder.itemView.setTag(itemModel);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return itemModelList.size();
    }

    public void addItems(List<ItemModel> itemModelList){
        this.itemModelList = itemModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView itemTextView;
        private TextView nameTextView;
        private TextView dateTextView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            itemTextView = (TextView) itemView.findViewById(R.id.itemTextView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            dateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
        }
    }
}
