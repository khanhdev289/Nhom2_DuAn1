package khanhnqph30151.fptpoly.duan1.model;

public class Invoice {
    int invoice_id,cart_id;
    String cart_address,invoice_time,invoice_status;

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public String getCart_address() {
        return cart_address;
    }

    public void setCart_address(String cart_address) {
        this.cart_address = cart_address;
    }

    public String getInvoice_time() {
        return invoice_time;
    }

    public void setInvoice_time(String invoice_time) {
        this.invoice_time = invoice_time;
    }

    public String getInvoice_status() {
        return invoice_status;
    }

    public void setInvoice_status(String invoice_status) {
        this.invoice_status = invoice_status;
    }
}
