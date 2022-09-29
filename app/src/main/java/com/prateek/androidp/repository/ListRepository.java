package com.prateek.androidp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.prateek.androidp.database.ListDao;
import com.prateek.androidp.database.ListDatabase;
import com.prateek.androidp.models.ListItemTableOne;
import com.prateek.androidp.models.ListItemTableTwo;

import java.util.List;

public class ListRepository {
    public ListDao dao;
    public LiveData<List<ListItemTableOne>> tableOneLiveData;
    public LiveData<List<ListItemTableTwo>> tableTwoLiveData;

    public ListRepository(Application app){
        ListDatabase db = ListDatabase.getInstance(app);
        dao = db.dao();
        tableOneLiveData = dao.getAllListOneItems();
        tableTwoLiveData = dao.getAllListTwoItems();
    }

    public void insertInTableOne(ListItemTableOne item){
        dao.insertInTableOne(item);
    }

    public void insertInTableTwo(ListItemTableTwo item){
        dao.insertInTableTwo(item);
    }

    public void deleteFromTableOne(int id){
        dao.deleteItemFromTableOne(id);
    }

    public void deleteFromTableTwo(int id){
        dao.deleteItemFromTableTwo(id);
    }

    public void deleteFromTableOne(String text){
        dao.deleteItemFromTableOne(text);
    }

    public void deleteFromTableTwo(String text){
        dao.deleteItemFromTableTwo(text);
    }

}
