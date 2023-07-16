package khanhnqph30151.fptpoly.duan1.user.history;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.data.DbHelper;
import khanhnqph30151.fptpoly.duan1.user.cart.Cart;

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
            history.setPhone(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_phone"))));
            history.setName(cursor.getString(cursor.getColumnIndex("cart_name")));
            history.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
            history.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
            history.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
            history.setSum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("invoice_sum"))));
            history.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
            list.add(history);
        }
        return list;
    }
    public ArrayList<History_model> getAllData() {
        String sql = "SELECT * FROM tbl_invoice";
        return getDataHistory(sql);
    }
    public long insert(Cart cart){
        ContentValues values = new ContentValues();
        values.put("food_id", cart.getIdFood());
        values.put("cart_quantity", cart.getQuanti());
        values.put("cart_sum", cart.getSum());

        return sqLite.insert("tbl_cart", null, values);
    }
    public ArrayList<String> getName(String sql, String... SelectAvgs) {
        ArrayList<String> lst = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("food_name"));
            lst.add(name);
        }
        return lst;
    }

//    public long updateSum(Cart cart){
//        ContentValues values = new ContentValues();
//        values.put("food_id", cart.getIdFood());
//        values.put("cart_quantity", cart.getQuanti());
//        values.put("cart_sum", cart.getSum());
//        return sqLiteDatabase.update("tbl_cart", values, "cart_id = ?", new String[]{String.valueOf(cart.getIdCart())});
//    }
    public boolean isInvoiceExists(History_model history) {
        String query = "SELECT * FROM tbl_invoice WHERE invoice_id = ?";
        String[] selectionArgs = {String.valueOf(history.getId_history())};
        Cursor cursor = sqLite.rawQuery(query, selectionArgs);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

}
