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
            "invoice_status TEXT  REFERENCES tbl_invoice(invoice_status)," +
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

//        db.execSQL("INSERT INTO tbl_noti(noti_time,noti_status) VALUES ('20:20','Đã thanh toán'),('30:20','Đang giao hàng')");
        db.execSQL("INSERT INTO tbl_typeFood(typeFood_typeName) VALUES ('Món chính'),('Món phụ'),('Đồ uống')");
        db.execSQL("INSERT INTO tbl_food(typeFood_typeName,food_img,food_name,food_description,food_price) VALUES ('Món chính','https://image.vtc.vn/resize/th/upload/2020/03/17/cay-to-7-mon-08364272.jpg'" +
                ", 'Thịt chó'," +
                " 'Ăn một bữa thịt chó, có người gỡ lại hết cả tiền thua, mà lại còn được thêm là khác. Thử hỏi trong tất cả các món ăn trên thế giới có món ăn ...'" +
                ",80000), " +
                "('Món phụ','https://static.vinwonders.com/production/bun-dau-mam-tom-ha-noi-1.jpg', " +
                "'Bún đậu', " +
                "'Một mẹt bún đậu mắm tôm với đầy đủ các nguyên liệu hấp dẫn, sạch sẽ chắc hẳn là món ngon mà bất cứ ai cũng khó có thể chối từ.'," +
                " 30000), " +
                "('Món phụ','https://cdn.tgdd.vn/2021/09/CookDishThumb/cach-lam-nem-nuong-nha-trang-bang-noi-chien-khong-dau-thom-thumb-620x620-1.jpg', " +
                "'Nem Nướng Nha Trang', " +
                "'Cách làm nem nướng Nha Trang thơm ngon khó cưỡng bằng nồi chiên không dầu cực kỳ đơn giản tại nhà ...'," +
                " 50000), " +
                "('Món chính','https://cdn.tgdd.vn/Files/2022/01/25/1412805/cach-nau-pho-bo-nam-dinh-chuan-vi-thom-ngon-nhu-hang-quan-202201250230038502.jpg', " +
                "'Phở Boà', " +
                "'Vị bò thơm ngọt nhiều dưỡng chất, bánh phở dai dai cùng với nước dùng đậm đà, hoà huyện với hương thơm của các loai rau thơm ăn kèm.'," +
                " 35000), " +
                "('Món chính','https://statics.vinpearl.com/bun-ca-ha-noi-1_1686916725.png', " +
                "'Bún Cá', " +
                "'Trụng bún qua nước sôi xếp vào tô, thêm cá rán, cà chua, rau cần chần, hành lá, rau thì là rồi múc nước dùng chan nóng lên. Ăn kèm với rau sống'," +
                " 30000), " +
                "('Món chính','https://ict-imgs.vgcloud.vn/2022/09/25/20/quan-com-tho-mo-rong-18-chi-nhanh-trong-2-nam-covid-nho-bat-tay-ung-dung-cong-nghe.png', " +
                "'Cơm Thố', " +
                "'Cơm thố là cách thức làm chín gạo trong một thố cơm nhỏ bằng cách chưng cách thủy. Mỗi một thố cơm tương đương với một chén nhỏ.'," +
                " 40000), " +
                "('Món phụ','https://cdn.tgdd.vn/Files/2020/03/23/1243844/lam-nem-chua-ran-chien-xu-chuan-vi-ha-noi-tai-nha-202003230644238881.jpg', " +
                "'Nem Chua Rán', " +
                "'Nem chua rán là một loại nem đặc biệt làm từ thịt heo xay và bì heo theo công thức đặc trưng. Tuy ở nhiều nơi có công thức chế biến khác nhau'," +
                " 5000), " +
                "('Món phụ','https://khoinguonsangtao.vn/wp-content/uploads/2022/10/hinh-anh-xuc-xich.jpg', " +
                "'Chúc Chích Rán', " +
                "'Xúc xích là một món ăn vặt quen thuộc với tất cả mọi người, đặc biệt là các bạn nhỏ. Tuy nhiên, xúc xích chiên thông thường ăn nhiều cũng ngán'," +
                " 7000), " +
                "('Món phụ','https://media.cooky.vn/article/s640/cooky-article-cover-b4644.jpg', " +
                "'Chả Mực Hạ Long', " +
                "'Chả mực Hạ Long có hương vị thơm ngon đặc biệt, từng miếng chả thơm nức, vàng ruộm, giòn sần sật, nếu đã thưởng thức một lần thì khó mà quên'," +
                " 10000), " +
                "('Món phụ','https://cdn.tgdd.vn/Files/2020/07/24/1273398/cach-lam-trung-cut-lon-xao-me-don-gian-ma-thom-ngon-202201110950108775.jpeg', " +
                "'Cút lộn xào me', " +
                "'Điểm đặc biệt của cút lộn xào me đến từ chính hương vị của nó. Cái vị chua chua ngọt ngọt đấy cực kích thích vị giác và khó mà diễn tả thành lời. '," +
                " 40000), " +
                "('Món chính','https://haisancoto.com/uploads/images/muc-chien-toi-thom-ngon.jpg', " +
                "'Mực cháy tỏi', " +
                "'Mực chiên tỏi thơm lừng, hình thức vô cùng bắt mắt, thịt mực săn chắc bên trong mọng nước, tỏi cháy giòn và thơm xen lẫn vị cay cay của ớt hoà quyện'," +
                " 70000), " +

                "('Đồ uống','https://product.hstatic.net/200000356473/product/cocacola-chai-390ml_7214ffae946e4e63826e8f38a45ed5fa.jpg', " +
                "'Cocacola', " +
                "'Coca-Cola (hay còn gọi là Coca, Coke) là một thương hiệu nước ngọt có ga chứa nước cacbon dioxide bão hòa được sản xuất bởi Công ty Coca-Cola.'," +
                " 10000), " +
                "('Đồ uống','https://storage.googleapis.com/onelife-public/8934588012112.jpg', " +
                "'Pepsi', " +
                "'Pepsi một đồ uống giải khát có gas, lần đầu tiên được sản xuất bởi Caleb Bradham. Ban đầu, Ông pha chế ra một loại nước uống dễ hấp thụ làm từ nước cacbonat, đường, vani và một ít dầu ăn dưới tên'," +
                " 10000), " +
                "('Đồ uống','https://www.coca-cola.com/content/dam/onexp/vn/home-image/fanta/Fanta_Orange_RCG_320ml_Desktop.png', " +
                "'Fanta', " +
                "'Fanta cho niềm vui thêm trọn vẹn. Nước ngọt có gas Fanta, với hương vị trái cây đậm đà và đầy màu sắc, giúp bạn cảm thấy sống động'," +
                " 10000), " +
                "('Đồ uống','https://cdn.tgdd.vn/Files/2021/08/23/1377290/cach-lam-tra-chanh-hong-kong-ngon-chuan-vi-ngoai-quan-202201210008425468.jpeg', " +
                "'Trà chanh', " +
                "'Trà chanh là một thức uống giải khát được kết hợp cân bằng giữa vị thanh chát dịu của trà cùng vị chua của chanh tạo nên thứ đồ uống độc đáo'," +
                " 15000), " +
                "('Đồ uống','https://img.tastykitchen.vn/2022/03/28/tra-dao-cam-sa-927c.jpg', " +
                "'Trà đào cam sả', " +
                "'Trà Đào Cam Sả là thức uống vô cùng được ưa chuộng trong vài năm trở lại đây và không hề có dấu hiệu giảm nhiệt.'," +
                " 15000), " +



                "('Món chính','https://i.ytimg.com/vi/S2bfZr2s-1g/maxresdefault.jpg'," +
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
