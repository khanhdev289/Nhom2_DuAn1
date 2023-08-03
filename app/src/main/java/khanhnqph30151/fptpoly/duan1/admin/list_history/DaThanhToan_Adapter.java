package khanhnqph30151.fptpoly.duan1.admin.list_history;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import khanhnqph30151.fptpoly.duan1.R;
import khanhnqph30151.fptpoly.duan1.user.history.ItemInforHistory;
import khanhnqph30151.fptpoly.duan1.user.notification.Noti;
import khanhnqph30151.fptpoly.duan1.user.notification.NotiDAO;

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
//        holder.id_cart.setText(String.valueOf(inv.getId_history()));
        holder.phone.setText(String.valueOf(inv.getPhone()));
        holder.name.setText(inv.getName());
        holder.address.setText(inv.getAddress());
        holder.time.setText(inv.getTime());
        holder.sum.setText(String.valueOf(inv.getSum()));
//        holder.content.setText(inv.getContten());
        holder.status.setText(inv.getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ItemInforHistory.class);
                i.putExtra("id_cart",list.get(position).getId_history());
                i.putExtra("phone", list.get(position).getPhone());
                i.putExtra("name", list.get(position).getName());
                i.putExtra("address", list.get(position).getAddress());
                i.putExtra("time", list.get(position).getTime());
                i.putExtra("sum",(list.get(position).getSum()));
                i.putExtra("content",list.get(position).getContten());
                i.putExtra("status",list.get(position).getStatus());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id_cart, phone, name,address,sum,time,status,content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            id_cart =itemView.findViewById(R.id.id_cart);
            phone =itemView.findViewById(R.id.id_phone);
            name =itemView.findViewById(R.id.id_hoten);
            address =itemView.findViewById(R.id.id_address);
            sum =itemView.findViewById(R.id.id_sum);
            time =itemView.findViewById(R.id.id_time);
            status=itemView.findViewById(R.id.status);
//            content=itemView.findViewById(R.id.id_noidung);

        }
    }
}
