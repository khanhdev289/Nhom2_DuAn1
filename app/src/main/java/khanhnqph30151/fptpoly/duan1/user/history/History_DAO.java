package khanhnqph30151.fptpoly.duan1.user.history;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.data.DbHelper;

public class History_DAO {
    DbHelper dbHelper;
    private SQLiteDatabase sqLite;
    public History_DAO(Context context){
        dbHelper = new DbHelper(context);
        sqLite = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public ArrayList<History_model> getDataHistory(String sql, String... SelectAvg){
        ArrayList<History_model> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_invoice", SelectAvg);
        while (cursor.moveToNext()){
            History_model history = new History_model();
            history.setId_history(Integer.parseInt(cursor.getString(cursor.getColumnIndex("invoice_id"))));
            history.setId_cart(cursor.getString(cursor.getColumnIndex("cart_id")));
            history.setPhone(cursor.getString(cursor.getColumnIndex("cart_phone")));
            history.setName(cursor.getString(cursor.getColumnIndex("cart_name")));
            history.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
            history.setTime(cursor.getString(cursor.getColumnIndex("invoice_conten")));
            history.setSum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("invoice_sum"))));

            list.add(history);
        }
        return list;
    }
    public ArrayList<History_model> getAllData() {
        String sql = "SELECT * FROM tbl_invoice";
        return getDataHistory(sql);
    }

}
