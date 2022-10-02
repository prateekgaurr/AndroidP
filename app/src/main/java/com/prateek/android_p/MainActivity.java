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

public class MainActivity extends AppCompatActivity implements SelectionChangedListener {
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

        adapter1 = new ListAdapterOne(list1, this);
        adapter2 = new ListAdapterTwo(list2, this);

        binding.listOneRecycler.setHasFixedSize(true);
        binding.listTwoRecycler.setHasFixedSize(true);

        binding.listOneRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.listTwoRecycler.setLayoutManager(new LinearLayoutManager(this));

        binding.listOneRecycler.setAdapter(adapter1);
        binding.listTwoRecycler.setAdapter(adapter2);
    }

    @Override
    public void onTableOneSelectionsChanged(List<String> tableOneList) {
        selected1 = tableOneList;
    }

    @Override
    public void onTableTwoSelectionsChanged(List<String> tableTwoList) {
        selected2 = tableTwoList;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setListeners() {
        binding.addButton.setOnClickListener(view->{
            if(!TextUtils.isEmpty(binding.editText.getText().toString().toString().trim())){
                list2.add(binding.editText.getText().toString().trim());
                binding.editText.setText("");
                adapter2.notifyDataSetChanged();
            }
            else
                Toast.makeText(this, "Please enter some text", Toast.LENGTH_SHORT).show();

        });

        binding.removeButton.setOnClickListener(view -> {
            list1.remove(binding.editText.getText().toString().trim());
            list2.remove(binding.editText.getText().toString().trim());
            binding.editText.setText("");
            adapter1.notifyDataSetChanged();
            adapter2.notifyDataSetChanged();
        });

        binding.copyFromListOneToListTwo.setOnClickListener(view->{
            if(selected1 != null){
                list2.addAll(selected1);
                adapter1.selected.removeAll(selected1);
                adapter2.notifyDataSetChanged();
            }
            else
                Toast.makeText(this, "Please select some items in list 2", Toast.LENGTH_SHORT).show();
        });

        binding.copyFromListTwoToListOne.setOnClickListener(view->{
            if(selected2 != null){
                list1.addAll(selected2);
                adapter2.selected.removeAll(selected2);
                adapter1.notifyDataSetChanged();

            }
            else
                Toast.makeText(this, "Please select some items in list 1", Toast.LENGTH_SHORT).show();
        });

        binding.moveFromListOneToListTwo.setOnClickListener(view->{
            if(selected1!=null){
                list2.addAll(selected1);
                selected1.forEach(list1::remove);
                adapter1.selected.removeAll(selected1);
                adapter1.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
            else
                Toast.makeText(this, "Please select some items in list 1", Toast.LENGTH_SHORT).show();
        });

        binding.moveFromListTwoToListOne.setOnClickListener(view->{
            if(selected2!=null){
                list1.addAll(selected2);
                selected2.forEach(list2::remove);
                adapter2.selected.removeAll(selected2);
                adapter1.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
            else
                Toast.makeText(this, "Please select some items in list 2", Toast.LENGTH_SHORT).show();
        });

        binding.swapItems.setOnClickListener(view->{
            if(selected2!=null && selected1!=null){
                Log.d("List 1 : ", list1.toString());
                Log.d("List 2 : ", list2.toString());
                Log.d("Selected 1 : ", selected1.toString());
                Log.d("Selected 2 : ", selected2.toString());
                list1.addAll(selected2);
                Log.d("List 1 : ", list1.toString());
                Log.d("List 2 : ", list2.toString());
                Log.d("Selected 1 : ", selected1.toString());
                Log.d("Selected 2 : ", selected2.toString());
                selected1.forEach(list2::remove);
                Log.d("List 1 : ", list1.toString());
                Log.d("List 2 : ", list2.toString());
                Log.d("Selected 1 : ", selected1.toString());
                Log.d("Selected 2 : ", selected2.toString());
                list2.addAll(selected1);
                Log.d("List 1 : ", list1.toString());
                Log.d("List 2 : ", list2.toString());
                Log.d("Selected 1 : ", selected1.toString());
                Log.d("Selected 2 : ", selected2.toString());
                selected2.forEach(list1::remove);
                Log.d("List 1 : ", list1.toString());
                Log.d("List 2 : ", list2.toString());
                Log.d("Selected 1 : ", selected1.toString());
                Log.d("Selected 2 : ", selected2.toString());
                adapter1.selected.removeAll(selected1);
                adapter2.selected.removeAll(selected2);
                Log.d("List 1 : ", list1.toString());
                Log.d("List 2 : ", list2.toString());
                Log.d("Selected 1 : ", selected1.toString());
                Log.d("Selected 2 : ", selected2.toString());
                adapter1.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
            else
                Toast.makeText(this, "Please select some items in each list", Toast.LENGTH_SHORT).show();
        });
    }
}