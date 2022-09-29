package com.prateek.androidp.ui.activities;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.prateek.androidp.databinding.ActivityMainBinding;
import com.prateek.androidp.models.ListItemTableOne;
import com.prateek.androidp.models.ListItemTableTwo;
import com.prateek.androidp.ui.adapters.ListAdapterOne;
import com.prateek.androidp.ui.adapters.ListAdapterTwo;
import com.prateek.androidp.usecases.ListItemsOperations;
import com.prateek.androidp.usecases.SelectionChangedListener;
import com.prateek.androidp.viewmodels.ListsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements SelectionChangedListener {
    private ActivityMainBinding binding;
    private List<ListItemTableOne> tableOneList;
    private List<ListItemTableTwo> tableTwoList;
    private List<ListItemTableOne> selectedTableOne;
    private List<ListItemTableTwo> selectedTableTwo;
    private ListsViewModel viewModel;
    private ListAdapterOne listAdapterOne;
    private ListAdapterTwo listAdapterTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialize();

        viewModel.getTableOneItems().observe(this, tableOneItem -> {
            tableOneList = tableOneItem;
            listAdapterOne = new ListAdapterOne(tableOneList, this);
            binding.listOneRecycler.setAdapter(listAdapterOne);
        });

        viewModel.getTableTwoItems().observe(this, tableTwoItem -> {
            tableTwoList=tableTwoItem;
            listAdapterTwo = new ListAdapterTwo(tableTwoList, this);
            binding.listTwoRecycler.setAdapter(listAdapterTwo);
        });

        setClickListeners();
    }

    private void setClickListeners() {
        binding.addButton.setOnClickListener(v -> {
            ListItemsOperations operations = new ListItemsOperations();
            operations.addFromEditText(viewModel, binding.editText.getText().toString().trim(), tableTwoList);
            binding.editText.setText("");
        });
        binding.removeButton.setOnClickListener(v -> {
            ListItemsOperations operations = new ListItemsOperations();
            operations.removeFromEditText(viewModel, binding.editText.getText().toString().trim());
            binding.editText.setText("");
        });
        binding.copyFromListOneToListTwo.setOnClickListener(v -> {
            Log.d("DB", "ONCLICK");
            ListItemsOperations operations = new ListItemsOperations();
            operations.copyFromListOneToListTwo(viewModel, selectedTableOne, tableTwoList);
        });
        binding.copyFromListTwoToListOne.setOnClickListener(v-> {
            Log.d("DB", "ON CLICK");
            ListItemsOperations operations = new ListItemsOperations();
            operations.copyFromListTwoTopListOne(viewModel, selectedTableTwo, tableOneList);
        });
        binding.moveFromListOneToListTwo.setOnClickListener(v-> {
            ListItemsOperations operations = new ListItemsOperations();
            operations.moveFromListOneToListTwo(viewModel, selectedTableOne, tableTwoList);
        });
        binding.moveFromListTwoToListOne.setOnClickListener(v->{
            ListItemsOperations operations = new ListItemsOperations();
            operations.moveFromListTwoToListOne(viewModel, selectedTableTwo, tableOneList);
        });
        binding.swapItems.setOnClickListener(v -> {
            ListItemsOperations operations = new ListItemsOperations();
            operations.swapItems(viewModel, selectedTableOne, selectedTableTwo, tableOneList, tableTwoList);
        });
    }

    private void initialize() {
        tableOneList = new ArrayList<>();
        tableTwoList = new ArrayList<>();
        binding.listOneRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.listTwoRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.listOneRecycler.setHasFixedSize(true);
        binding.listTwoRecycler.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(ListsViewModel.class);
    }

    @Override
    public void onTableOneSelectionsChanged(List<ListItemTableOne> tableOneList) {
        Log.d("debug", "Change in selected table one");
        selectedTableOne = tableOneList;
    }

    @Override
    public void onTableTwoSelectionsChanged(List<ListItemTableTwo> tableTwoList) {
        Log.d("debug", "change in selected table two");
        selectedTableTwo = tableTwoList;
    }

}