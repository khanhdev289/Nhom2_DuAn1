package khanhnqph30151.fptpoly.duan1.user.history;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import khanhnqph30151.fptpoly.duan1.R;
import khanhnqph30151.fptpoly.duan1.admin.list_history.QuanLy_Status_Adapter;


public class History_QuanLyFragment extends Fragment {

    private TabLayout tabLayoutstatus;
    private ViewPager2 vpstatus;
    private History_QuanLy_Adapter history_quanLy_adapter;

    public History_QuanLyFragment() {
        // Required empty public constructor
    }


    public static History_QuanLyFragment newInstance(String param1, String param2) {
        History_QuanLyFragment fragment = new History_QuanLyFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayoutstatus = view.findViewById(R.id.tab_layout_Hitory);
        vpstatus = view.findViewById(R.id.vp_Hitory);
        history_quanLy_adapter = new History_QuanLy_Adapter(getActivity());
        vpstatus.setAdapter(history_quanLy_adapter);
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
        return inflater.inflate(R.layout.fragment_history__quan_ly, container, false);
    }
}