package khanhnqph30151.fptpoly.duan1.user.cart;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import khanhnqph30151.fptpoly.duan1.ConfirmCart;
import khanhnqph30151.fptpoly.duan1.R;
import khanhnqph30151.fptpoly.duan1.admin.food.Food;
import khanhnqph30151.fptpoly.duan1.admin.food.FoodAdapter;
import khanhnqph30151.fptpoly.duan1.admin.food.FoodDAO;
import khanhnqph30151.fptpoly.duan1.setting.User;
import khanhnqph30151.fptpoly.duan1.setting.UserDAO;
import khanhnqph30151.fptpoly.duan1.user.history.History_Adapter;
import khanhnqph30151.fptpoly.duan1.user.history.History_DAO;
import khanhnqph30151.fptpoly.duan1.user.history.History_model;


public class Cart_Fragment extends Fragment implements CartAdapter.OnQuantityUpClickListener, CartAdapter.OnQuantityDownClickListener {
    RecyclerView recyclerView;
    CartDAO cartDAO;
    FoodDAO foodDAO;
    UserDAO userDAO;
    ArrayList<Cart> listCart;
    ArrayList<Food> listFood;
    ArrayList<History_model> listHis;
    ArrayList<User> listUser;
    CartAdapter adapter;
    History_Adapter adapterHis;
    TextView tv_sumPrice;
    Button btn_confirm;
    History_DAO historyDao;
    SharedPreferences sharedPreferences;


    public Cart_Fragment() {
    }

    public static Cart_Fragment newInstance() {
        Cart_Fragment fragment = new Cart_Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_, container, false);
        recyclerView = view.findViewById(R.id.recy_fragment_cart_listFood);
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String InUsername1 = sharedPreferences.getString("USERNAME", "");
        cartDAO=new CartDAO(getContext());
        listCart = cartDAO.getByUser(InUsername1);
        tv_sumPrice = view.findViewById(R.id.tv_fragment_cart_sumPrice);
        btn_confirm = view.findViewById(R.id.btn_fragment_cart_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogConfirm();
            }
        });

        reloadData();

        updateTotalSum();

        return view;
    }

    private void reloadData() {
        cartDAO = new CartDAO(getActivity());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String loggedInUserName = sharedPreferences.getString("USERNAME", "");
        listCart = cartDAO.getByUser(loggedInUserName);
        adapter = new CartAdapter(getContext(), listCart, cartDAO);
        adapter.setOnQuantityUpClickListener(this);
        adapter.setOnQuantityDownClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    }

    @Override
    public void onQuantityUpClick(int position) {
        updateTotalSum();
    }

    @Override
    public void onQuantityDownClick(int position) {
        updateTotalSum();
    }

    private void updateTotalSum() {
        int totalSum = calculateTotalSum();
        tv_sumPrice.setText(String.valueOf(totalSum)+" VND");
    }

    private int calculateTotalSum() {
        int totalSum = 0;
        for (Cart cart : listCart) {
            totalSum += cart.getSum();
        }
        return totalSum;
    }

    public void dialogConfirm() {
        Dialog dialog = new Dialog(getContext());
        History_model history = new History_model();

        cartDAO = new CartDAO(getActivity());
        foodDAO = new FoodDAO(getActivity());
        userDAO = new UserDAO(getActivity());
        historyDao = new History_DAO(getActivity());

        dialog.setContentView(R.layout.dialog_confirm_invoice);

        EditText ed_address, ed_phone;
        TextView tvDateTime, tvInvSum, tvContent, tvUsername;
        Button btnDialogAddCancel, btnDialogAddSubmit;
        ed_address = dialog.findViewById(R.id.ed_dialog_invoice_confirm_address);
        ed_phone = dialog.findViewById(R.id.ed_dialog_invoice_confirm_phone);

        tvUsername = dialog.findViewById(R.id.tv_dialog_invoice_confirm_user);
        tvDateTime = dialog.findViewById(R.id.tv_dialog_invoice_confirm_date);
        tvInvSum = dialog.findViewById(R.id.tv_dialog_invoice_confirm_priceSum);
        tvContent = dialog.findViewById(R.id.tv_dialog_invoice_confirm_content);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String loggedInUserName = sharedPreferences.getString("USERNAME", "");

        listUser = userDAO.getUsersByName(loggedInUserName);
        for (User user : listUser) {
            if (user.getUser_name().equals(loggedInUserName)) {
                loggedInUserName = user.getUser_name();
                break;
            }
        }

        tvUsername.setText(loggedInUserName);

        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        String formattedDate = dateFormat.format(currentDate);
        String formattedTime = timeFormat.format(currentDate);

        tvDateTime.setText(formattedTime + " " + formattedDate);
        double totalSum = calculateTotalSum();
        tvInvSum.setText(String.valueOf(totalSum));

        String InUsername2 = sharedPreferences.getString("USERNAME", "");
        listCart = cartDAO.getByUser(InUsername2);
        listFood = foodDAO.getAllData();
        String cartData = "";
        for (Cart cart : listCart) {
            for (Food food : listFood) {
                if (food.getId() == cart.getIdFood()) {
                    cartData += "- " + food.getName() + " (" + cart.getSum() + " VNĐ)"+ ", Số Lượng: " + cart.getQuanti() + "\n";
                    break;
                }
            }
        }
        String content = cartData;
        tvContent.setText(content);

        btnDialogAddSubmit = dialog.findViewById(R.id.btn_dialog_invoice_add_add);

        btnDialogAddSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addrs = ed_address.getText().toString();
                String phoneString = ed_phone.getText().toString();
                String dateTime = tvDateTime.getText().toString();
                String content = tvContent.getText().toString();


                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String name= sharedPreferences.getString("USERNAME", "");


                if (addrs.trim().isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (phoneString.trim().isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                } else if (content.isEmpty()){
                    Toast.makeText(getContext(), "Hãy chọn món ăn trước khi đặt hàng", Toast.LENGTH_SHORT).show();
                }else {
                    int phone = 0;
                    try {
                        phone = Integer.parseInt(phoneString);
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Số điện thoại phải là một số", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    history.setName(name);
                    history.setAddress(addrs);
                    history.setPhone(phone);
                    history.setTime(dateTime);
                    history.setSum(calculateTotalSum());
                    history.setContten(content);
                    history.setStatus("Đã Đặt Hàng");


                    if (historyDao.insert(history) >= 0) {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                        cartDAO.DelCart(name);
                        listCart=cartDAO.getAllData();
                        adapter.setData(listCart);
                        dialog.dismiss();
                        Intent i = new Intent(getContext(), ConfirmCart.class);
                        i.putExtra("address", history.getAddress());
                        getActivity().startActivity(i);
                        updateTotalSum();
                    } else {
                        Toast.makeText(getContext(), "Thêm thất bại!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}