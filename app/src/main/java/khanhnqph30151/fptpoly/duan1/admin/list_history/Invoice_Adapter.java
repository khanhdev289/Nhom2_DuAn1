package khanhnqph30151.fptpoly.duan1.admin.list_history;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.R;


public class Invoice_Adapter extends RecyclerView.Adapter<Invoice_Adapter.ViewHolder>{
    private ArrayList<invoice> list;
    private Context context;

    private invoce_DAO invoce_dao;
    public Invoice_Adapter(ArrayList<invoice> list, Context context){
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
    public void onBindViewHolder(@NonNull ViewHolder holder,@SuppressLint("RecyclerView") int position) {
        holder.id_cart.setText(String.valueOf(list.get(position).getId_cart()));
        holder.phone.setText(list.get(position).getPhone());
        holder.name.setText(list.get(position).getName());
        holder.address.setText(list.get(position).getAddress());
        holder.time.setText(list.get(position).getTime());
        holder.sum.setText(String.valueOf(list.get(position).getSum()) );
        holder.content.setText(list.get(position).getContent());
        holder.status.setText(list.get(position).getstatus());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onLongClick(View v) {
                @SuppressLint("RestrictedApi") MenuBuilder builder = new MenuBuilder(context);
                MenuInflater inflater = new MenuInflater(context);
                inflater.inflate(R.menu.menu_status_invoice, builder);
                @SuppressLint("RestrictedApi") MenuPopupHelper optionmenu = new MenuPopupHelper(context, builder, v);
                builder.setCallback(new MenuBuilder.Callback() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                        if (item.getItemId() == R.id.option_ThanhToan) {
                            updateDia(list.get(position), position);
                            return true;
                        } else {
                            return false;
                        }
                    }

                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onMenuModeChange(@NonNull MenuBuilder menu) {

                    }
                });
                optionmenu.show();
                return true;
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
            id_cart =itemView.findViewById(R.id.id_cart);
            phone =itemView.findViewById(R.id.id_phone);
            name =itemView.findViewById(R.id.id_hoten);
            address =itemView.findViewById(R.id.id_address);
            sum =itemView.findViewById(R.id.id_sum);
            time =itemView.findViewById(R.id.id_time);
            status=itemView.findViewById(R.id.status);
            content=itemView.findViewById(R.id.id_noidung);        }
    }
    public void updateDia(invoice invoice, int id){
        AlertDialog.Builder dialogDL = new AlertDialog.Builder(context);
        dialogDL.setMessage("Bạn có muốn thay đổi  không?");
        dialogDL.setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDL.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                invoce_DAO dao = new invoce_DAO(context);

                    Toast.makeText(context, "updete Thành Công", Toast.LENGTH_SHORT).show();
                    list = dao.getAllData();
                    setData(list);

                dialog.dismiss();

            }
        });
        dialogDL.show();
    }
}
