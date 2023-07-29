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
    public static final String insert_admin = "Insert into tbl_user(user_name,user_pass,user_role) values" +
            "('admin','123','admin'), ('khanh','123','hehe'),('khai','123','')";
    public static final String TABLE_REQUEST_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_request (" +
            "request_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "request_name TEXT ," +
            "request_email TEXT ," +
            "request_phone TEXT ," +
            "request_content TEXT " +
            ")";
    public static final String TABLE_TYPE_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_typeFood (" +
            "typeFood_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "typeFood_typeName TEXT NOT NULL" +
            ")";


    public static final String TABLE_FOOD_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_food (" +
            "food_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "typeFood_typeName TEXT REFERENCES tbl_typeFood(typeFood_typeName)," +
            "food_img TEXT NOT NULL, " +
            "food_name TEXT NOT NULL, " +
            "food_description TEXT NOT NULL, " +
            "food_price DOUBLE NOT NULL" +
            ")";

    public static final String TABLE_CART_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_cart (" +
            "cart_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "food_id INTEGER REFERENCES tbl_food(food_id), " +
            "user_name TEXT REFERENCES tbl_user(user_name)," +
            "cart_quantity INTEGER NOT NULL, " +
            "cart_sum DOUBLE NOT NULL" +
            ")";

    public static final String TABLE_INVOICE_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_invoice (" +
            "invoice_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "user_name TEXT REFERENCES tbl_user(user_name)," +
            "cart_id INTEGER REFERENCES tbl_cart(cart_id), " +
            "cart_phone TEXT NOT NULL, " +
            "cart_address TEXT NOT NULL, " +
            "invoice_content TEXT NOT NULL, " +
            "invoice_sum DOUBLE NOT NULL, " +
            "invoice_status TEXT ," +
            "invoice_time TEXT NOT NULL" +
            ")";
    public static final String TABLE_COMMENT_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_comment (" +
            "comment_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "comment_content TEXT ," +
            "user_name TEXT REFERENCES tbl_user(user_name)," +
            "food_id INTEGER REFERENCES tbl_food(food_id)," +
            "rating INTEGER )";
    public static final String TABLE_NOTI_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_noti (" +
            "noti_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "noti_time TEXT NOT NULL, " +
            "invoice_content TEXT REFERENCES tbl_invoice(invoice_content), " +
            "invoice_status TEXT REFERENCES tbl_invoice(invoice_status)," +
            "user_name TEXT REFERENCES tbl_invoice(user_name)" +
            ")";
    public static final String insert_cmt = "Insert into tbl_comment(comment_content,user_name,food_id,rating) values" +
            "('xời , tuyệt vời','khanh','1','4'), ('hết nước chấm','khanh','2','5'),('hết nước chấm','khai','1','5'),('xời, tuyệt vời','khai','2','3'),('mlem','khanh','2','5'),('okkkk','khai','2','4')";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USER_CREATE);

        db.execSQL(TABLE_FOOD_CREATE);

        db.execSQL(TABLE_CART_CREATE);

        db.execSQL(TABLE_REQUEST_CREATE);

        db.execSQL(TABLE_INVOICE_CREATE);
        db.execSQL(TABLE_TYPE_CREATE);

        db.execSQL(TABLE_COMMENT_CREATE);
        db.execSQL(TABLE_NOTI_CREATE);

        db.execSQL("INSERT INTO tbl_noti(noti_time,noti_status) VALUES ('20:20','Đã thanh toán'),('30:20','Đang giao hàng')");
        db.execSQL("INSERT INTO tbl_typeFood(typeFood_typeName) VALUES ('Món chính'),('Món phụ'),('Đồ uống')");
        db.execSQL("INSERT INTO tbl_food(typeFood_typeName,food_img,food_name,food_description,food_price) VALUES ('Món chính','https://image.vtc.vn/resize/th/upload/2020/03/17/cay-to-7-mon-08364272.jpg'" +
                ", 'Thịt chó'," +
                " 'Ăn một bữa thịt chó, có người gỡ lại hết cả tiền thua, mà lại còn được thêm là khác. Thử hỏi trong tất cả các món ăn trên thế giới có món ăn ...'" +
                ",80000), " +
                "('Món phụ','https://static.vinwonders.com/production/bun-dau-mam-tom-ha-noi-1.jpg', " +
                "'Bún đậu', " +
                "'Một mẹt bún đậu mắm tôm với đầy đủ các nguyên liệu hấp dẫn, sạch sẽ chắc hẳn là món ngon mà bất cứ ai cũng khó có thể chối từ.'," +
                " 30000), " +
                "('Đồ uống','https://i.ytimg.com/vi/S2bfZr2s-1g/maxresdefault.jpg'," +
                " 'Gà Chiên Mắm', " +
                "'Hương vị thơm ngon, hấp dẫn của món cánh gà chiên mắm vừa giúp thay đổi khẩu vị vừa khiến bạn cảm thấy ngon miệng hơn.', " +
                "50000)");
        db.execSQL(insert_admin);
        db.execSQL(insert_cmt);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS tbl_usser");
            db.execSQL("DROP TABLE IF EXISTS tbl_request");
            db.execSQL("DROP TABLE IF EXISTS tbl_food");
            db.execSQL("DROP TABLE IF EXISTS tbl_cart");
            db.execSQL("DROP TABLE IF EXISTS tbl_invoice");
            onCreate(db);
        }

    }
}
