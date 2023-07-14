package khanhnqph30151.fptpoly.duan1.admin.statis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import khanhnqph30151.fptpoly.duan1.R;



public class StatisFragment extends Fragment {



    public StatisFragment() {
        // Required empty public constructor
    }


    public static StatisFragment newInstance() {
        StatisFragment fragment = new StatisFragment();


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
        return inflater.inflate(R.layout.fragment_admin_statis, container, false);
    }
}