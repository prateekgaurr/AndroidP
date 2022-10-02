package com.prateek.android_p;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import com.prateek.android_p.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainActivity_B extends AppCompatActivity implements SelectionChangedListener {
    private ActivityMainBinding binding;
    private HashSet<String> list1;
    private HashSet<String> list2;
    private ListAdapterOne adapter1;
    private ListAdapterTwo adapter2;
    private List<String> selected1;
    private List<String> selected2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initEverything();

        setListeners();

    }


    private void initEverything() {
        list1 = new HashSet<>();
        list2 = new HashSet<>();

        binding.listOneRecycler.setHasFixedSize(true);
        binding.listTwoRecycler.setHasFixedSize(true);

        binding.listOneRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.listTwoRecycler.setLayoutManager(new LinearLayoutManager(this));

        refresh();
    }



    @Override
    public void onTableOneSelectionsChanged(List<String> tableOneList) {
        selected1 = tableOneList;
    }

    @Override
    public void onTableTwoSelectionsChanged(List<String> tableTwoList) {
        selected2 = tableTwoList;
    }

    private void setListeners() {
        binding.addButton.setOnClickListener(view->{
            if(!TextUtils.isEmpty(binding.editText.getText().toString().toString().trim())){
                list2.add(binding.editText.getText().toString().trim());
                binding.editText.setText("");
                refresh();
            }
            else
                Toast.makeText(this, "Please enter some text", Toast.LENGTH_SHORT).show();

        });

        binding.removeButton.setOnClickListener(view -> {
            list1.remove(binding.editText.getText().toString().trim());
            list2.remove(binding.editText.getText().toString().trim());
            binding.editText.setText("");
            refresh();
        });

        binding.copyFromListOneToListTwo.setOnClickListener(view->{
            if(selected1 != null){
                list2.addAll(selected1);
                adapter1.selected.removeAll(selected1);
                refresh();
            }
            else
                Toast.makeText(this, "Please select some items in list 2", Toast.LENGTH_SHORT).show();
        });

        binding.copyFromListTwoToListOne.setOnClickListener(view->{
            if(selected2 != null){
                list1.addAll(selected2);
                adapter2.selected.removeAll(selected2);
                refresh();
            }
            else
                Toast.makeText(this, "Please select some items in list 1", Toast.LENGTH_SHORT).show();
        });

        binding.moveFromListOneToListTwo.setOnClickListener(view->{
            if(selected1!=null){
                list2.addAll(selected1);
                selected1.forEach(list1::remove);
                adapter1.selected.removeAll(selected1);
               refresh();
            }
            else
                Toast.makeText(this, "Please select some items in list 1", Toast.LENGTH_SHORT).show();
        });

        binding.moveFromListTwoToListOne.setOnClickListener(view->{
            if(selected2!=null){
                list1.addAll(selected2);
                selected2.forEach(list2::remove);
                adapter2.selected.removeAll(selected2);
                refresh();
            }
            else
                Toast.makeText(this, "Please select some items in list 2", Toast.LENGTH_SHORT).show();
        });

        binding.swapItems.setOnClickListener(view->{
            if(selected2!=null && selected1!=null){
                list1.addAll(selected2);
                selected1.forEach(list2::remove);
                list2.addAll(selected1);
                selected2.forEach(list1::remove);
                adapter1.selected.removeAll(selected1);
                adapter2.selected.removeAll(selected2);
                refresh();
            }
            else
                Toast.makeText(this, "Please select some items in each list", Toast.LENGTH_SHORT).show();
        });
    }

    private void refresh() {
        binding.listOneRecycler.setAdapter(new ListAdapterOne(list1, this));
        binding.listTwoRecycler.setAdapter(new ListAdapterTwo(list2, this));
    }
}