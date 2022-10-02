package com.prateek.android_p;


import java.util.List;

public interface SelectionChangedListener {

    void onTableOneSelectionsChanged(List<String> tableOneList);

    void onTableTwoSelectionsChanged(List<String> tableTwoList);
}
