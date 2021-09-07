package com.example.carsapp_week8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsapp_week8.provider.Car;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    List<Car> data = new ArrayList<>();
//    ArrayList<CarBasic> data;

//    public  MyRecyclerAdapter(ArrayList<CarBasic> data)
//    {
//        this.data = data;
//    }

    public MyRecyclerAdapter()
    {

    }

    public void setData(List<Car> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false); //inflate cardView to recyclerView
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder; // pass viewHolder to the next callback (onBindViewHolder); whatever outcome of this method will be the input for onBindViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { // put in the data that you want to show on recyclerView
        holder.makerTv.setText(data.get(position).getCar_maker());
        holder.modelTv.setText(data.get(position).getCar_model());
        holder.yearTv.setText(Integer.toString(data.get(position).getCar_year()));
        holder.colorTv.setText(data.get(position).getCar_color());
        holder.seatsTv.setText(Integer.toString(data.get(position).getCar_seats()));
        holder.priceTv.setText(Float.toString(data.get(position).getCar_price()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder { // bind View components to cardView which is going to show on the recyclerView
        public TextView makerTv;
        public TextView modelTv;
        public TextView yearTv;
        public TextView colorTv;
        public TextView seatsTv;
        public TextView priceTv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            makerTv = itemView.findViewById(R.id.maker_id);
            modelTv = itemView.findViewById(R.id.model_id);
            yearTv = itemView.findViewById(R.id.year_id);
            colorTv = itemView.findViewById(R.id.color_id);
            seatsTv = itemView.findViewById(R.id.seats_id);
            priceTv = itemView.findViewById(R.id.price_id);
        }
    }
}
