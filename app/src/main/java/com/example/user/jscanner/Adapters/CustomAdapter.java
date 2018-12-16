package com.example.user.jscanner.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.jscanner.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    String code;

    public CustomAdapter(String code){
        this.code = code;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.card_view, parent, false);

        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.code.setText(code);
        switch (position){
            default :
            case 0 :
                holder.host.setText("Datakick");
                break;
            case 1 :
                holder.host.setText("Mashape");
                break;
        }

        switch (code){
            case "9002490100070":
                holder.brand.setText("Red Bull");
                holder.name.setText(position == 0 ? "Energy drink 250ml" : "Drink vol. 0.25");
                break;
            case "7622210309358":
                holder.brand.setText("Oreo");
                holder.name.setText(position == 0 ? "Cookies" : "Chocolate cookies with milk");
                break;
            case "4600949140018":
                holder.brand.setText("Чудо");
                holder.name.setText(position == 0 ? "Молочный напиток 0.2л" : "Шоколадный коктейль");
                break;
            default :
                holder.brand.setText("");
                holder.name.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        TextView host, brand, name, code;

        public CustomViewHolder(View itemView) {
            super(itemView);
            host = itemView.findViewById(R.id.cv_host);
            brand = itemView.findViewById(R.id.cv_brand);
            name = itemView.findViewById(R.id.cv_name);
            code = itemView.findViewById(R.id.cv_code);
        }
    }
}
