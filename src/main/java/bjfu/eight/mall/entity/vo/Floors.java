package bjfu.eight.mall.entity.vo;

import bjfu.eight.mall.entity.po.Products;

import java.util.List;

public class Floors {
    List<Products> oneFloor;

    public List<Products> getOneFloor() {
        return oneFloor;
    }

    public void setOneFloor(List<Products> oneFloor) {
        this.oneFloor = oneFloor;
    }

    public List<Products> getTwoFloor() {
        return twoFloor;
    }

    public void setTwoFloor(List<Products> twoFloor) {
        this.twoFloor = twoFloor;
    }

    public List<Products> getThreeFloor() {
        return threeFloor;
    }

    public void setThreeFloor(List<Products> threeFloor) {
        this.threeFloor = threeFloor;
    }

    public List<Products> getFourFloor() {
        return fourFloor;
    }

    public void setFourFloor(List<Products> fourFloor) {
        this.fourFloor = fourFloor;
    }

    List<Products> twoFloor;
    List<Products> threeFloor;
    List<Products> fourFloor;


}
