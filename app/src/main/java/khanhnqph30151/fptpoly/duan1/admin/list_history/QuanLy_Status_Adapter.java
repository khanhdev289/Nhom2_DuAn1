package khanhnqph30151.fptpoly.duan1.admin.list_history;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class QuanLy_Status_Adapter extends FragmentStateAdapter {


    public QuanLy_Status_Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ListHistoryFragment();
            case 1:
                return new DeliveringFragment();
            case 2:
                return new DaThanhToanFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
