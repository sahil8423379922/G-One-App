package com.project.gone.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.gone.R;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecyclerviewAdapter_hospital extends RecyclerView.Adapter<RecyclerviewAdapter_hospital.Viewholder> {

    private ArrayList<String> hospitalname;
    private ArrayList<String> address;
    private ArrayList<String> availablebed;
    private ArrayList<String> totalbed;
    private ArrayList<String> number;
    private ArrayList<String> lat;
    private ArrayList<String> lon;
    private Context context;

    public RecyclerviewAdapter_hospital(ArrayList<String> hospitalname, ArrayList<String> address, ArrayList<String> availablebed, ArrayList<String> totalbed, ArrayList<String> number, ArrayList<String> lat, ArrayList<String> lon,Context context) {
        this.hospitalname = hospitalname;
        this.address = address;
        this.availablebed = availablebed;
        this.totalbed = totalbed;
        this.number = number;
        this.lat = lat;
        this.lon = lon;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerviewAdapter_hospital.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bed_availability_view, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapter_hospital.Viewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.hospitalname.setText(String.valueOf(hospitalname.get(position)));
        holder.hospitaladdress.setText(String.valueOf(address.get(position)));
        holder.bed_total_count.setText(String.valueOf(totalbed.get(position))+" Bed");
        holder.bed_available_count.setText(String.valueOf(availablebed.get(position))+" Bed");
        holder.contact_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String contactno = number.get(position);
                intent.setData(Uri.parse("tel:" + contactno));
                context.startActivity(intent);

            }
        });

        holder.direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String geoUri = "http://maps.google.com/maps?q=loc:" + lat.get(position) + "," + lon.get(position)+ " (" + "M.Kashiram Hospital" + ")";
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
        private TextView bed_available_count;
        private TextView bed_total_count;
        private Button contact_no,direction;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            hospitalname = itemView.findViewById(R.id.hospital_name);
            hospitaladdress = itemView.findViewById(R.id.address);
            bed_available_count = itemView.findViewById(R.id.reserved_bed_in_hospital);
            bed_total_count = itemView.findViewById(R.id.total_bed_in_hospital);
            contact_no = itemView.findViewById(R.id.contact_no);
            direction = itemView.findViewById(R.id.direction);



        }

        @Override
        public void onClick(View v) {

        }
    }


}
