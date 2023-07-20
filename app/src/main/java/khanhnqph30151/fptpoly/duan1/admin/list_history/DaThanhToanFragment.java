package khanhnqph30151.fptpoly.duan1.admin.list_history;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.R;


public class DaThanhToanFragment extends Fragment {
    private invoce_DAO dao;
    private ArrayList<invoice> list;

    private DaThanhToan_Adapter adapter;
    RecyclerView recyclerView;

    public DaThanhToanFragment() {
        // Required empty public constructor
    }


    public static DaThanhToanFragment newInstance() {
        DaThanhToanFragment fragment = new DaThanhToanFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView =view.findViewById(R.id.invoice_ry_da_thanh_toan);
        invoce_DAO dao = new invoce_DAO(getContext());
        list = dao.SeLectDaThanhToan();
        adapter = new DaThanhToan_Adapter(list, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_da_thanh_toan, container, false);
    }
}