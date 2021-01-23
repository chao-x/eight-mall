package bjfu.eight.mall.service;


import bjfu.eight.mall.entity.po.Address;
import bjfu.eight.mall.entity.po.Item;
import bjfu.eight.mall.entity.po.Order;
import bjfu.eight.mall.entity.vo.OrderDetail;
import bjfu.eight.mall.mapper.AddressMapper;
import bjfu.eight.mall.mapper.ItemsMapper;
import bjfu.eight.mall.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private ItemsMapper itemsMapper;
    @Autowired
    private AddressMapper addressMapper;

    public int confirmReceipt(long orderNo){
        return ordersMapper.updateStatus(4,orderNo);
    }

    public OrderDetail getdetail(long orderNo){
        Order order=ordersMapper.getByOrderno(orderNo);
        int orderId=order.getId();
        int addrId=order.getAddrId();
        Item[] items=itemsMapper.getByOrderid(orderId);
        Address address=addressMapper.getByAddrid(addrId);
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setAddress(address);
        orderDetail.setAddrId(addrId);
        orderDetail.setAmount(order.getAmount());
        orderDetail.setCloseTime(order.getCloseTime());
        orderDetail.setCreated(order.getCreated());
        orderDetail.setDeliveryTime(order.getDeliveryTime());
        orderDetail.setFinishTime(order.getFinishTime());
        orderDetail.setFreight(order.getFreight());
        orderDetail.setOrderItems(items);
        orderDetail.setOrderNo(orderNo);
        orderDetail.setPaymentTime(order.getPaymentTime());
        orderDetail.setType(order.getType());
        orderDetail.setTypeDesc("在线支付");
        orderDetail.setStatus(order.getStatus());
        if(order.getStatus()==1){
            orderDetail.setStatusDesc("未付款");
        }
        else if(order.getStatus()==2){
            orderDetail.setStatusDesc("已付款");
        }
        else if(order.getStatus()==3){
            orderDetail.setStatusDesc("已发货");
        }
        else if(order.getStatus()==4){
            orderDetail.setStatusDesc("交易成功");
        }
        else if(order.getStatus()==5){
            orderDetail.setStatusDesc("交易关闭");
        }
        else if(order.getStatus()==6){
            orderDetail.setStatusDesc("已取消");
        }
        orderDetail.setDeliveryName(address.getName());
        return orderDetail;
    }
}
