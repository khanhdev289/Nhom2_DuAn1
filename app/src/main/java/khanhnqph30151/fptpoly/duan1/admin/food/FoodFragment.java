package khanhnqph30151.fptpoly.duan1.admin.food;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.R;



public class FoodFragment extends Fragment {
    RecyclerView recyclerView;
    FoodDAO foodDAO;
    ArrayList<Food> listFood;
    ArrayList<TypeFood> llist;
    FoodAdapter adapter;
    Context context;

    public FoodFragment() {
        // Required empty public constructor
    }


    public static FoodFragment newInstance() {
        FoodFragment fragment = new FoodFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_food, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recy_fragment_listFood_listFood);

        ImageButton img_tapSearch = view.findViewById(R.id.btn_fragment_listFood_tapSearch);
        ImageButton img_tapAdd = view.findViewById(R.id.btn_fragment_listFood_tapAdd);
        EditText edSearch = view.findViewById(R.id.ed_fragment_listFood_search);

        img_tapSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edSearch.length()>0){
                    String searchName = edSearch.getText().toString();
                    LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 1);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    FoodDAO foodDAO1 = new FoodDAO(getContext());
                    listFood = new ArrayList<>();
                    listFood = foodDAO1.Search(searchName);
                    adapter.setData(listFood);
                    recyclerView.setAdapter(adapter);
                }else {
                    reloadData();
                }
            }
        });
        img_tapAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                TypeDAO typeDAO = new TypeDAO(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_listfood_add);
                Food food = new Food();
                EditText ed_listfood_img,ed_listfood_name,ed_listfood_price,ed_listfood_des;
                Spinner spn = dialog.findViewById(R.id.spn_dialog_listfood_add_type);
                Button btnDialogAddSubmit;
                ed_listfood_img = dialog.findViewById(R.id.edt_dialog_listfood_add_img);
                ed_listfood_name = dialog.findViewById(R.id.edt_dialog_listfood_add_name);
                ed_listfood_price = dialog.findViewById(R.id.edt_dialog_listfood_add_price);
                ed_listfood_des = dialog.findViewById(R.id.edt_dialog_listfood_add_des);
                btnDialogAddSubmit = dialog.findViewById(R.id.btn_dialog_listfood_add_add);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, typeDAO.name());
                spn.setAdapter(adapter1);
                spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        llist = typeDAO.getAllData();
                        food.setType((llist.get(position).getTypeName()));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                btnDialogAddSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String img = ed_listfood_img.getText().toString();
                        String name = ed_listfood_name.getText().toString();
                        String priceString = ed_listfood_price.getText().toString();
                        String des = ed_listfood_des.getText().toString();

                        if (img.trim().isEmpty() || name.trim().isEmpty()) {
                            Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        } else if (priceString.trim().isEmpty()) {
                            Toast.makeText(getContext(), "Vui lòng nhập giá tiền", Toast.LENGTH_SHORT).show();
                        } else {
                            int price = 0;
                            try {
                                price = Integer.parseInt(priceString);
                            } catch (NumberFormatException e) {
                                Toast.makeText(getContext(), "Giá tiền phải là một số", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            food.setImg(img);
                            food.setName(name);
                            food.setPrice(price);
                            food.setDes(des);

                            if (foodDAO.insert(food) >= 0) {
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                                listFood = foodDAO.getAllData();
                                adapter.setData(listFood);
                                dialog.dismiss();
                            } else {
                                Toast.makeText(getContext(), "Thêm thất bại!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        });
        reloadData();

    }
    private void reloadData(){
        foodDAO = new FoodDAO(getContext());
        listFood = foodDAO.getAllData();
        adapter = new FoodAdapter(getContext(),listFood,foodDAO);
        adapter.setData(listFood);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    }
}