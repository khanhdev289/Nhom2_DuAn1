package khanhnqph30151.fptpoly.duan1.user.history;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

import khanhnqph30151.fptpoly.duan1.R;

public class DangChuanBiHangFragment extends Fragment {

    private History_DAO dao;
    private ArrayList<History_model> list;

    private History_Adapter adapter;
    RecyclerView recyclerView;


    public DangChuanBiHangFragment() {
        // Required empty public constructor
    }


    public static DangChuanBiHangFragment newInstance(String param1, String param2) {
        DangChuanBiHangFragment fragment = new DangChuanBiHangFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_chuan_bi_hang, container, false);
        recyclerView =view.findViewById(R.id.history_dangcb);
        reloadData();
        return view;

    }
    public void reloadData(){
        dao = new History_DAO(getActivity());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String loggedInUserName = sharedPreferences.getString("USERNAME", "");
        list = dao.SeLectUESeDangCB(loggedInUserName);
        Collections.reverse(list);
        adapter = new History_Adapter(list, getContext(), dao);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    }

}