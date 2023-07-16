package khanhnqph30151.fptpoly.duan1.admin.list_history;

public class invoice {
    private int id_history;
    private String id_cart;
    private String phone;
    private String name;
    private String address;
    private String time;
    private double sum;
    private String status;

    public invoice() {
    }

    public invoice(int id_history, String id_cart, String phone, String name, String address, String time, double sum, String status ) {
        this.id_history = id_history;
        this.id_cart = id_cart;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.time = time;
        this.sum = sum;
        this.status = status;
    }

    public int getId_history() {
        return id_history;
    }

    public void setId_history(int id_history) {
        this.id_history = id_history;
    }

    public String getId_cart() {
        return id_cart;
    }

    public void setId_cart(String id_cart) {
        this.id_cart = id_cart;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }
}
