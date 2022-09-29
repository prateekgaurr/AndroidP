package com.prateek.androidpr.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prateek.androidpr.R;
import com.prateek.androidpr.databinding.SingleItemBinding;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterListOne extends RecyclerView.Adapter<MyAdapterListOne.ViewHolder> {
    private List<String> allItems;
    private List<String> selectedList;
    private final ItemsSelectedListenerOne listener;


    public MyAdapterListOne(List<String> allItems, ItemsSelectedListenerOne listener) {
        this.allItems = allItems;
        this.listener = listener;
        this.selectedList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.checkBox.setText(allItems.get(position));
        holder.binding.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedList.add(allItems.get(position));
            } else {
               selectedList.remove(allItems.get(position));
            }
            listener.SelectedItemsChangedOne(selectedList);
        });
    }

    @Override
    public int getItemCount() {
        if(allItems == null) return 0;
        return allItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        SingleItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SingleItemBinding.bind(itemView);
        }
    }
}
