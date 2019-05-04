package com.sim.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TypeAdaptor extends  RecyclerView.Adapter<TypeAdaptor.MyViewHolderType> {

    private Context context;
    public List<TypeClasse> leadersList;

    public TypeAdaptor(Context context, List<TypeClasse> leadersList) {
        this.context = context;
        this.leadersList = leadersList;
    }

    @NonNull
    @Override
    public MyViewHolderType onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.class_item, viewGroup, false);

        return new MyViewHolderType(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderType myViewHolderType, final int i) {

        final  TypeClasse u = leadersList.get(i);
        myViewHolderType.score.setText(String.valueOf(u.getScore()));
        myViewHolderType.type_classe.setText(u.getNom_class());
        myViewHolderType.xmax.setText(String.valueOf(u.getBox().getXmax()));
        myViewHolderType.xmin.setText(String.valueOf(u.getBox().getXmin()));
        myViewHolderType.ymax.setText(String.valueOf(u.getBox().getYmax()));
        myViewHolderType.ymin.setText(String.valueOf(u.getBox().getYmin()));
    }

    @Override
    public int getItemCount() {
        return leadersList.size();
    }

    public class MyViewHolderType extends RecyclerView.ViewHolder {

        public TextView score , type_classe , xmin , xmax , ymin , ymax ;

        public MyViewHolderType(@NonNull View v) {
            super(v);
            score = v.findViewById(R.id.score);
            type_classe = v.findViewById(R.id.class_type);
            xmin = v.findViewById(R.id.xmin);
            xmax = v.findViewById(R.id.xmax);
            ymin = v.findViewById(R.id.ymin);
            ymax = v.findViewById(R.id.ymax);

        }


    }

}
