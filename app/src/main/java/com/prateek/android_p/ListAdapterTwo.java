package com.prateek.android_p;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prateek.android_p.databinding.SingleItemBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ListAdapterTwo extends RecyclerView.Adapter<ListAdapterTwo.ViewHolder> {
    private final HashSet<String> list;
    protected final List<String> selected;
    private final SelectionChangedListener listener;

    public ListAdapterTwo(HashSet<String> list, SelectionChangedListener listener) {
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
        int index = (position > list.size()) ? position-1 : position;
        String item = (String) list.toArray()[index];
        holder.binding.checkBox.setText(item);
        holder.binding.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked)
                selected.add(item);
            else
                selected.remove(item);
            listener.onTableTwoSelectionsChanged(selected);
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
