package com.prateek.androidp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prateek.androidp.models.ListItemTableOne;
import com.prateek.androidp.models.ListItemTableTwo;
import com.prateek.androidp.repository.ListRepository;

import java.util.List;

public class ListsViewModel extends AndroidViewModel {

    ListRepository repository;
    LiveData<List<ListItemTableOne>> tableOneItems;
    LiveData<List<ListItemTableTwo>> tableTwoItems;


    public ListsViewModel(@NonNull Application application) {
        super(application);
        repository = new ListRepository(application);
        tableOneItems = repository.tableOneLiveData;
        tableTwoItems = repository.tableTwoLiveData;
    }

    public LiveData<List<ListItemTableOne>> getTableOneItems() {
        return tableOneItems;
    }

    public LiveData<List<ListItemTableTwo>> getTableTwoItems() {
        return tableTwoItems;
    }


    public void insertInTableOne(ListItemTableOne item){
        repository.insertInTableOne(item);
    }

    public void insertInTableTwo(ListItemTableTwo item){
        repository.insertInTableTwo(item);
    }

    public void deleteFromTableOne(int id){
        repository.deleteFromTableOne(id);
    }

    public void deleteFromTableTwo(int id){
        repository.deleteFromTableTwo(id);
    }

    public void deleteFromTableOne(String text){
        repository.deleteFromTableOne(text);
    }

    public void deleteFromTableTwo(String text){
        repository.deleteFromTableTwo(text);
    }

}
