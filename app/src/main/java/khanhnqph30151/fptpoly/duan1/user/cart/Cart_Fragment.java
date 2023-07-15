package khanhnqph30151.fptpoly.duan1.user.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.R;
import khanhnqph30151.fptpoly.duan1.admin.food.Food;
import khanhnqph30151.fptpoly.duan1.admin.food.FoodAdapter;
import khanhnqph30151.fptpoly.duan1.admin.food.FoodDAO;
import khanhnqph30151.fptpoly.duan1.user.history.History_DAO;


public class Cart_Fragment extends Fragment {
    RecyclerView recyclerView;
    CartDAO cartDAO;
    ArrayList<Cart> listCart;
    CartAdapter adapter;

    History_DAO historyDao;





    public Cart_Fragment() {

    }

    public static Cart_Fragment newInstance() {
        Cart_Fragment fragment = new Cart_Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_, container, false);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recy_fragment_cart_listFood);
        TextView tv_sumPrice = view.findViewById(R.id.tv_fragment_cart_sumPrice);

        cartDAO = new CartDAO(getActivity());
        historyDao = new History_DAO(getContext());
        int sum=getActivity().getIntent().getIntExtra("sum",0);
        tv_sumPrice.setText(""+sum);

        reloadData();
    }
    private void reloadData(){

        cartDAO = new CartDAO(getActivity());
        listCart = cartDAO.getAllData();
        adapter = new CartAdapter(getContext(),listCart,cartDAO);
        adapter.setData(listCart);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    }

}