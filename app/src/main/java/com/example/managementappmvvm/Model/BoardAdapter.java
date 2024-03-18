package com.example.managementappmvvm.Model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managementappmvvm.R;
import com.example.managementappmvvm.View.OnItemClickListernerInterface;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardView> {

    private Context context;
    private ArrayList<BoardNameModelClass> boardNameArr = new ArrayList<>();


    public BoardAdapter(Context context, ArrayList<BoardNameModelClass> boardNameArr) {
        this.context = context;
        this.boardNameArr = boardNameArr;
    }

    @NonNull
    @Override
    public BoardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.board_layout, parent, false);

        return new BoardView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardView holder, @SuppressLint("RecyclerView") int position) {

        holder.boardName.setText(boardNameArr.get(position).getBoard_name());



    }

    @Override
    public int getItemCount() {
        return boardNameArr.size();
    }

    public class BoardView extends RecyclerView.ViewHolder{

        TextView boardName;
        public BoardView(@NonNull View itemView) {
            super(itemView);
            boardName = itemView.findViewById(R.id.board_name);
        }
    }
}
