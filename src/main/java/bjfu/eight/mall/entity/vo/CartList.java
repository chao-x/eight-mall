package bjfu.eight.mall.entity.vo;

public class CartList {
    private Cart2[] lists;
    private double totalPrice;

    public Cart2[] getLists() {
        return lists;
    }

    public void setLists(Cart2[] lists) {
        this.lists = lists;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
