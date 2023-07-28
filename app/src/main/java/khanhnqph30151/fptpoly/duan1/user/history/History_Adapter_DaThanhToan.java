package khanhnqph30151.fptpoly.duan1.user.history;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import android.content.SharedPreferences;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.R;
import khanhnqph30151.fptpoly.duan1.admin.food.FoodDAO;
import khanhnqph30151.fptpoly.duan1.user.home.comment.Comment;
import khanhnqph30151.fptpoly.duan1.user.home.comment.CommentDAO;
import khanhnqph30151.fptpoly.duan1.user.home.comment.RatingStarView;

public class History_Adapter_DaThanhToan extends RecyclerView.Adapter<History_Adapter_DaThanhToan.ViewHolder> {
    private ArrayList<History_model> list;
    private Context context;

    private History_DAO history_dao;
    CommentDAO commentDAO;
    FoodDAO foodDAO;

    public History_Adapter_DaThanhToan(ArrayList<History_model> list, Context context, History_DAO history_dao) {
        this.list = list;
        this.context = context;
        this.history_dao = history_dao;
        commentDAO=new CommentDAO(context);
        foodDAO=new FoodDAO(context);
    }

    public void setData(ArrayList<History_model> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_history_user_danhgia, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        History_model history=list.get(position);
        holder.id_cart.setText(String.valueOf(list.get(position).getId_history()));
        holder.phone.setText(String.valueOf(list.get(position).getPhone()));
        holder.name.setText(list.get(position).getName());
        holder.address.setText(list.get(position).getAddress());
        holder.time.setText(list.get(position).getTime());
        holder.sum.setText(String.valueOf(list.get(position).getSum()));
        holder.conten.setText(list.get(position).getContten());
        holder.status.setText(list.get(position).getStatus());
        history_dao = new History_DAO(context);
        History_model inv = list.get(position);
        if (inv.getStatus().equals("Đã Đặt Hàng")) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        showDele(list.get(position).getId_history());
                }
            });
        }
        holder.btn_cmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_cmt);
                ArrayList<String> lst_foodName=new ArrayList<>();
                ArrayList<Integer> lst_foodID=new ArrayList<>();
                RatingStarView ratingStarView=dialog.findViewById(R.id.rating);
                EditText ed_cmt=dialog.findViewById(R.id.ed_cmt);
                Button btn_send_cmt=dialog.findViewById(R.id.btn_send_cmt);
                TextView tv_start=dialog.findViewById(R.id.tv_rating);
                String content=history.getContten();
                char startChar = '-';
                char endChar = '(';

                for (int i = 0; i < content.length(); i++) {
                    if (content.charAt(i) == startChar) {
                        for (int j = i+1; j < content.length(); j++) {
                            if (content.charAt(j) == endChar) {
                                String substring = content.substring(i+1,j);
                                lst_foodName.add(substring);
                            }

                        }
                    }
                }
                for(String foodName: lst_foodName){
                 int id_food= foodDAO.getbyName(foodName);
                 if(id_food>=0){
                     lst_foodID.add(id_food);
                 }
                }
                btn_send_cmt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String cmt=ed_cmt.getText().toString();
                        int rating=ratingStarView.getRating();
                        for(int id_food: lst_foodID){
                            Comment comment=new Comment();
                            comment.setFood_id(id_food);
                            comment.setUser_name(list.get(position).getName());
                            comment.setRating(rating);
                            comment.setComment_content(cmt);
                            if(commentDAO.insert(comment)>0){
                                Toast.makeText(context, "Cảm ơn bạn đã đánh giá ", Toast.LENGTH_SHORT).show();
                            } else{
                                Toast.makeText(context, "Chưa ổn r ", Toast.LENGTH_SHORT).show();
                            }
                        }

                        dialog.dismiss();

                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id_cart, phone, name,address,sum,time,conten,status;
        Button btn_cmt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_cart =itemView.findViewById(R.id.id_cart);
            phone =itemView.findViewById(R.id.id_phone);
            name =itemView.findViewById(R.id.id_hoten);
            address =itemView.findViewById(R.id.id_address);
            sum =itemView.findViewById(R.id.id_sum);
            time =itemView.findViewById(R.id.id_time);
            conten=itemView.findViewById(R.id.id_noidung);
            status=itemView.findViewById(R.id.history_status);
            btn_cmt=itemView.findViewById(R.id.btn_cmt);

        }
    }
    public void showDele(int id){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_item_delete_invoice);

        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AppCompatButton btnSubmit, btnCancel;
        btnSubmit = dialog.findViewById(R.id.btn_dialog_item_delete_submit);
        btnCancel = dialog.findViewById(R.id.btn_dialog_item_delete_cancel);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                History_DAO dao = new History_DAO(context);
                if (dao.delete(id) > 0) {
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = dialog.getContext().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                    String loggedInUserName = sharedPreferences.getString("USERNAME", "");
                    list = dao.getByUser(loggedInUserName);
                    setData(list);
                } else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();

                }
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

}