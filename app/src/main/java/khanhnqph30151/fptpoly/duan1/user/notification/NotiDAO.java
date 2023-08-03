package khanhnqph30151.fptpoly.duan1.user.notification;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.data.DbHelper;
import khanhnqph30151.fptpoly.duan1.user.home.Home;
import khanhnqph30151.fptpoly.duan1.user.home.comment.Comment;

public class NotiDAO {
    DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    public NotiDAO(Context contex){
        dbHelper = new DbHelper(contex);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public ArrayList<Noti> getData(String sql, String... SelectAvg){
        ArrayList<Noti> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvg);
        while (cursor.moveToNext()){
            Noti noti = new Noti();
            noti.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("noti_id"))));
            noti.setTime(cursor.getString(cursor.getColumnIndex("noti_time")));
            noti.setContent(cursor.getString(cursor.getColumnIndex("invoice_content")));
            noti.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
            noti.setUser_name(cursor.getString(cursor.getColumnIndex("user_name")));
            list.add(noti);
        }
        return list;
    }
    public long insert(Noti noti){
        ContentValues values = new ContentValues();
        values.put("noti_time", noti.getTime());
        values.put("invoice_status", noti.getStatus());
        values.put("invoice_content",noti.getContent());
        values.put("user_name",noti.getUser_name());
        return sqLiteDatabase.insert("tbl_noti", null, values);
    }
    public ArrayList<Noti> getAllData(){
        String sql = "SELECT * FROM tbl_noti ORDER BY noti_time ASC";
        return getData(sql);
    }
    public ArrayList<Noti> getByuserName(String userName) {
        String sql = "SELECT * FROM tbl_noti  where user_name = ?";
        return getData(sql, userName);
    }

}
