package khanhnqph30151.fptpoly.duan1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "data";
    private static final int DB_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public static final String TABLE_USER_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_user (" +
            "user_name TEXT PRIMARY KEY," +
            "user_pass TEXT NOT NULL," +
            "user_role TEXT" +
            ")";
    public static final String insert_admin="Insert into tbl_user(user_name,user_pass,user_role) values" +
            "('admin','123','admin')";
    public static final String TABLE_REQUEST_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_request (" +
            "request_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "request_name TEXT NOT NULL," +
            "request_email TEXT NOT NULL," +
            "request_phone TEXT NOT NULL," +
            "request_content TEXT NOT NULL" +
            ")";

    public static final String TABLE_FOOD_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_food (" +
            "food_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "food_img TEXT NOT NULL, " +
            "food_name TEXT NOT NULL, " +
            "food_description TEXT NOT NULL, " +
            "food_price DOUBLE NOT NULL" +
            ")";

    public static final String TABLE_CART_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_cart (" +
            "cart_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "food_id INTEGER REFERENCES tbl_food(food_id), " +
            "cart_quantity INTEGER NOT NULL, " +
            "cart_sum DOUBLE NOT NULL" +
            ")";

    public static final String TABLE_INVOICE_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_invoice (" +
            "invoice_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "cart_id INTEGER REFERENCES tbl_cart(cart_id), " +
            "cart_address TEXT NOT NULL, " +
            "invoice_time TEXT NOT NULL, " +
            "invoice_status TEXT NOT NULL" +
            ")";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USER_CREATE);

        db.execSQL(TABLE_FOOD_CREATE);

        db.execSQL(TABLE_CART_CREATE);

        db.execSQL(TABLE_REQUEST_CREATE);

        db.execSQL(TABLE_INVOICE_CREATE);

      db.execSQL(insert_admin);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS tbl_thuThu");
            db.execSQL("DROP TABLE IF EXISTS tbl_thanhVien");
            db.execSQL("DROP TABLE IF EXISTS tbl_loaiSach");
            db.execSQL("DROP TABLE IF EXISTS tbl_Sach");
            db.execSQL("DROP TABLE IF EXISTS tbl_phieuMuon");
            onCreate(db);
        }

    }
}
