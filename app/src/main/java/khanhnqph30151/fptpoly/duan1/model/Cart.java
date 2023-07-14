package khanhnqph30151.fptpoly.duan1.model;

public class Cart {
    int cart_id,food_id,cart_quantity;
    double cart_sum;

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getCart_quantity() {
        return cart_quantity;
    }

    public void setCart_quantity(int cart_quantity) {
        this.cart_quantity = cart_quantity;
    }

    public double getCart_sum() {
        return cart_sum;
    }

    public void setCart_sum(double cart_sum) {
        this.cart_sum = cart_sum;
    }
}
