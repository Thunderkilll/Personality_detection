package com.sim.myapplication;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class PersonalityAdaptor  extends RecyclerView.Adapter<PersonalityAdaptor.MyViewHolderType>{
    private Context context;
    public List<Personality> leadersList;

    public PersonalityAdaptor(Context context, List<Personality> leadersList) {
        this.context = context;
        this.leadersList = leadersList;
    }

    @NonNull
    @Override
    public PersonalityAdaptor.MyViewHolderType onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.personality_type, viewGroup, false);

        return new PersonalityAdaptor.MyViewHolderType(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull PersonalityAdaptor.MyViewHolderType myViewHolderType, final int i) {

        final  Personality u = leadersList.get(i);
        myViewHolderType.description.setText(String.valueOf(u.getPersonaly_text()));
        myViewHolderType.percentage.setText(String.valueOf(u.getPourcentage()));

    }

    @Override
    public int getItemCount() {
        return leadersList.size();
    }
    public class MyViewHolderType extends RecyclerView.ViewHolder {

        public TextView description , percentage ;

        public MyViewHolderType(@NonNull View v) {
            super(v);
            description = v.findViewById(R.id.description);
            percentage = v.findViewById(R.id.percent);


        }


    }

}

    /*   {









*/

