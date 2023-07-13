package khanhnqph30151.fptpoly.duan1.Setting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.data.DbHelper;
import khanhnqph30151.fptpoly.duan1.model.User;

public class UserDAO {
    private final SQLiteDatabase sqLiteDatabase;
    private Context context;
    DbHelper helper;
    public UserDAO(Context context) {
        helper = new DbHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }

    public long insert(User user) {
        ContentValues values = new ContentValues();
        values.put("user_name",user.getUser_name());
        values.put("user_pass",user.getUser_pass());
        values.put("user_role",user.getUser_role());
        return sqLiteDatabase.insert("tbl_user", null, values);
    }
public int update(User user){
    ContentValues values = new ContentValues();
    values.put("user_name",user.getUser_name());
    values.put("user_pass",user.getUser_pass());
    values.put("user_role",user.getUser_role());
    return sqLiteDatabase.update("tbl_user", values, "user_name=?", new String[]{String.valueOf(user.getUser_name())});

}


    public int delete(int ID) {
        return sqLiteDatabase.delete("tbl_user", "user_name=?", new String[]{String.valueOf(ID)});
    }

    public ArrayList<User> getData(String sql, String... SelectAvgs) {
        ArrayList<User> lst = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            User user=new User();
            user.setUser_name(cursor.getString(cursor.getColumnIndex("user_name")));
            user.setUser_pass(cursor.getString(cursor.getColumnIndex("user_pass")));
            user.setUser_role(cursor.getString(cursor.getColumnIndex("user_role")));
            lst.add(user);
        }
        return lst;
    }

    public ArrayList<User> getAllData() {
        String sql = "SELECT * FROM tbl_user";
        return getData(sql);
    }


    public int checkLogin(String name,String password){
        String sql="SELECT * FROM tbl_user WHERE user_name=? AND user_pass=?";
        ArrayList<User> list=getData(sql,name,password);
        if(list.size()==0){
            return -1;
        }
        return 1;
    }
    public String getRole(String  id) {
        SQLiteDatabase database =helper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT user_role FROM tbl_user WHERE user_id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getString(0);
        } else return "Lỗi xác thực !";
    }
    public User getByID(String id) {
        String sql = "SELECT * FROM tbl_user  where user_name=?";
        return getData(sql, id).get(0);
    }
}
