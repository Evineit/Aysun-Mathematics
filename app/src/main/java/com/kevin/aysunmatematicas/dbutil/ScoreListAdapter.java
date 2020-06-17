package com.kevin.aysunmatematicas.dbutil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kevin.aysunmatematicas.R;

import java.util.List;

public class ScoreListAdapter extends RecyclerView.Adapter<ScoreListAdapter.ScoreViewHolder> {
    class ScoreViewHolder extends RecyclerView.ViewHolder{
        private final TextView scoreItemView;
        private ScoreViewHolder(View itemView){
            super(itemView);
            scoreItemView = itemView.findViewById(R.id.textView);
        }
    }
    private final LayoutInflater mInflater;
    private List<Score> mScores;
    public ScoreListAdapter(Context context){
        mInflater=LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item,parent,false);
        return new ScoreViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        if (mScores != null) {
            Score current = mScores.get(position);
            holder.scoreItemView.setText("Sesion:"+current.id+" Puntos: "+current.puntos);
        } else {
            // Covers the case of data not being ready yet.
            holder.scoreItemView.setText("No hay puntos");
        }
    }

    @Override
    public int getItemCount() {
        if (mScores != null)
            return mScores.size();
        else return 0;
    }
    public void setScores(List<Score> scores){
        mScores = scores;
        notifyDataSetChanged();
    }
}
