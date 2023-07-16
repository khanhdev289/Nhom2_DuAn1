package khanhnqph30151.fptpoly.duan1.admin.list_history;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.admin.food.Food;
import khanhnqph30151.fptpoly.duan1.data.DbHelper;
import khanhnqph30151.fptpoly.duan1.user.history.History_model;

public class invoce_DAO {
    DbHelper dbHelper;
    private SQLiteDatabase sqLite;
    public invoce_DAO(Context context){
        dbHelper = new DbHelper(context);
        sqLite = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public ArrayList<invoice> getDaTaInvoice(String sql, String... SelectAvg){
        ArrayList<invoice> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_invoice", SelectAvg);
        while (cursor.moveToNext()){
            invoice i = new invoice();
            i.setId_history(Integer.parseInt(cursor.getString(cursor.getColumnIndex("invoice_id"))));
            i.setId_cart(cursor.getString(cursor.getColumnIndex("cart_id")));
            i.setPhone(cursor.getString(cursor.getColumnIndex("cart_phone")));
            i.setName(cursor.getString(cursor.getColumnIndex("cart_name")));
            i.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
            i.setContent(cursor.getString(cursor.getColumnIndex("invoice_content")));
            i.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
            i.setSum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("invoice_sum"))));
            i.setstatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
            list.add(i);
        }
        return list;
    }
    public ArrayList<invoice> getAllData() {
        String sql = "SELECT * FROM tbl_invoice";
        return getDaTaInvoice(sql);
    }
    public long update(invoice i){
        ContentValues values = new ContentValues();

        values.put("invoice_status", i.getstatus());
        return sqLite.update("tbl_invoice", values, "invoice_id = ?", new String[]{String.valueOf(i.getId_history())});
    }
}
