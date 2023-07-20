package khanhnqph30151.fptpoly.duan1.admin.list_request;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.R;
import khanhnqph30151.fptpoly.duan1.admin.food.Food;
import khanhnqph30151.fptpoly.duan1.admin.food.FoodAdapter;
import khanhnqph30151.fptpoly.duan1.admin.food.FoodDAO;
import khanhnqph30151.fptpoly.duan1.user.request.DAO;
import khanhnqph30151.fptpoly.duan1.user.request.Request;

public class ListReQuest_Adapter extends RecyclerView.Adapter<ListReQuest_Adapter.ViewHolder> {
    Context context;
    private ArrayList<khanhnqph30151.fptpoly.duan1.user.request.Request> list;
    private DAO dao;

    public ListReQuest_Adapter(Context context, ArrayList<khanhnqph30151.fptpoly.duan1.user.request.Request> list, DAO dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    public void setData(ArrayList<Request> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_admin_request, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_name.setText(list.get(position).getTen());
        holder.tv_sdt.setText(list.get(position).getSodienthoai());
        holder.tv_email.setText(list.get(position).getEmail());
        holder.tv_conten.setText(list.get(position).getNoidung());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name, tv_sdt, tv_email,tv_conten;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.admin_request_name);
            tv_sdt = itemView.findViewById(R.id.admin_request_phone);
            tv_email = itemView.findViewById(R.id.admin_request_email);
            tv_conten = itemView.findViewById(R.id.admin_request_content);
        }
    }
}
