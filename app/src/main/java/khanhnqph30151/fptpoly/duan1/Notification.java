package khanhnqph30151.fptpoly.duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;

import khanhnqph30151.fptpoly.duan1.user.home.HomeAdapter;
import khanhnqph30151.fptpoly.duan1.user.home.HomeDAO;
import khanhnqph30151.fptpoly.duan1.user.notification.Noti;
import khanhnqph30151.fptpoly.duan1.user.notification.NotiAdapter;
import khanhnqph30151.fptpoly.duan1.user.notification.NotiDAO;

public class Notification extends AppCompatActivity {
    Button btnBack;
    RecyclerView recyNoti;
    ArrayList<Noti> list;
    NotiDAO dao;
    NotiAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        btnBack = findViewById(R.id.btn_noti_back);
        recyNoti = findViewById(R.id.recy_noti);



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        reloadData();
    }
    private void reloadData(){

        dao = new NotiDAO(this);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("USER_FILE", getApplicationContext().MODE_PRIVATE);
        String loggedInUserName = sharedPreferences.getString("USERNAME", "");
        list = dao.getByuserName(loggedInUserName);
        Collections.reverse(list);
        adapter = new NotiAdapter(this,list);
        adapter.setData(list);
        recyNoti.setAdapter(adapter);
        recyNoti.setLayoutManager(new LinearLayoutManager(this));
    }
}