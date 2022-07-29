package com.project.gone.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.gone.Direction;
import com.project.gone.R;

import java.util.ArrayList;

public class RecyclerviewAdapter_oxygen_available extends RecyclerView.Adapter<RecyclerviewAdapter_oxygen_available.Viewholder> {

    private ArrayList<String> hospitalname;
    private ArrayList<String> address;
    private ArrayList<String> oxygen_available;
    private Context context;
    private ArrayList<String> contactlist;
    private ArrayList<String> lat,lon;


    public RecyclerviewAdapter_oxygen_available(ArrayList<String> hospitalname, ArrayList<String> address, ArrayList<String> oxygen_available, Context context, ArrayList<String> contactlist, ArrayList<String> lat,ArrayList<String> lon) {
        this.hospitalname = hospitalname;
        this.address = address;
        this.oxygen_available = oxygen_available;
        this.context = context;
        this.contactlist = contactlist;
        this.lat = lat;
        this.lon=lon;



    }

    @NonNull
    @Override
    public RecyclerviewAdapter_oxygen_available.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oxygen_availability_view, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapter_oxygen_available.Viewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.hospitalname.setText(String.valueOf(hospitalname.get(position)));
        holder.hospitaladdress.setText(String.valueOf(address.get(position)));
        holder.oxygen_available.setText(String.valueOf(oxygen_available.get(position)));
        holder.dialer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String contactno = contactlist.get(position);
                intent.setData(Uri.parse("tel:" + contactno));
                context.startActivity(intent);
            }
        });
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geoUri = "http://maps.google.com/maps?q=loc:" + lat.get(position) + "," + lon.get(position)+ " (" + hospitalname.get(position) + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return hospitalname.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView hospitalname;
        private TextView hospitaladdress;
        private TextView oxygen_available;
        private TextView dialer;
        private Button button;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            hospitalname = itemView.findViewById(R.id.hospital_name);
            hospitaladdress = itemView.findViewById(R.id.address);
            oxygen_available = itemView.findViewById(R.id.oxygen_available_count);
            dialer = itemView.findViewById(R.id.contact_no);
            button = itemView.findViewById(R.id.direction);


        }

        @Override
        public void onClick(View v) {

        }
    }


}
