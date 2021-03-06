package bjfu.eight.mall.service;

import bjfu.eight.mall.entity.po.Products;
import bjfu.eight.mall.entity.vo.FindProducts;
import bjfu.eight.mall.entity.vo.FindProducts2;
import bjfu.eight.mall.entity.vo.Floors;
import bjfu.eight.mall.mapper.ProductsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductsMapper productsMapper;

    public Floors findFloors() {
        int floors_length;
        Floors floors = new Floors();
        List<Integer> product_ids = productsMapper.findAllProductsID();
        if (product_ids.size() >= 4) {
            floors_length = 4;
        } else {
            floors_length = product_ids.size();
        }
        for (int i = 1; i <= floors_length; i++) {
            List<Products> result = productsMapper.findProductByProductID(product_ids.get(i - 1));
            switch (i) {
                case 1:
                    floors.setOneFloor(result);
                    break;
                case 2:
                    floors.setTwoFloor(result);
                    break;
                case 3:
                    floors.setThreeFloor(result);
                    break;
                case 4:
                    floors.setFourFloor(result);
                    break;
            }
        }
        return floors;
    }

    public List<Products> findHotProducts() {
        return productsMapper.findHotProducts();
    }

    public List<Products> findProductsByID(int id) {
        return productsMapper.findProductsByID(id);
    }

    public List<Products> findProductsByProductID(Products products) {
        return productsMapper.findProductByProduct_ID(products);
    }

    public List<Products> findProductsByIDAll(int id) {
        if (id == -1) {
            return productsMapper.findProductsAll();
        } else {
            return productsMapper.findProductsByIDAll(id);
        }

    }

    public FindProducts2 findProducts(FindProducts findProducts) {
        FindProducts2 findProducts2=new FindProducts2();
        findProducts2.setPageNum(findProducts.getPageNum());
        findProducts.setPageNum((findProducts.getPageNum() - 1) * findProducts.getPageSize());
        if(findProducts.getProductTypeId()!=0)
        {
            findProducts2.setData(productsMapper.findProductsBySort2(findProducts));
            findProducts2.setTotalRecord(productsMapper.countfindProductsBySort2(findProducts));
        }
        else
        {
            findProducts2.setData(productsMapper.findProductsBySort(findProducts));
            findProducts2.setTotalRecord(productsMapper.countfindProductsBySort(findProducts));
        }
        findProducts2.setPageSize(findProducts.getPageSize());
        return findProducts2;
    }

    public boolean updateProduct(Products products) {
        return productsMapper.updateById(products) > 0;
    }


    public boolean updateProductAll(Products products) {
        return productsMapper.updateProduct(products)==1;
    }

    public boolean insertProduct(Products products) {
        return productsMapper.insertProduct(products) == 1;
    }

}
