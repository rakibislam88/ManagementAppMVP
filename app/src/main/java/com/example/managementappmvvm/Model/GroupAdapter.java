package com.example.managementappmvvm.Model;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.managementappmvvm.R;
import com.example.managementappmvvm.View.HomeActivity;
import com.example.managementappmvvm.View.OnItemClickListernerInterface;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupView>{

    private Context context;
    private ArrayList<BoardNameModelClass> boardNameArr = new ArrayList<>();
    private ArrayList<GroupNameModelClass> groupNameArr = new ArrayList<>();
    public static OnItemClickListernerInterface mListener;



    public void setOnItemClickListener(OnItemClickListernerInterface listener){
        mListener = listener;
    }
    public GroupAdapter (Context context, ArrayList<GroupNameModelClass> groupNameArr, ArrayList<BoardNameModelClass> boardNameArr) {
            this.context = context;
            this.groupNameArr = groupNameArr;
            this.boardNameArr = boardNameArr;
    }

    @NonNull
    @Override
    public GroupView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.groupnamelayout, parent, false);

        return new GroupView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupView holder, @SuppressLint("RecyclerView") int position) {

        holder.GROUP_FIRST_CHAR.setText(groupNameArr.get(position).getFirst_char());
        holder.GROUP_NAME.setText(groupNameArr.get(position).getGroup_name());





        /****************************************************************************************
         *
         * start
         * show group board file
         *
         */
        holder.SHOW_BOARD_LAYOUT.setVisibility(View.GONE);
        holder.SHOW_GROUP_BOARD_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isVisible = holder.SHOW_BOARD_LAYOUT.getVisibility();
                if (isVisible==View.VISIBLE){
                    holder.SHOW_BOARD_LAYOUT.setVisibility(View.GONE);
                }else{

                    BoardAdapter boardAdapter = new BoardAdapter(context, boardNameArr);
                    holder.BOARD_RECYCLE.setLayoutManager(new GridLayoutManager(context, 1));
                    holder.BOARD_RECYCLE.setAdapter(boardAdapter);
                    holder.SHOW_BOARD_LAYOUT.setVisibility(View.VISIBLE);
                }
            }


        });






        /****************************************************************************************
         *
         * start
         * add group features
         *
         */
        Dialog dialog = new Dialog(context);
        holder.GROUP_MORE_ICON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.popup_window);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayout ADD_PEOPLE_BUTTON = dialog.findViewById(R.id.add_people);
                LinearLayout ADD_NEW_BOARD = dialog.findViewById(R.id.add_board);


                Dialog createBoardDialog = new Dialog(context);
                ADD_NEW_BOARD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createBoardDialog.setContentView(R.layout.create_board_out);
                        createBoardDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        TextView create_board_button = createBoardDialog.findViewById(R.id.create_board_button);
                        EditText board_name = createBoardDialog.findViewById(R.id.create_board_name);

                        create_board_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String boardName = board_name.getText().toString().trim();
                                mListener.Board(groupNameArr.get(position).getGroup_name(), position, boardName);
                                dialog.dismiss();
                                createBoardDialog.dismiss();
                            }
                        });



                        createBoardDialog.show();
                    }
                });

                Dialog add_people_dialog = new Dialog(context);
                ADD_PEOPLE_BUTTON.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        add_people_dialog.setContentView(R.layout.add_people_layout);
                        add_people_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        RecyclerView ADD_PEOPLE_RECYCLE = add_people_dialog.findViewById(R.id.suggested_people);


                        add_people_dialog.show();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupNameArr.size();
    }

    public class GroupView extends RecyclerView.ViewHolder{

        TextView GROUP_FIRST_CHAR, GROUP_NAME;
        ImageView SHOW_GROUP_BOARD_BTN, GROUP_MORE_ICON;
        LinearLayout SHOW_BOARD_LAYOUT;
        RecyclerView BOARD_RECYCLE;
        public GroupView(@NonNull View itemView) {
            super(itemView);

            GROUP_FIRST_CHAR = itemView.findViewById(R.id.group_frist_digit);
            GROUP_NAME = itemView.findViewById(R.id.group_name);
            SHOW_GROUP_BOARD_BTN = itemView.findViewById(R.id.show_group_file);
            GROUP_MORE_ICON = itemView.findViewById(R.id.group_more_icon);
            SHOW_BOARD_LAYOUT = itemView.findViewById(R.id.show_board_layout);
            BOARD_RECYCLE   = itemView.findViewById(R.id.board_recycle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener!=null){
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            mListener.OnItem(position, groupNameArr.get(position).getId());
                        }
                    }
                }
            });

        }
    }

}
