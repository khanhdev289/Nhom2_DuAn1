package khanhnqph30151.fptpoly.duan1.user.cart;

public class Cart {
    private int idCart;
    private int idFood;
    private int quanti;
    private double sum;

    public Cart() {
    }

    public Cart(int idCart, int idFood, int quanti, double sum) {
        this.idCart = idCart;
        this.idFood = idFood;
        this.quanti = quanti;
        this.sum = sum;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public int getQuanti() {
        return quanti;
    }

    public void setQuanti(int quanti) {
        this.quanti = quanti;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
