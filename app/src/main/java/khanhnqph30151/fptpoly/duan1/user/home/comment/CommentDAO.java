package khanhnqph30151.fptpoly.duan1.user.home.comment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.admin.food.Food;
import khanhnqph30151.fptpoly.duan1.data.DbHelper;
import khanhnqph30151.fptpoly.duan1.user.cart.Cart;
import khanhnqph30151.fptpoly.duan1.user.home.Home;

public class CommentDAO {
    DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    public CommentDAO(Context context){
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public ArrayList<Comment> getData(String sql, String... SelectAvg){
        ArrayList<Comment> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvg);
        while (cursor.moveToNext()){
            Comment comment = new Comment();
            comment.setComment_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("comment_id"))));
            comment.setUser_name(cursor.getString(cursor.getColumnIndex("user_name")));
            comment.setComment_content(cursor.getString(cursor.getColumnIndex("comment_content")));
            comment.setFood_id(cursor.getInt(cursor.getColumnIndex("food_id")));
            list.add(comment);
        }
        return list;
    }
    public long insert(Comment comment){
        ContentValues values = new ContentValues();
        values.put("comment_id", comment.getComment_id());
        values.put("user_name", comment.getUser_name());
        values.put("comment_content", comment.getComment_content());
        values.put("food_id", comment.getFood_id());
        return sqLiteDatabase.insert("tbl_comment", null, values);
    }
//    public ArrayList<Comment> getAllData(){
//        String sql = "SELECT * FROM tbl_comment";
//        return getData(sql);
//    }

    public long update(Comment comment){

        ContentValues values = new ContentValues();
        values.put("comment_id", comment.getComment_id());
        values.put("user_name", comment.getUser_name());
        values.put("comment_content", comment.getComment_content());
        values.put("food_id", comment.getFood_id());
        return sqLiteDatabase.update("tbl_comment", values, "comment_id = ?", new String[]{String.valueOf(comment.getComment_id())});
    }
    public int delete(int ID) {
        return sqLiteDatabase.delete("tbl_comment", "comment_id = ?", new String[]{String.valueOf(ID)});
    }
    public ArrayList<Comment> getByFoodId(String ID) {
        String sql = "SELECT * FROM tbl_comment  where food_id = ?";
        return getData(sql, ID);
    }
    public int countCmt(String id){
        String sql="SELECT COUNT(comment_id) FROM tbl_comment WHERE food_id =?";
        Cursor c=sqLiteDatabase.rawQuery(sql,new String[]{id});
        int count = 0;
        if (c != null) {
            if (c.moveToFirst()) {
                count = c.getInt(0);
            }
            c.close();
        }

        return count;
    }
}
