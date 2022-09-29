package com.prateek.androidpr.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prateek.androidpr.repository.DataRepository;

import java.util.List;

public class DataViewModel extends AndroidViewModel {
    private DataRepository repository;
    public LiveData<List<String>> listOneItems;
    public LiveData<List<String>> listTwoItems;


    public DataViewModel(@NonNull Application application) {
        super(application);
        repository = new DataRepository(application);
        listOneItems = repository.listOneData;
        listTwoItems = repository.listTwoData;
    }

    public void insert(String text){
        repository.insertString(text.trim());
    }

    public void delete(String text){
        repository.deleteFromBothLists(text.trim());
    }

    public void copyFromOneToTwo(List<String> itemsOne){
        repository.copyFromListOneToListTwo(itemsOne);
    }

    public void copyFromTwoToOne(List<String> itemsTwo){
        repository.copyFromListTwoToListOne(itemsTwo);
    }

    public void moveFromOneToTwo(List<String> itemsOne){
        repository.moveFromListOneToListTwo(itemsOne);
    }

    public void moveFromTwoToOne(List<String> itemsTwo){
        repository.moveFromListTwoToListOne(itemsTwo);
    }

    public void swap(List<String> itemsOne, List<String> itemsTwo){
        repository.swap(itemsOne, itemsTwo);
    }
}
