package khanhnqph30151.fptpoly.duan1.user.notification;

public class Noti {
    private int id;
    private String status;
    private String time;

    public Noti() {
    }

    public Noti(int id, String status, String time) {
        this.id = id;
        this.status = status;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
