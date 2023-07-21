package khanhnqph30151.fptpoly.duan1.admin.list_history;

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

public class DaThanhToan_Adapter  extends RecyclerView.Adapter<DaThanhToan_Adapter.ViewHolder>{
    private ArrayList<invoice> list;
    private Context context;


    private invoce_DAO invoce_dao;

    public DaThanhToan_Adapter(ArrayList<invoice> list, Context context){
        this.list = list;
        this.context = context;
    }
    public void setData(ArrayList<invoice> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_invoice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        invoice inv;
        inv=list.get(position);
        holder.id_cart.setText(String.valueOf(inv.getId_history()));
        holder.phone.setText(String.valueOf(inv.getPhone()));
        holder.name.setText(inv.getName());
        holder.address.setText(inv.getAddress());
        holder.time.setText(inv.getTime());
        holder.sum.setText(String.valueOf(inv.getSum()));
        holder.content.setText(inv.getContten());
        holder.status.setText(inv.getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id_cart, phone, name,address,sum,time,status,content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_cart =itemView.findViewById(R.id.id_cart);
            phone =itemView.findViewById(R.id.id_phone);
            name =itemView.findViewById(R.id.id_hoten);
            address =itemView.findViewById(R.id.id_address);
            sum =itemView.findViewById(R.id.id_sum);
            time =itemView.findViewById(R.id.id_time);
            status=itemView.findViewById(R.id.status);
            content=itemView.findViewById(R.id.id_noidung);

        }
    }
}
