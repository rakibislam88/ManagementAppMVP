package com.example.managementappmvvm.View;

public interface OnItemClickListernerInterface {
    void OnItem(int position, int group_id);
    void Board(String group_name_of_admin, int position, String board_name);
}
