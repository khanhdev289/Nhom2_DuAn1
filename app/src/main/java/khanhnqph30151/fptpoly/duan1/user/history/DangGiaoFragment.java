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

import khanhnqph30151.fptpoly.duan1.R;

public class DangGiaoFragment extends Fragment {

    private History_DAO dao;
    private ArrayList<History_model> list;

    private History_Adapter adapter;
    RecyclerView recyclerView;

    public DangGiaoFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DangGiaoFragment newInstance(String param1, String param2) {
        DangGiaoFragment fragment = new DangGiaoFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_giao, container, false);
        recyclerView =view.findViewById(R.id.history_danggiao);
        reloadData();
        return view;

    }
    public void reloadData(){
        dao = new History_DAO(getActivity());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String loggedInUserName = sharedPreferences.getString("USERNAME", "");
        list = dao.SeLectUESeDangGiao(loggedInUserName);
        adapter = new History_Adapter(list, getContext(), dao);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    }
}