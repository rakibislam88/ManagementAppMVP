package com.example.managementappmvvm.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.managementappmvvm.Model.BoardNameModelClass;
import com.example.managementappmvvm.Model.GroupAdapter;
import com.example.managementappmvvm.Model.GroupNameModelClass;
import com.example.managementappmvvm.Model.MySingleton;
import com.example.managementappmvvm.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity  implements OnItemClickListernerInterface{


    public static ArrayList<BoardNameModelClass> boardNameArr = new ArrayList<>();
    public static ArrayList<GroupNameModelClass> groupNameArr = new ArrayList<>();
    public static ArrayList<String> checkgroupNameArr = new ArrayList<>();
    ImageView ADD_GROUP_BTN;
    RecyclerView GROUP_RECYCLE_VIEW;
    GroupAdapter GROUP_ADAPTER;
    SharedPreferences shared1, shared2;
    public String admingroupid = "";

    public static int POSITION;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        /****************************************************************************************
         *
         * start
         * find id
         *
         */
        ADD_GROUP_BTN = findViewById(R.id.add_group);
        GROUP_RECYCLE_VIEW = findViewById(R.id.group_recycle);





        /****************************************************************************************
         *
         * start
         * all function loader
         *
         */
        groupNameJsonArrFun();
        BoardNameJsonArrFun();




        /****************************************************************************************
         *
         * start
         * get adming id
         *
         */
        shared1 = getSharedPreferences("loginData", MODE_PRIVATE);
        shared2 = getSharedPreferences("signupsp", MODE_PRIVATE);
        admingroupid = shared2.getString("token", "");







        /****************************************************************************************
         *
         * start
         * create group
         *
         */
        Dialog GROUP_DIALOG = new Dialog(this);
        ADD_GROUP_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GROUP_DIALOG.setContentView(R.layout.create_group_layout);
                GROUP_DIALOG.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                ImageView GROUP_DISMIS = GROUP_DIALOG.findViewById(R.id.close_group_dialog);
                TextView textView = GROUP_DIALOG.findViewById(R.id.group_frist_digit);
                Button CREAET_GROUP_BTN = GROUP_DIALOG.findViewById(R.id.create_group_btn);
                EditText GROUP_NAME = GROUP_DIALOG.findViewById(R.id.group_name);



                /****************************************************************************************
                 *
                 * start
                 * group dialog close
                 *
                 */
                GROUP_DISMIS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GROUP_DIALOG.dismiss();
                    }
                });




                /****************************************************************************************
                 *
                 * start
                 * create group button
                 *
                 */
                CREAET_GROUP_BTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String groupName = GROUP_NAME.getText().toString();
                        String firstDigit = "G";
                        int p = 1;
                        String url = "https://raquib.000webhostapp.com/apps/group_name_data.php?userid="+ admingroupid + "&groupname="+ groupName + "&position="+ p  +"&groupfirstdigit="+ firstDigit;

                        if (checkgroupNameArr.contains(groupName)){
                            Toast.makeText(HomeActivity.this, "Group name must be unique?", Toast.LENGTH_SHORT).show();
                        }else{
                            StringRequest add_group_request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    groupNameJsonArrFun();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });
                            RequestQueue queue = Volley.newRequestQueue(HomeActivity.this);
                            queue.add(add_group_request);
                        }
                    }
                });




                GROUP_DIALOG.show();
            }
        });









    }




    /****************************************************************************************
     *
     * start
     * load group name
     *
     */
    public void groupNameJsonArrFun() {
        groupNameArr      = new ArrayList<>();
        checkgroupNameArr = new ArrayList<>();

        RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        JsonArrayRequest groupNameRequest = new JsonArrayRequest(Request.Method.GET, "https://raquib.000webhostapp.com/apps/arr_of_group_name_data.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String userid = jsonObject.getString("userid");
                        String groupnm = jsonObject.getString("groupname");
                        String groupfd = jsonObject.getString("groupfirstdigit");
                        //int position = jsonObject.getInt("position");
                        //if (admingroupid.equals(userid)){
                            groupNameArr.add(new GroupNameModelClass(id, userid, groupnm, groupfd));
                        //}
                        checkgroupNameArr.add(groupnm.toLowerCase());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }

                if (groupNameArr.size() > 0){
                    GROUP_ADAPTER = new GroupAdapter(HomeActivity.this, groupNameArr, boardNameArr);
                    GROUP_RECYCLE_VIEW.setLayoutManager(new GridLayoutManager(HomeActivity.this, 1));
                    GROUP_RECYCLE_VIEW.setAdapter(GROUP_ADAPTER);
                    GROUP_ADAPTER.setOnItemClickListener(HomeActivity.this);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(this).addToRequestQueue(groupNameRequest);
    }






    /****************************************************************************************
     *
     * start
     * load board name
     *
     */
    public static void BoardNameJsonArrFun(){
        boardNameArr = new ArrayList<>();


        //RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        JsonArrayRequest boardNameRequest = new JsonArrayRequest(Request.Method.GET, "https://raquib.000webhostapp.com/apps/arr_of_board.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String userid = jsonObject.getString("userid");
                        String groupofnm = jsonObject.getString("groupnameofadmin");
                        String groupnm = jsonObject.getString("createboardname");
                        int position = jsonObject.getInt("position");
                        if (0== position){
                            boardNameArr.add(new BoardNameModelClass(id, userid, groupofnm, groupnm));
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        //MySingleton.getInstance(this).addToRequestQueue(boardNameRequest);
    }





    /****************************************************************************************
     *
     * start
     * group item section
     *
     */

    @Override
    public void OnItem(int position, int group_id) {

        /**
        String url = "https://raquib.000webhostapp.com/apps/delete_group_name.php?id="+group_id;
        StringRequest deleteRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                groupNameJsonArrFun();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(deleteRequest);
         **/
    }




    @Override
    public void Board(String group_name_of_admin, int position, String board_name) {
        String nl = "null";
        String board = "https://raquib.000webhostapp.com/apps/create_board_name.php?adminid="+ admingroupid  +"&adminname="+ nl +"&groupnameofadmin="+ group_name_of_admin +"&position="+ position +"&createboardname="+ board_name;
        Toast.makeText(HomeActivity.this, ""+position + group_name_of_admin, Toast.LENGTH_SHORT).show();
        StringRequest boardRequest = new StringRequest(Request.Method.GET, board, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(boardRequest);

    }






}