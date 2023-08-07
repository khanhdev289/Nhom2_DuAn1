package khanhnqph30151.fptpoly.duan1.admin.list_request;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.R;
import khanhnqph30151.fptpoly.duan1.admin.list_history.Invoice_Adapter;
import khanhnqph30151.fptpoly.duan1.admin.list_history.invoce_DAO;
import khanhnqph30151.fptpoly.duan1.admin.list_history.invoice;
import khanhnqph30151.fptpoly.duan1.user.request.DAO;
import khanhnqph30151.fptpoly.duan1.user.request.Request;


public class ListRequestFragment extends Fragment {
    private DAO dao;
    private ArrayList<Request> list;

    private ListReQuest_Adapter adapter;
    RecyclerView recyclerView;



    public ListRequestFragment() {
        // Required empty public constructor
    }


    public static ListRequestFragment newInstance(String param1, String param2) {
        ListRequestFragment fragment = new ListRequestFragment();

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
        return inflater.inflate(R.layout.fragment_admin_list_request, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView =view.findViewById(R.id.list_frag_admin_request);


        reloadData();
    }
    private void reloadData(){
        dao = new DAO(getContext());
        list = dao.getAllData();
        adapter = new ListReQuest_Adapter( getContext(),list,dao);
        adapter.setData(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    }
}