package com.prateek.androidpr.ui;

import android.animation.RectEvaluator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prateek.androidpr.R;
import com.prateek.androidpr.databinding.SingleItemBinding;
import com.prateek.androidpr.models.ListItem;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private List<ListItem> allItems;
    private List<String> selectedListOne = new ArrayList<>();
    private List<String> selectedListTwo = new ArrayList<>();
    private final ItemsSelectedListenerOne listener;

    public MyListAdapter(List<String> allItems, ItemsSelectedListenerOne listener) {
//        this.allItems = allItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem item = allItems.get(position);
        holder.binding.checkBox.setText(item.text);
        holder.binding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (item.listNo == 1)
                        selectedListOne.add(item.text);
                    else
                        selectedListTwo.add(item.text);
                } else {
                    if (item.listNo == 1)
                        selectedListOne.add(item.text);
                    else
                        selectedListTwo.add(item.text);
                }
//                listener.SelectedItemsChanged(selectedListOne, selectedListTwo);
            }
        });
    }

    @Override
    public int getItemCount() {
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
