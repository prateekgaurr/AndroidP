package com.prateek.androidpr.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.prateek.androidpr.db.ItemsDao;
import com.prateek.androidpr.db.RoomDB;
import com.prateek.androidpr.models.ListItem;

import java.util.List;

public class DataRepository {
    private ItemsDao dao;
    public LiveData<List<String>> listOneData;
    public LiveData<List<String>> listTwoData;

    public DataRepository(Context context){
        RoomDB db = RoomDB.getInstance(context);
        dao = db.dao();
        listOneData = dao.getAllListOneItems();
        listTwoData = dao.getAllListTwoItems();
    }

    public void insertString(String text){
        if(!isAlreadyPresent(dao.getAllListTwoItems().getValue(), text)){
            dao.insertValue(new ListItem(text, 2));
            Log.d("DB", text+" --> Inserted in table as a list 2 Item");
        }
        else
            Log.d("DB", "Item already present in List 2, So skipped");
    }

    private boolean isAlreadyPresent(List<String> values, String text) {
        if(values == null) return false;
        for(String item : values){
            if(item.equals(text)) return true;
        }
        return false;
    }

    public void deleteFromBothLists(String text){
        dao.deleteFromBothTables(text);
        Log.d("DB", text+"  --> Delete from both lists DONE");
    }

    public void copyFromListOneToListTwo(List<String> toBeCopied){
        if(toBeCopied == null){
            Log.d("DB", "copy from list one To list two --> ERROR --> List was null");
            return;
        }
        for(String s : toBeCopied){
            if(!isAlreadyPresent(dao.getAllListTwoItems().getValue(), s)){
                dao.insertValue(new ListItem(s, 2));
                Log.d("DB", s+"  --> copy from List One To List Two --> SUCCESS");
            }
            else
                Log.d("DB", s+"  --> copy from List One To List Two --> ERROR --> was already present in List 2");
        }
    }

    public void copyFromListTwoToListOne(List<String> toBeCopied){
        if(toBeCopied == null){
            Log.d("DB", "copy from list two To list one --> ERROR --> List was null");
            return;
        }
        for(String s : toBeCopied){
            if(!isAlreadyPresent(dao.getAllListOneItems().getValue(), s)){
                dao.insertValue(new ListItem(s, 1));
                Log.d("DB", s+"  --> copy from List two To List one --> SUCCESS");
            }
            else
                Log.d("DB", s+"  --> copy from List two To List one --> ERROR --> was already present in List 1");
        }
    }

    public void moveFromListTwoToListOne(List<String> toBeMoved){
        if(toBeMoved == null){
            Log.d("DB", "move from list two To list one --> ERROR --> List was null");
            return;
        }
        for(String s : toBeMoved){
            if(!isAlreadyPresent(dao.getAllListOneItems().getValue(), s)){
                dao.insertValue(new ListItem(s, 1));
                dao.deleteItemInListTwo(s);
                Log.d("DB", s+"  --> move from List two To List one --> SUCCESS");
            }
            else
                Log.d("DB", s+"  --> move from List two To List one --> ERROR --> was already present in List 1");
        }
    }

    public void moveFromListOneToListTwo(List<String> toBeMoved){
        if(toBeMoved == null){
            Log.d("DB", "move from list one To list two --> ERROR --> List was null");
            return;
        }
        for(String s : toBeMoved){
            if(!isAlreadyPresent(dao.getAllListTwoItems().getValue(), s)){
                dao.insertValue(new ListItem(s, 2));
                dao.deleteItemInListOne(s);
                Log.d("DB", s+"  --> move from List One To List Two --> SUCCESS");
            }
            else
                Log.d("DB", s+"  --> move from List One To List Two --> ERROR --> was already present in List 1");
        }
    }

    public void swap(List<String> listOne, List<String> listTwo){
        if(listOne == null || listTwo == null){
            Log.d("DB", "swap--> SKIPPED as any of the list is empty");
            return;
        }
        for(String s : listOne){
            dao.swapFromOneToTwo(s);
            Log.d("DB", s+"  --> swap from one to two SUCCESS");
        }
        for(String s : listTwo){
            dao.swapFromTwoToOne(s);
            Log.d("DB", s+"  --> swap from two to one SUCCESS");
        }


    }

}
