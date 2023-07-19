package khanhnqph30151.fptpoly.duan1.user.request;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.data.DbHelper;

public class DAO {
    private DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    public DAO(Context contex){
        dbHelper = new DbHelper(contex);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public ArrayList<Request> getDataRequest(){
        ArrayList<Request> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_request",null);
        while (cursor.moveToNext()){
            Request request = new Request();
            request.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("request_id"))));
            request.setTen((cursor.getString(cursor.getColumnIndex("request_name"))));
            request.setEmail((cursor.getString(cursor.getColumnIndex("request_email"))));
            request.setSodienthoai((cursor.getString(cursor.getColumnIndex("request_phone"))));
            request.setNoidung((cursor.getString(cursor.getColumnIndex("request_content"))));
            list.add(request);
        }
        return list;
    }
    public ArrayList<Request> getAllData() {
        String sql = "SELECT * FROM tbl_request";
        return getDataRequest();
    }

//    public ArrayList<Request> GetRequest() {
//        ArrayList<Request> list = new ArrayList<>();
//
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_request", null);
//        while (cursor.moveToNext()){
//            Request ph = new Request();
//            ph.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("request_id"))));
//        }
//        if (cursor.getCount() > 0) {
//            cursor.moveToFirst();
//            do {
//                Request ph = new Request();
//                //add_rq_name, add_rq_phone,add_rq_email, add_rq_content
//                ph.setId(Integer.parseInt(cursor.getString(0)));
//                ph.setSodienthoai(cursor.getString(1));
//                ph.setEmail(cursor.getString(2));
//                ph.setNoidung(cursor.getString(3));
//                list.add(ph);
//            } while (cursor.moveToNext());
//        }
//        return list;
//    }

    public long AddRQ(Request ph) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("request_name", ph.getTen());
        contentValues.put("request_email", ph.getEmail());
        contentValues.put("request_phone", ph.getSodienthoai());
        contentValues.put("request_content", ph.getNoidung());
        return sqLiteDatabase.insert("tbl_request", null, contentValues);
    }
}
