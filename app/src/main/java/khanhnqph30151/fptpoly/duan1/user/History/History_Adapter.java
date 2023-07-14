package khanhnqph30151.fptpoly.duan1.user.History;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.R;

public class History_Adapter extends RecyclerView.Adapter<History_Adapter.ViewHolder> {
    private ArrayList<History_model> list;
    private Context context;

    private History_DAO history_dao;
    public History_Adapter(ArrayList<History_model> list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_history_user, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id_cart.setText(String.valueOf(list.get(position).getId_cart()));
        holder.phone.setText(list.get(position).getPhone());
        holder.name.setText(list.get(position).getName());
        holder.address.setText(list.get(position).getAddress());
        holder.time.setText(list.get(position).getTime());
        holder.sum.setText(String.valueOf(list.get(position).getSum()) );

    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id_cart, phone, name,address,sum,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_cart =itemView.findViewById(R.id.id_cart);
            phone =itemView.findViewById(R.id.id_phone);
            name =itemView.findViewById(R.id.id_hoten);
            address =itemView.findViewById(R.id.id_address);
            sum =itemView.findViewById(R.id.id_sum);
            time =itemView.findViewById(R.id.id_time);
        }
    }
}
