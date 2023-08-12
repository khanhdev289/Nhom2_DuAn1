package khanhnqph30151.fptpoly.duan1.admin.list_history;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.data.DbHelper;

public class Invoce_DAO {
    DbHelper dbHelper;
    private SQLiteDatabase sqLite;

    public Invoce_DAO(Context context) {
        dbHelper = new DbHelper(context);
        sqLite = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public ArrayList<Invoice> getDaTaInvoice(String sql, String... SelectAvg) {
        ArrayList<Invoice> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_invoice", SelectAvg);
        while (cursor.moveToNext()) {
            Invoice i = new Invoice();
            i.setId_history(Integer.parseInt(cursor.getString(cursor.getColumnIndex("invoice_id"))));
            i.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
            i.setPhone(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_phone"))));
            i.setName(cursor.getString(cursor.getColumnIndex("user_name")));
            i.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
            i.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
            i.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
            i.setSum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("invoice_sum"))));
            i.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
            list.add(i);
        }
        return list;
    }

    public ArrayList<Invoice> getAllData() {
        String sql = "SELECT * FROM tbl_invoice";
        return getDaTaInvoice(sql);
    }

    public long update(Invoice i) {
        ContentValues values = new ContentValues();
        values.put("invoice_status", i.getStatus());
        return sqLite.update("tbl_invoice", values, "invoice_id = ?", new String[]{String.valueOf(i.getId_history())});
    }
    @SuppressLint("Range")
    public ArrayList<Invoice> SeLectDaDatHang() {
        ArrayList<Invoice> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_invoice WHERE invoice_status LIKE '%Đã Đặt Hàng%'  ", null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                Invoice i = new Invoice();
                i.setId_history(Integer.parseInt(cursor.getString(cursor.getColumnIndex("invoice_id"))));
                i.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
                i.setPhone(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_phone"))));
                i.setName(cursor.getString(cursor.getColumnIndex("user_name")));
                i.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
                i.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
                i.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
                i.setSum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("invoice_sum"))));
                i.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
                list.add(i);

            }
            while (cursor.moveToNext());
        }
        return list;
    }
    @SuppressLint("Range")
    public ArrayList<Invoice> SeLectDangGiao() {
        ArrayList<Invoice> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_invoice WHERE invoice_status LIKE '%Đang Giao%'  ", null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                Invoice i = new Invoice();
                i.setId_history(Integer.parseInt(cursor.getString(cursor.getColumnIndex("invoice_id"))));
                i.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
                i.setPhone(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_phone"))));
                i.setName(cursor.getString(cursor.getColumnIndex("user_name")));
                i.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
                i.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
                i.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
                i.setSum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("invoice_sum"))));
                i.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
                list.add(i);

            }
            while (cursor.moveToNext());
        }
        return list;
    }
    @SuppressLint("Range")
    public ArrayList<Invoice> SeLectDangchuanbi() {
        ArrayList<Invoice> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_invoice WHERE invoice_status LIKE '%Đang Chuẩn Bị Hàng%'  ", null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                Invoice i = new Invoice();
                i.setId_history(Integer.parseInt(cursor.getString(cursor.getColumnIndex("invoice_id"))));
                i.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
                i.setPhone(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_phone"))));
                i.setName(cursor.getString(cursor.getColumnIndex("user_name")));
                i.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
                i.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
                i.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
                i.setSum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("invoice_sum"))));
                i.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
                list.add(i);

            }
            while (cursor.moveToNext());
        }
        return list;
    }

    @SuppressLint("Range")
    public ArrayList<Invoice> SeLectDaThanhToan() {
        ArrayList<Invoice> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_invoice WHERE invoice_status LIKE '%Đã Thanh Toán%'  ", null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                Invoice i = new Invoice();
                i.setId_history(Integer.parseInt(cursor.getString(cursor.getColumnIndex("invoice_id"))));
                i.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
                i.setPhone(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_phone"))));
                i.setName(cursor.getString(cursor.getColumnIndex("user_name")));
                i.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
                i.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
                i.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
                i.setSum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("invoice_sum"))));
                i.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
                list.add(i);

            }
            while (cursor.moveToNext());
        }
        return list;
    }
}
