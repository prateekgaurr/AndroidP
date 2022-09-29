package com.prateek.androidpr.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.prateek.androidpr.databinding.ActivityMainBinding;
import com.prateek.androidpr.viewmodel.DataViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements ItemsSelectedListenerOne, ItemsSelectedListenerTwo{
    private ActivityMainBinding binding;
    private List<String> selectedOne = new ArrayList<>();
    private List<String> selectedTwo = new ArrayList<>();
    private List<String> itemsOne;
    private List<String> itemsTwo;
    private DataViewModel viewModel;
    private ExecutorService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        service = Executors.newSingleThreadExecutor();
        binding.listOneRecycler.setHasFixedSize(true);
        binding.listTwoRecycler.setHasFixedSize(true);
        binding.listOneRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.listTwoRecycler.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(DataViewModel.class);

        viewModel.listTwoItems.observe(this, tableTwoList-> {
            itemsTwo = tableTwoList;
            updateUIOne();
        });

        viewModel.listOneItems.observe(this, tableOneList -> {
            itemsOne = tableOneList;
            updateUITwo();
        });


        setClickListeners();

    }

    private void setClickListeners() {
        binding.addButton.setOnClickListener(v -> {
            service.execute(() -> {
                viewModel.insert(binding.editText.getText().toString().trim());
            });
            binding.editText.setText("");
        });
        binding.removeButton.setOnClickListener(v -> {
            service.execute(()->{
                viewModel.delete(binding.editText.getText().toString().trim());
            });
            binding.editText.setText("");
        });
        binding.copyFromListOneToListTwo.setOnClickListener(v -> {
            service.execute(()->{
                viewModel.copyFromOneToTwo(selectedOne);
            });
        });
        binding.copyFromListTwoToListOne.setOnClickListener(v-> {
            service.execute(()->{
                viewModel.copyFromTwoToOne(selectedTwo);
            });
        });
        binding.moveFromListOneToListTwo.setOnClickListener(v-> {
            service.execute(()->{
                viewModel.moveFromOneToTwo(selectedOne);
            });
        });
        binding.moveFromListTwoToListOne.setOnClickListener(v->{
            service.execute(()->{
                viewModel.moveFromTwoToOne(selectedTwo);
            });
        });
        binding.swapItems.setOnClickListener(v -> {
            service.execute(()->{
                viewModel.swap(selectedOne, selectedTwo);
            });
        });
    }

    private void updateUIOne() {
        MyAdapterListOne adapter = new MyAdapterListOne(itemsOne, this);
        binding.listOneRecycler.setAdapter(adapter);
    }

    private void updateUITwo() {
        MyAdapterListTwo adapter = new MyAdapterListTwo(itemsTwo, this);
        binding.listTwoRecycler.setAdapter(adapter);
    }


    @Override
    public void SelectedItemsChanged(List<String> selectedListTwo) {
        Log.d("selection", selectedListTwo.toString());
        this.selectedTwo = selectedListTwo;
    }

    @Override
    public void SelectedItemsChangedOne(List<String> selectedListOne) {
        Log.d("Selection", selectedListOne.toString());
        this.selectedOne = selectedListOne;
    }
}