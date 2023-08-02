package khanhnqph30151.fptpoly.duan1.admin.list_history;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import khanhnqph30151.fptpoly.duan1.R;
import khanhnqph30151.fptpoly.duan1.user.notification.Noti;
import khanhnqph30151.fptpoly.duan1.user.notification.NotiDAO;

public class Dekivering_Adapter extends RecyclerView.Adapter<Dekivering_Adapter.ViewHolder> {
    private ArrayList<invoice> list;
    private ArrayList<invoice> list1;
    private Context context;
    private NotiDAO notiDAO;

    private invoce_DAO invoce_dao;

    public Dekivering_Adapter(ArrayList<invoice> list, Context context) {
        this.list = list;
        this.context = context;
        invoce_dao=new invoce_DAO(context);
        notiDAO=new NotiDAO(context);
    }

    public void setData(ArrayList<invoice> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_invoice, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        invoce_dao = new invoce_DAO(context);
        invoice inv = list.get(position);
        holder.id_cart.setText(String.valueOf(list.get(position).getId_history()));
        holder.phone.setText(String.valueOf(list.get(position).getPhone()));
        holder.name.setText(list.get(position).getName());
        holder.address.setText(list.get(position).getAddress());
        holder.time.setText(list.get(position).getTime());
        holder.sum.setText(String.valueOf(list.get(position).getSum()));
        holder.content.setText(list.get(position).getContten());
        holder.status.setText(list.get(position).getStatus());

        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inv.setStatus("Đã Thanh Toán");
                invoce_dao.update(inv);
                list = invoce_dao.SeLectDangGiao();
                setData(list);
                Calendar calendar = Calendar.getInstance();
                Date currentDate = calendar.getTime();
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                String formattedTime = timeFormat.format(currentDate);
                Noti noti = new Noti();
                noti.setStatus(inv.getStatus());
                noti.setContent(inv.getContten());
                noti.setUser_name(inv.getName());
                noti.setTime(formattedTime);
                if (notiDAO.insert(noti) > 0) {
                    Toast.makeText(context, "okkkk", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "cccc", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id_cart, phone, name, address, sum, time, status, content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_cart = itemView.findViewById(R.id.id_cart);
            phone = itemView.findViewById(R.id.id_phone);
            name = itemView.findViewById(R.id.id_hoten);
            address = itemView.findViewById(R.id.id_address);
            sum = itemView.findViewById(R.id.id_sum);
            time = itemView.findViewById(R.id.id_time);
            status = itemView.findViewById(R.id.status);
            content = itemView.findViewById(R.id.id_noidung);

        }
    }
}
