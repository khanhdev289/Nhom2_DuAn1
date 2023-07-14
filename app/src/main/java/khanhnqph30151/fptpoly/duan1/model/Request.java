package khanhnqph30151.fptpoly.duan1.model;

public class Request {
    int request_id;
    String request_name,request_email,request_phone,request_content;

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public String getRequest_name() {
        return request_name;
    }

    public void setRequest_name(String request_name) {
        this.request_name = request_name;
    }

    public String getRequest_email() {
        return request_email;
    }

    public void setRequest_email(String request_email) {
        this.request_email = request_email;
    }

    public String getRequest_phone() {
        return request_phone;
    }

    public void setRequest_phone(String request_phone) {
        this.request_phone = request_phone;
    }

    public String getRequest_content() {
        return request_content;
    }

    public void setRequest_content(String request_content) {
        this.request_content = request_content;
    }
}
