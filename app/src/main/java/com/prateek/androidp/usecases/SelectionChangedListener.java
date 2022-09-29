package com.prateek.androidp.usecases;

import com.prateek.androidp.models.ListItemTableOne;
import com.prateek.androidp.models.ListItemTableTwo;

import java.util.List;

public interface SelectionChangedListener {

    void onTableOneSelectionsChanged(List<ListItemTableOne> tableOneList);

    void onTableTwoSelectionsChanged(List<ListItemTableTwo> tableTwoList);
}
