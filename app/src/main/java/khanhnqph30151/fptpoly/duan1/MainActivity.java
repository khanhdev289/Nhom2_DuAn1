package khanhnqph30151.fptpoly.duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

import khanhnqph30151.fptpoly.duan1.admin.list_history.Quanly_StatusFragment;
import khanhnqph30151.fptpoly.duan1.setting.UserFragment;
import khanhnqph30151.fptpoly.duan1.admin.food.FoodFragment;
import khanhnqph30151.fptpoly.duan1.admin.list_history.ListHistoryFragment;
import khanhnqph30151.fptpoly.duan1.admin.list_request.ListRequestFragment;
import khanhnqph30151.fptpoly.duan1.admin.statis.StatisFragment;
import khanhnqph30151.fptpoly.duan1.user.cart.Cart_Fragment;
import khanhnqph30151.fptpoly.duan1.user.history.HistoryFragment;
import khanhnqph30151.fptpoly.duan1.user.history.History_QuanLyFragment;
import khanhnqph30151.fptpoly.duan1.user.home.HomeFragment;
import khanhnqph30151.fptpoly.duan1.user.request.RequestFragment;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationBarView view = findViewById(R.id.bottom_navi);
        sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String role = sharedPreferences.getString("ROLE", "");
        if(role.equalsIgnoreCase("admin")){
            view.getMenu().clear();
            view.inflateMenu(R.menu.bottom_navigation_menu_admin);
                replaceFragment(new FoodFragment());
        }else{
            view.getMenu().clear();
            view.inflateMenu(R.menu.bottom_navigation_menu_user);
            replaceFragment(new HomeFragment());
        }
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
                    replaceFragment(new History_QuanLyFragment());
                    return true;
                } else if (item.getItemId() == R.id.action_User) {
                    replaceFragment(new UserFragment());
                    return true;
                }else if(item.getItemId()==R.id.action_ListFood){
                    replaceFragment(new FoodFragment());
                    return true;
                }
                else if(item.getItemId()==R.id.action_ListRequestt){
                    replaceFragment(new ListRequestFragment());
                    return true;
                }else if(item.getItemId()==R.id.action_List_Invo){
                    replaceFragment(new Quanly_StatusFragment());
                    return true;
                }
                else if(item.getItemId()==R.id.action_Statis){
                    replaceFragment(new StatisFragment());
                    return true;
                }
                else {
                    return false;
                }
            }
        });


    }
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_content, fragment);
        transaction.commit();
    }
}