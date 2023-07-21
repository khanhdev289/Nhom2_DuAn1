package khanhnqph30151.fptpoly.duan1.admin.list_history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.R;
import khanhnqph30151.fptpoly.duan1.user.history.History_Adapter;
import khanhnqph30151.fptpoly.duan1.user.history.History_DAO;
import khanhnqph30151.fptpoly.duan1.user.history.History_model;


public class ListHistoryFragment extends Fragment {

    private invoce_DAO dao;
    private ArrayList<invoice> list;
    private ArrayList<invoice> list1;

    private Invoice_Adapter adapter;
    RecyclerView recyclerView;


    public ListHistoryFragment() {
        // Required empty public constructor
    }


    public static ListHistoryFragment newInstance() {
        ListHistoryFragment fragment = new ListHistoryFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_list_history, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView =view.findViewById(R.id.invoice_ry);
        invoce_DAO dao = new invoce_DAO(getContext());
        list = dao.SeLectDaDatHang();
        adapter = new Invoice_Adapter(list, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}