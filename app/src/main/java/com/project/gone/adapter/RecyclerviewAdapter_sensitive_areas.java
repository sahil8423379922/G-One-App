package com.project.gone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.gone.R;

import java.util.ArrayList;

public class RecyclerviewAdapter_sensitive_areas extends RecyclerView.Adapter<RecyclerviewAdapter_sensitive_areas.Viewholder> {

    private ArrayList<String> active_cases;
    private ArrayList<String> fatal_cases;
    private ArrayList<String> locality_name;

    public RecyclerviewAdapter_sensitive_areas(ArrayList<String> active_cases, ArrayList<String> fatal_cases, ArrayList<String> locality_name) {
        this.active_cases = active_cases;
        this.fatal_cases = fatal_cases;
        this.locality_name = locality_name;
    }

    @NonNull
    @Override
    public RecyclerviewAdapter_sensitive_areas.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cases_view, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapter_sensitive_areas.Viewholder holder, int position) {

        holder.fatalvases.setText(String.valueOf(fatal_cases.get(position)));
        holder.activecases.setText(String.valueOf(active_cases.get(position)));
        holder.locatility.setText(String.valueOf(locality_name.get(position)));



    }

    @Override
    public int getItemCount() {
        return active_cases.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView fatalvases;
        private TextView activecases;
        private TextView locatility;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            fatalvases = itemView.findViewById(R.id.fatal);
            activecases = itemView.findViewById(R.id.active);
            locatility = itemView.findViewById(R.id.locality);


        }

        @Override
        public void onClick(View v) {

        }
    }


}
