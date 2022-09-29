package com.prateek.androidp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prateek.androidp.R;
import com.prateek.androidp.databinding.SingleItemBinding;
import com.prateek.androidp.models.ListItemTableOne;
import com.prateek.androidp.usecases.SelectionChangedListener;

import java.util.ArrayList;
import java.util.List;

public class ListAdapterOne extends RecyclerView.Adapter<ListAdapterOne.ViewHolder> {
    private final List<ListItemTableOne> list;
    private final List<ListItemTableOne> selected;
    private final SelectionChangedListener listener;

    public ListAdapterOne(List<ListItemTableOne> list, SelectionChangedListener listener) {
        this.list = list;
        this.listener = listener;
        selected = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItemTableOne item = list.get(position);
        holder.binding.checkBox.setText(item.text);
        holder.binding.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked)
                selected.add(item);
            else
                selected.remove(item);
            listener.onTableOneSelectionsChanged(selected);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        SingleItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=SingleItemBinding.bind(itemView);
        }
    }

}
