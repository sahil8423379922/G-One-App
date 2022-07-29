package com.project.gone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.gone.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Integer> active_cases;
    private ArrayList<Integer> fatal_cases;
    private ArrayList<String> locality_name;
    public Context context;


    public RecyclerViewAdapter(ArrayList<Integer> active_cases, ArrayList<Integer> fatal_cases, ArrayList<String> locality_name, Context context) {
        this.active_cases = active_cases;
        this.fatal_cases = fatal_cases;
        this.locality_name = locality_name;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.active_cases_count, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

      //  holder.fatalvases.setText(String.valueOf(fatal_cases.get(position)));
        //holder.activecases.setText(String.valueOf(active_cases.get(position)));
        //holder.locatility.setText(String.valueOf(locality_name.get(position)));

    }

    @Override
    public int getItemCount() {
        return locality_name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView fatalvases;
        private TextView activecases;
        private TextView locatility;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

       fatalvases = itemView.findViewById(R.id.fatal_cases);
       activecases = itemView.findViewById(R.id.active_count);
       locatility = itemView.findViewById(R.id.active_cases);


        }

        @Override
        public void onClick(View v) {

        }
    }



}
