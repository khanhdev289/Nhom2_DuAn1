package khanhnqph30151.fptpoly.duan1.user.request;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.R;
import khanhnqph30151.fptpoly.duan1.admin.food.FoodAdapter;


public class RequestFragment extends Fragment {
    Context context;
    private DAO dao;
    private ArrayList<Request> listRequest ;
    private EditText user_rq_name, user_rq_email, user_rq_phone, user_rq_content;
    private Button btn_user_rq_send;
    AdapterRequest adapter;
    private View mView;

    public RequestFragment() {
        // Required empty public constructor
    }

    public static RequestFragment newInstance(String param1, String param2) {
        RequestFragment fragment = new RequestFragment();

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

        mView = inflater.inflate(R.layout.fragment_request, container, false);
        dao=new DAO(getContext());
        listRequest=dao.getAllData();
        adapter=new AdapterRequest(getContext(),listRequest);
        user_rq_name = mView.findViewById(R.id.user_rq_name);
        user_rq_email = mView.findViewById(R.id.user_rq_email);
        user_rq_phone = mView.findViewById(R.id.user_rq_phone);
        user_rq_content = mView.findViewById(R.id.user_rq_content);
        btn_user_rq_send = mView.findViewById(R.id.btn_user_rq_send);
        btn_user_rq_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendRequestfromUserforAdmin();
            }
        });

        return mView;
         }
    private void sendRequestfromUserforAdmin() {
        dao =new DAO(getContext());
        Request request = new Request();
        String add_rq_name = user_rq_name.getText().toString();
        String add_rq_email = user_rq_email.getText().toString();
        String add_rq_phone = user_rq_phone.getText().toString();
        String add_rq_content = user_rq_content.getText().toString();
        if (add_rq_name.length() == 0) {
            user_rq_name.requestFocus();
            user_rq_name.setError("Không được để trống tên");
        } else if (add_rq_email.length() == 0) {
            user_rq_email.requestFocus();
            user_rq_email.setError("Không được để trống email");
        } else if (add_rq_phone.length() == 0) {
            user_rq_phone.requestFocus();
            user_rq_phone.setError("Không được để trống số điện thoại");
        } else if (add_rq_content.length() == 0) {
            user_rq_content.requestFocus();
            user_rq_content.setError("Không được để trống nội dung");
        } else {


            request.setTen(add_rq_name);
            request.setEmail(add_rq_email);
            request.setNoidung(add_rq_content);
            request.setSodienthoai(add_rq_phone);

            if (dao.AddRQ(request) >= 0) {
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(mView.getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }

        }
    }
}