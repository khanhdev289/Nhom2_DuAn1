package khanhnqph30151.fptpoly.duan1.user.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import khanhnqph30151.fptpoly.duan1.Notification;
import khanhnqph30151.fptpoly.duan1.R;
import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    HomeDAO homeDAO;
    ArrayList<Home> listHome;
    HomeAdapter adapter;
    Context context;


    ViewPager viewPager;
    CircleIndicator circleIndicator;
    SlideAdapter slideAdapter;
    ArrayList<Slide> listPhoto;
    Timer timer;
    private GradientDrawable selectedBorder;
    private GradientDrawable normalBorder;
    private View selectedView = null;
    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recy_fragment_home_listFood);

        ImageButton img_tapsearch = view.findViewById(R.id.btn_fragment_home_tapSearch);
        ImageButton img_noti = view.findViewById(R.id.btn_fragment_home_noti);
        EditText edSearch = view.findViewById(R.id.ed_fragment_home_search);

        ImageView ivTypeFood1,ivTypeFood2,ivTypeFood3;
        TextView tvTypeFood1,tvTypeFood2,tvTypeFood3, tvGetAll;

        ivTypeFood1 = view.findViewById(R.id.iv_home_typeFood1);
        ivTypeFood2 = view.findViewById(R.id.iv_home_typeFood2);
        ivTypeFood3 = view.findViewById(R.id.iv_home_typeFood3);
        tvTypeFood1 = view.findViewById(R.id.tv_home_typeFood1);
        tvTypeFood2 = view.findViewById(R.id.tv_home_typeFood2);
        tvTypeFood3 = view.findViewById(R.id.tv_home_typeFood3);
        tvGetAll = view.findViewById(R.id.tv_home_getAll);


        viewPager = view.findViewById(R.id.viewPager);
        circleIndicator = view.findViewById(R.id.circle_indicator);

        listPhoto = getListPhoto();
        slideAdapter = new SlideAdapter(getActivity(), listPhoto);
        viewPager.setAdapter(slideAdapter);
        circleIndicator.setViewPager(viewPager);
        slideAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());


        selectedBorder = new GradientDrawable();
        selectedBorder.setShape(GradientDrawable.RECTANGLE);
        selectedBorder.setStroke(5, Color.BLACK); // Màu viền khi được chọn
        selectedBorder.setCornerRadius(10);

        normalBorder = new GradientDrawable();
        normalBorder.setShape(GradientDrawable.RECTANGLE);
        normalBorder.setStroke(0, Color.TRANSPARENT); // Màu viền bình thường
        normalBorder.setCornerRadius(10);

        img_noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Notification.class);
                startActivity(i);
            }
        });
        img_tapsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edSearch.length()>0){
                    String searchName = edSearch.getText().toString().trim();
                    LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 1);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    HomeDAO homeDAO1 = new HomeDAO(getContext());
                    listHome = new ArrayList<>();
                    listHome = homeDAO1.Search(searchName);
                    adapter.setData(listHome);
                    recyclerView.setAdapter(adapter);
                }else {
                    reloadData();
                }
            }
        });
        ivTypeFood1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typeName = tvTypeFood1.getText().toString();
                selectTypeFood(v, typeName);
            }
        });
        ivTypeFood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typeName = tvTypeFood2.getText().toString();
                selectTypeFood(v, typeName);
            }
        });
        ivTypeFood3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typeName = tvTypeFood3.getText().toString();
                selectTypeFood(v, typeName);
            }
        });
        tvGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadData();
            }
        });

        autoSlideShow();
        reloadData();


    }
    private void selectTypeFood(View view, String typeName) {
        // Kiểm tra nếu mục được chọn trước đó không null và khác mục mới
        if (selectedView != null && selectedView != view) {
            // Bỏ viền của mục cũ
            selectedView.setBackground(normalBorder);
        }

        // Set viền mới cho mục mới được chọn
        view.setBackground(selectedBorder);

        // Lưu trạng thái mục mới được chọn
        selectedView = view;

        // Xử lý logic tương ứng với việc nhấn vào từng mục ở đây
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(linearLayoutManager);
        HomeDAO homeDAO1 = new HomeDAO(getContext());
        listHome = new ArrayList<>();
        listHome = homeDAO1.TypeName(typeName);
        adapter.setData(listHome);
        recyclerView.setAdapter(adapter);
    }
    private ArrayList<Slide> getListPhoto(){
        ArrayList<Slide> list = new ArrayList<>();
        list.add(new Slide(R.drawable.banner_home1));
        list.add(new Slide(R.drawable.banner_home2));
        list.add(new Slide(R.drawable.banner_home3));
        list.add(new Slide(R.drawable.banner_home4));
        return list;
    }
    private void autoSlideShow(){
        if (listPhoto == null || listPhoto.isEmpty() || viewPager == null){
            return;
        }
        if (timer == null){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = listPhoto.size()-1;
                        if (currentItem < totalItem){
                            currentItem ++;
                            viewPager.setCurrentItem(currentItem);
                        }else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
            timer = null;
        }
    }

    private void reloadData(){
        homeDAO = new HomeDAO(getContext());
        listHome = homeDAO.getAllData();
        adapter = new HomeAdapter(getContext(),listHome,homeDAO);
        adapter.setData(listHome);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    }

}