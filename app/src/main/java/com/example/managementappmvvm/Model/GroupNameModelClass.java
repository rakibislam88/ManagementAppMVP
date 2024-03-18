package com.example.managementappmvvm.Model;

import com.example.managementappmvvm.View.HomeActivity;

public class GroupNameModelClass{

    private int id;
    private String user_id;
    private String group_name;
    private String first_char;

    public GroupNameModelClass(int id, String user_id, String group_name, String first_char) {
        this.id = id;
        this.user_id = user_id;
        this.group_name = group_name;
        this.first_char = first_char;
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

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getFirst_char() {
        return first_char;
    }

    public void setFirst_char(String first_char) {
        this.first_char = first_char;
    }
}
