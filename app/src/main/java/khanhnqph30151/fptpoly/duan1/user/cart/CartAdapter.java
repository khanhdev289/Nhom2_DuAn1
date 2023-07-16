package khanhnqph30151.fptpoly.duan1.user.cart;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.R;
import khanhnqph30151.fptpoly.duan1.admin.food.Food;
import khanhnqph30151.fptpoly.duan1.admin.food.FoodAdapter;
import khanhnqph30151.fptpoly.duan1.admin.food.FoodDAO;
import khanhnqph30151.fptpoly.duan1.user.home.Home;
import khanhnqph30151.fptpoly.duan1.user.home.HomeDAO;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Cart> list;
    private CartDAO cartDao;
    private FoodDAO foodDao;

    private OnQuantityUpClickListener quantityUpClickListener;
    private OnQuantityDownClickListener quantityDownClickListener;

    public CartAdapter(Context context, ArrayList<Cart> list, CartDAO cartDao) {
        this.context = context;
        this.list = list;
        this.cartDao = cartDao;
        this.foodDao = new FoodDAO(context);
    }

    public void setData(ArrayList<Cart> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnQuantityUpClickListener(OnQuantityUpClickListener listener) {
        this.quantityUpClickListener = listener;
    }

    public void setOnQuantityDownClickListener(OnQuantityDownClickListener listener) {
        this.quantityDownClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart cart = list.get(position);
        Food food = foodDao.getById(cart.getIdFood());

        if (food != null) {
            holder.tv_name.setText(food.getName());
            Picasso.get().load(food.getImg()).into(holder.iv_img);
            holder.tv_des.setText(food.getDes());
            holder.tv_price.setText(String.valueOf(food.getPrice()));
        }

        holder.tv_price.setText(String.valueOf(food.getPrice() * cart.getQuanti()));
        holder.tv_quanti.setText(String.valueOf(cart.getQuanti()));

        holder.btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quanti = cart.getQuanti() + 1;
                holder.tv_quanti.setText(String.valueOf(quanti));
                holder.tv_price.setText(String.valueOf(food.getPrice() * quanti));
                cart.setQuanti(quanti);
                cart.setSum(food.getPrice() * quanti);
                cartDao.updateSum(cart);

                if (quantityUpClickListener != null) {
                    quantityUpClickListener.onQuantityUpClick(holder.getAdapterPosition());
                }
            }
        });

        holder.btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quanti = cart.getQuanti();

                if (quanti > 1) {
                    quanti -= 1;
                    holder.tv_quanti.setText(String.valueOf(quanti));
                    holder.tv_price.setText(String.valueOf(food.getPrice() * quanti));
                    cart.setQuanti(quanti);
                    cart.setSum(food.getPrice() * quanti);
                    cartDao.updateSum(cart);

                    if (quantityDownClickListener != null) {
                        quantityDownClickListener.onQuantityDownClick(holder.getAdapterPosition());
                    }
                } else {
                    Toast.makeText(context, "Số lượng không thể nhỏ hơn 1", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_img;
        TextView tv_name, tv_des, tv_price, tv_quanti;
        ImageButton btn_up, btn_down;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_item_cart_foodImg);
            tv_name = itemView.findViewById(R.id.tv_item_cart_foodName);
            tv_des = itemView.findViewById(R.id.tv_item_cart_foodContent);
            tv_price = itemView.findViewById(R.id.tv_item_cart_foodPrice);
            tv_quanti = itemView.findViewById(R.id.tv_item_cart_quantity);
            btn_up = itemView.findViewById(R.id.btn_item_cart_quantity_up);
            btn_down = itemView.findViewById(R.id.btn_item_cart_quantity_down);
        }
    }

    public interface OnQuantityUpClickListener {
        void onQuantityUpClick(int position);
    }

    public interface OnQuantityDownClickListener {
        void onQuantityDownClick(int position);
    }

}
