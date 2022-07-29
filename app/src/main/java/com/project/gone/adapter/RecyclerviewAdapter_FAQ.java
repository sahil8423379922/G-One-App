package com.project.gone.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.gone.R;
import com.project.gone.load_frequently_asked_question;

import java.util.ArrayList;

public class RecyclerviewAdapter_FAQ extends RecyclerView.Adapter<RecyclerviewAdapter_FAQ.Viewholder> {

    private ArrayList<String> faq;
    private ArrayList<String> link;
    private Context context;


    public RecyclerviewAdapter_FAQ(ArrayList<String> faq, ArrayList<String> link, Context context) {
        this.faq = faq;
        this.link = link;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerviewAdapter_FAQ.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout_faq, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapter_FAQ.Viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.faq_question.setText(String.valueOf(faq.get(position)));
        holder.faq_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link1 = link.get(position);
                Intent intent = new Intent(context, load_frequently_asked_question.class);
                Bundle bundle = new Bundle();
                bundle.putString("link", link1);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return faq.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView faq_question;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            faq_question = itemView.findViewById(R.id.faq_question);


        }

        @Override
        public void onClick(View v) {

        }
    }


}
