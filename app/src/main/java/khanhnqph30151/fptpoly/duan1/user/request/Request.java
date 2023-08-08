package khanhnqph30151.fptpoly.duan1.user.request;

public class Request {
    private int id;
    private String ten, sodienthoai, email, noidung;

    public Request(String ten, String sodienthoai, String email, String noidung) {
        this.ten = ten;
        this.sodienthoai = sodienthoai;
        this.email = email;
        this.noidung = noidung;
    }

    public Request() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
