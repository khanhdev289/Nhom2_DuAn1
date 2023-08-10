package khanhnqph30151.fptpoly.duan1.admin.list_history;

import android.annotation.SuppressLint;
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


public class Invoice_Adapter extends RecyclerView.Adapter<Invoice_Adapter.ViewHolder>{
    private ArrayList<Invoice> list;
    private Context context;

    private Invoce_DAO invoce_dao;

    private NotiDAO notiDAO;

    public Invoice_Adapter(ArrayList<Invoice> list, Context context){
        this.list = list;
        this.context = context;
        notiDAO=new NotiDAO(context);
    }
    public void setData(ArrayList<Invoice> list){
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
    public void onBindViewHolder(@NonNull ViewHolder holder,@SuppressLint("RecyclerView") int position) {
        Invoice inv;
        invoce_dao = new Invoce_DAO(context);
        inv=list.get(position);
//        holder.id_cart.setText(String.valueOf(list.get(position).getId_history()));

        holder.name.setText(list.get(position).getName());

        holder.time.setText(list.get(position).getTime());
        holder.sum.setText(String.format("%.0f",list.get(position).getSum())+ " VND");
        holder.content.setText(list.get(position).getContten());
        holder.status.setText(list.get(position).getStatus());

        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.status.setText("Đang Chuẩn Bị Hàng");
                inv.setStatus("Đang Chuẩn Bị Hàng");
                invoce_dao.update(inv);
                list=invoce_dao.SeLectDaDatHang();
                setData(list);

                Calendar calendar = Calendar.getInstance();
                Date currentDate = calendar.getTime();

                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                String formattedTime = timeFormat.format(currentDate);
                Noti noti=new Noti();
                noti.setStatus(inv.getStatus());
                noti.setContent(inv.getContten());
                noti.setUser_name(inv.getName());
                noti.setTime(formattedTime);
                if(notiDAO.insert(noti)>0){
                    Toast.makeText(context, "Đang Chuẩn Bị Hàng", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "thay đổi thát bại ", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
            name =itemView.findViewById(R.id.id_hoten);
            sum =itemView.findViewById(R.id.id_sum);
            time =itemView.findViewById(R.id.id_time);
            status=itemView.findViewById(R.id.status);
          content=itemView.findViewById(R.id.id_content);

        }
    }

}
