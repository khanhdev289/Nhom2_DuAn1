package khanhnqph30151.fptpoly.duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

import khanhnqph30151.fptpoly.duan1.Setting.UserFragment;
import khanhnqph30151.fptpoly.duan1.user.Cart.Cart_Fragment;
import khanhnqph30151.fptpoly.duan1.user.History.HistoryFragment;
import khanhnqph30151.fptpoly.duan1.user.Home.HomeFragment;
import khanhnqph30151.fptpoly.duan1.user.Request.RequestFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationBarView view = findViewById(R.id.bottom_navi);

        view.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_Home) {
                    replaceFragment(new HomeFragment());
                    return true;
                } else if (item.getItemId() == R.id.action_Cart) {
                    replaceFragment(new Cart_Fragment());
                    return true;
                } else if (item.getItemId() == R.id.action_Request) {
                    replaceFragment(new RequestFragment());
                    return true;
                } else if (item.getItemId() == R.id.action_History) {
                    replaceFragment(new HistoryFragment());
                    return true;
                } else if (item.getItemId() == R.id.action_User) {
                    replaceFragment(new UserFragment());
                    return true;
                } else {
                    return false;
                }
            }
        });

        replaceFragment(new HomeFragment());
    }
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_content, fragment);
        transaction.commit();
    }
}