package khanhnqph30151.fptpoly.duan1.admin.list_history;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import khanhnqph30151.fptpoly.duan1.R;


public class Quanly_StatusFragment extends Fragment {


    private TabLayout tabLayoutstatus;
    private ViewPager2 vpstatus;
    private QuanLy_Status_Adapter quanLy_status_adapter;
    public Quanly_StatusFragment() {
        // Required empty public constructor
    }

    public static Quanly_StatusFragment newInstance(String param1, String param2) {
        Quanly_StatusFragment fragment = new Quanly_StatusFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayoutstatus = view.findViewById(R.id.tab_layout_status);
        vpstatus = view.findViewById(R.id.vp_status);
        quanLy_status_adapter = new QuanLy_Status_Adapter(getActivity());
        vpstatus.setAdapter(quanLy_status_adapter);
        new TabLayoutMediator(tabLayoutstatus, vpstatus, ((tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Đã Đặt Hàng");
                    break;
                case 1:
                    tab.setText("Đang Giao");
                    break;
                case 2:
                    tab.setText("Đã Thanh Toán");

                    break;
            }
        })).attach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quanly__status, container, false);
    }
}