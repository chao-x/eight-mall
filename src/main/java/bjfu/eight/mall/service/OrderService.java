package bjfu.eight.mall.service;


import bjfu.eight.mall.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrdersMapper ordersMapper;

    public int confirmReceipt(long orderNo){
        return ordersMapper.updateStatus(4,orderNo);
    }
}
