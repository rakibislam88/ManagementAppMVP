package com.example.managementappmvvm.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardNameModelClass {

    private int id;
    private String user_id;
    private String group_name_of_admin;
    private String board_name;

    public BoardNameModelClass(int id, String user_id, String group_name_of_admin, String board_name) {
        this.id = id;
        this.user_id = user_id;
        this.group_name_of_admin = group_name_of_admin;
        this.board_name = board_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGroup_name_of_admin() {
        return group_name_of_admin;
    }

    public void setGroup_name_of_admin(String group_name_of_admin) {
        this.group_name_of_admin = group_name_of_admin;
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }
}
