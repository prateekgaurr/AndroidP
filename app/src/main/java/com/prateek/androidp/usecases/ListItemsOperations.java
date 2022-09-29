package com.prateek.androidp.usecases;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.prateek.androidp.models.ListItemTableOne;
import com.prateek.androidp.models.ListItemTableTwo;
import com.prateek.androidp.viewmodels.ListsViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListItemsOperations {

    private final ExecutorService service = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public void copyFromListOneToListTwo(ListsViewModel viewModel, List<ListItemTableOne> tableOneSelected, List<ListItemTableTwo> allTableTwo) {
        if(tableOneSelected == null) {
            Log.d("DB Table 1 selected none", "tableOneSelected.toString()");
            return;
        }
        service.execute(() -> {
            for (ListItemTableOne itemTableOne : tableOneSelected) {
                if(!isFoundInTwoList(allTableTwo, itemTableOne.text)){
                    ListItemTableTwo itemTableTwo = new ListItemTableTwo(itemTableOne.text);
                    viewModel.insertInTableTwo(itemTableTwo);
                }
            }
            handler.post(() -> Log.d("DB", "copyFromListOneToListTwo Completed"));
        });
    }

    public void copyFromListTwoTopListOne(ListsViewModel viewModel, List<ListItemTableTwo> selectedTableTwo, List<ListItemTableOne> allTableOne) {
        Log.d("DB", "Copy from Table Two To Table One Initiated");
        if(selectedTableTwo == null) return;
        service.execute(() -> {
            for (ListItemTableTwo itemTableTwo : selectedTableTwo) {
                if(!isFoundInOneList(allTableOne, itemTableTwo.text)){
                    ListItemTableOne itemTableOne = new ListItemTableOne(itemTableTwo.text);
                    viewModel.insertInTableOne(itemTableOne);
                }
            }
            handler.post(() -> Log.d("DB", "copy From List Two To List One Completed"));
        });
    }

    public void addFromEditText(ListsViewModel viewModel, String itemText, List<ListItemTableTwo> allTableTwo) {
        if(TextUtils.isEmpty(itemText)) return;
        service.execute(() -> {
            if(!isFoundInTwoList(allTableTwo, itemText)){
                viewModel.insertInTableTwo(new ListItemTableTwo(itemText));
            }
            handler.post(() -> Log.d("DB", "added to table"));
        });
    }

    public void removeFromEditText(ListsViewModel viewModel, String toBeDeleted) {
        service.execute(() -> {
                viewModel.deleteFromTableOne(toBeDeleted);
                viewModel.deleteFromTableTwo(toBeDeleted);
            handler.post(() -> Log.d("DB", "Deleted from both lists"));
        });
    }

    public void moveFromListOneToListTwo(ListsViewModel viewModel, List<ListItemTableOne> selectedTableOne, List<ListItemTableTwo> allTableTwo) {
        Log.d("DB", "Copy from One to Two Initiated");
        if(selectedTableOne == null) {
            Log.d("DB", "SELECTED TABLE ONE EMPTY");
            return;
        }
        service.execute(() -> {
            for(ListItemTableOne tableOneItem : selectedTableOne){
                if(!isFoundInTwoList(allTableTwo, tableOneItem.text)){
                    viewModel.insertInTableTwo(new ListItemTableTwo(tableOneItem.text));
                    viewModel.deleteFromTableOne(tableOneItem.id);
                }
            }
            handler.post(() -> Log.d("DB", "Move from Table One To Table Two Completed"));
        });
    }

    public void moveFromListTwoToListOne(ListsViewModel viewModel, List<ListItemTableTwo> selectedTableTwo, List<ListItemTableOne> allTableOne) {
        if(selectedTableTwo == null) return;
        service.execute(() -> {
            for(ListItemTableTwo tableTwoItem : selectedTableTwo){
                if(!isFoundInOneList(allTableOne, tableTwoItem.text)){
                    viewModel.insertInTableOne(new ListItemTableOne(tableTwoItem.text));
                    viewModel.deleteFromTableTwo(tableTwoItem.id);
                }
            }
            handler.post(() -> Log.d("DB", "Move from Table Two To Table One Completed"));
        });
    }

    public void swapItems(ListsViewModel viewModel, List<ListItemTableOne> selectedTableOne, List<ListItemTableTwo> selectedTableTwo, List<ListItemTableOne> allTableOne, List<ListItemTableTwo> allTableTwo) {
        if(selectedTableOne == null && selectedTableTwo == null) return;
        moveFromListOneToListTwo(viewModel, selectedTableOne, allTableTwo);
        moveFromListTwoToListOne(viewModel, selectedTableTwo, allTableOne);
    }

    private Boolean isFoundInOneList(List<ListItemTableOne> allItems, String item){
        if(allItems == null || item == null) return false;
        for(ListItemTableOne listItem : allItems){
            if (listItem.text.equals(item)) return true;
        }
        return false;
    }

    private Boolean isFoundInTwoList(List<ListItemTableTwo> allItems, String item){
        if(allItems == null || item == null) return false;
        for(ListItemTableTwo listItem : allItems){
            if (listItem.text.equals(item)) return true;
        }
        return false;
    }
}
