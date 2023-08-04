package khanhnqph30151.fptpoly.duan1.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.R;
import khanhnqph30151.fptpoly.duan1.user.cart.Cart;
import khanhnqph30151.fptpoly.duan1.user.cart.CartDAO;
import khanhnqph30151.fptpoly.duan1.user.home.Home;
import khanhnqph30151.fptpoly.duan1.user.home.comment.Comment;
import khanhnqph30151.fptpoly.duan1.user.home.comment.CommentAdapter;
import khanhnqph30151.fptpoly.duan1.user.home.comment.CommentDAO;
import khanhnqph30151.fptpoly.duan1.user.home.comment.RatingStarView;


public class ItemInforFood extends AppCompatActivity {

    Button btn_add;
    RecyclerView recyclerView;
    TextView count_cmt;
    CommentAdapter commentAdapter;
    CommentDAO commentDAO;
    ArrayList<Comment> list;

    TextView tv_avgRating;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_infor_food);
        int id_food=getIntent().getIntExtra("foodId", 0);
        String dataImage = getIntent().getStringExtra("foodImg");
        String dataName = getIntent().getStringExtra("foodName");
        String dataContent = getIntent().getStringExtra("foodDes");
        int dataPrice = getIntent().getIntExtra("foodPrice", 0);

        tv_avgRating=findViewById(R.id.AVG_rating);
        ImageButton btn_back = findViewById(R.id.btn_infor_food_back);
        btn_add=findViewById(R.id.btn_add_cart);
        recyclerView=findViewById(R.id.recy_comment);
        count_cmt=findViewById(R.id.tv_count_comment);
        commentDAO=new CommentDAO(getApplicationContext());
        list=commentDAO.getByFoodId(String.valueOf(id_food));
        commentAdapter=new CommentAdapter(ItemInforFood.this,list);
        recyclerView.setAdapter(commentAdapter);
        count_cmt.setText("("+commentDAO.countCmt(String.valueOf(id_food))+")");
        float rating_avg=commentDAO.getAVG(String.valueOf(id_food));
        tv_avgRating.setText(String.format("%.1f",rating_avg)+"/5");



        ImageView iv_image = findViewById(R.id.iv_infor_food_img);
        TextView tv_name = findViewById(R.id.tv_infor_food_name);
        TextView tv_content = findViewById(R.id.tv_infor_food_content);
        TextView tv_price = findViewById(R.id.tv_infor_food_price);


        Picasso.get().load(dataImage).into(iv_image);
        tv_name.setText(dataName);
        tv_content.setText(dataContent);
        tv_price.setText(String.valueOf(dataPrice)+" VND");

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartDAO cartDAO = new CartDAO(getApplicationContext());
                Cart cart = new Cart();
                cart.setIdFood(id_food);
                cart.setQuanti(1);
                cart.setSum(dataPrice);
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String loggedInUserName = sharedPreferences.getString("USERNAME", "");
                cart.setUsername(loggedInUserName);
                if (!cartDAO.isFoodExists(cart.getIdFood(), cart.getUsername())) {
                    if (cartDAO.insert(cart) > 0) {
                        Toast.makeText(ItemInforFood.this, "Đã Thêm Vào Giỏ Hàng", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(ItemInforFood.this, "không Thêm Vào Giỏ Hàng", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ItemInforFood.this, "Món ăn đã được chọn", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}