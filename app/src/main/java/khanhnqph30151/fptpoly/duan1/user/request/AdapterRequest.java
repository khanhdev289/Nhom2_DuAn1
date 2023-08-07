package khanhnqph30151.fptpoly.duan1.user.request;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.R;

public class AdapterRequest extends BaseAdapter {
    private Context context;
    private ArrayList<Request> listrq;
    private RequestDAO dao;

    public AdapterRequest(Context context, ArrayList<Request> listrq) {
        this.context = context;
        this.listrq = listrq;
    }
    public void setData(ArrayList<Request> list){
        this.listrq = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listrq.size();
    }

    @Override
    public Object getItem(int i) {
        return listrq.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if (view == null) {
            row = View.inflate(context, R.layout.item_admin_request, null);
        } else {
            row = view;
        }
        Request objRequest = listrq.get(i);
//        EditText user_rq_name, user_rq_email, user_rq_phone, user_rq_content;

//        EditText request_name = row.findViewById(R.id.request_name);
//        EditText request_phone = row.findViewById(R.id.request_phone);
//        EditText request_email = row.findViewById(R.id.request_email);
//        EditText request_content = row.findViewById(R.id.request_content);
//
//        request_name.setText(objRequest.getTen());
//        request_phone.setText(objRequest.getSodienthoai());
//        request_email.setText(objRequest.getEmail());
//        request_content.setText(objRequest.getNoidung());
        return row;
    }

}
