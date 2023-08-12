package khanhnqph30151.fptpoly.duan1.admin.list_history;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

import khanhnqph30151.fptpoly.duan1.R;


public class DangCBFragment extends Fragment {

    private Invoce_DAO dao;
    private ArrayList<Invoice> list;

    private DangChuanBiHangAdapter adapter;
    RecyclerView recyclerView;

    public DangCBFragment() {
        // Required empty public constructor
    }


    public static DangCBFragment newInstance(String param1, String param2) {
        DangCBFragment fragment = new DangCBFragment();

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
        return inflater.inflate(R.layout.fragment_dang_c_b, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView =view.findViewById(R.id.invoice_ry_danghuanbi);
        Invoce_DAO dao = new Invoce_DAO(getContext());
        list = dao.SeLectDangchuanbi();
        Collections.reverse(list);
        adapter = new DangChuanBiHangAdapter(list, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    }
    @Override
    public void onResume() {
        super.onResume();
        Invoce_DAO dao = new Invoce_DAO(getContext());
        list = dao.SeLectDangchuanbi();
        Collections.reverse(list);
        adapter = new DangChuanBiHangAdapter(list, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

}