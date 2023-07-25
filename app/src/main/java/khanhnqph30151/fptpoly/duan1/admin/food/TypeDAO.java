package khanhnqph30151.fptpoly.duan1.admin.food;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.data.DbHelper;

public class TypeDAO {
    DbHelper dbHelper;
    private SQLiteDatabase sqLite;
    public TypeDAO(Context context){
        dbHelper = new DbHelper(context);
        sqLite = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public ArrayList<TypeFood> getDataLoaiSach(String sql, String... SelectAvg){
        ArrayList<TypeFood> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_typeFood", SelectAvg);
        while (cursor.moveToNext()){
            TypeFood type = new TypeFood();
            type.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("typeFood_id"))));
            type.setTypeName(cursor.getString(cursor.getColumnIndex("typeFood_typeName")));
            list.add(type);
        }
        return list;
    }

    public ArrayList<TypeFood> getAllData() {
        String sql = "SELECT * FROM tbl_typeFood";
        return getDataLoaiSach(sql);
    }
    public ArrayList<String> name() {
        String name = "SELECT typeFood_typeName FROM tbl_typeFood";
        return getName(name);
    }


    public ArrayList<String> getName(String sql, String... SelectAvgs) {
        ArrayList<String> lst = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("typeFood_typeName"));
            lst.add(name);
        }
        return lst;

    }

}
