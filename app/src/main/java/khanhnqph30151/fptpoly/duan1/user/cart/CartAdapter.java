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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    private ArrayList<Cart> list;

    private CartDAO cartDao;
    private FoodDAO foodDao;
    Cart cart;
    int number=0;



    public CartAdapter(Context context, ArrayList<Cart> list, CartDAO cartDao) {
        this.context = context;
        this.list = list;
        this.cartDao = cartDao;
    }

    public void setData(ArrayList<Cart> list) {
        this.list = list;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user_cart, null);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        foodDao = new FoodDAO(context);
        cartDao = new CartDAO(context);
        list = cartDao.getAllData();
        for (Cart cart : list){
            number += cart.getSum();
        }


        cart = list.get(position);
        Food food = foodDao.getById(cart.getIdFood());
        if (food != null) {
            holder.tv_name.setText(food.getName());
            String img = food.getImg();
            Picasso.get().load(img).into(holder.iv_img);
            holder.tv_des.setText(food.getDes());
            holder.tv_price.setText(String.valueOf(food.getPrice()));
        }
        holder.tv_price.setText(food.getPrice()*cart.getQuanti()+"");
        holder.tv_quanti.setText(cart.getQuanti()+"");
        holder.btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart1 = new Cart();
                int quanti=cart.getQuanti();
                quanti += 1;
                holder.tv_quanti.setText("" + quanti);
                holder.tv_price.setText("" + food.getPrice() * quanti);
                cart1.setSum(Double.parseDouble("" + food.getPrice() * quanti));
                cart1.setQuanti(Integer.parseInt("" + quanti));
                cart1.setIdFood(cart.getIdFood());
                cart1.setIdCart(cart.getIdCart());
                if (cartDao.updateSum(cart1) >= 0) {
                    Toast.makeText(context, "Thanh cong hehe", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Ko thanh cong roi huhu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quanti=cart.getQuanti();
                if (quanti > 1) {
                    Cart cart1 = new Cart();
                    quanti -= 1;
                    holder.tv_quanti.setText("" + quanti);
                    holder.tv_price.setText("" + food.getPrice() * quanti);
                    cart1.setSum(Double.parseDouble(holder.tv_price.getText().toString()));
                    cart1.setQuanti(Integer.parseInt(holder.tv_quanti.getText().toString()));
                    cart1.setIdFood(cart.getIdFood());
                    cart1.setIdCart(cart.getIdCart());
                    if (cartDao.updateSum(cart1) > 0) {

                        Toast.makeText(context, "Thanh cong hehe", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Ko thanh cong roi huhu", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    quanti = 1;
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onLongClick(View v) {
                @SuppressLint("RestrictedApi") MenuBuilder builder = new MenuBuilder(context);
                MenuInflater inflater = new MenuInflater(context);
                inflater.inflate(R.menu.menu_popup_delete, builder);
                @SuppressLint("RestrictedApi") MenuPopupHelper optionmenu = new MenuPopupHelper(context, builder, v);
                builder.setCallback(new MenuBuilder.Callback() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                        if (item.getItemId() == R.id.option_delete) {
                            showDele(list.get(position).getIdCart());
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

    public class ViewHolder extends RecyclerView.ViewHolder {
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

    public void showDele(int id) {
        AlertDialog.Builder dialogDL = new AlertDialog.Builder(context);
        dialogDL.setMessage("Bạn có muốn xóa không?");
        dialogDL.setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDL.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CartDAO dao = new CartDAO(context);
                if (dao.delete(id) > 0) {
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    list = dao.getAllData();
                    setData(list);
                } else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();

                }
                dialog.dismiss();

            }
        });
        dialogDL.show();
    }


}
