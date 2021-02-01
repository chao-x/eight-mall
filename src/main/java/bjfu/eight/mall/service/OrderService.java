package bjfu.eight.mall.service;


import bjfu.eight.mall.entity.po.*;
import bjfu.eight.mall.entity.vo.OrderDetail;
import bjfu.eight.mall.entity.vo.OrderList;
import bjfu.eight.mall.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private ItemsMapper itemsMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private CartsMapper cartsMapper;
    @Autowired
    private ProductsMapper productsMapper;

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

    public int cancelOrder(long orderNo){
        return ordersMapper.updateStatus(6,orderNo);
    }

    public OrderList getList(int uid,int status){
        if(status==0){
            ArrayList<OrderDetail> orderDetails=new ArrayList<OrderDetail>();
            Order[] orders=ordersMapper.getByUserid(uid).toArray(new Order[ordersMapper.getByUserid(uid).size()]);
            for(Order order:orders){
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
                orderDetail.setOrderNo(order.getOrderNo());
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
                orderDetails.add(orderDetail);
            }
            OrderList orderList=new OrderList();
            orderList.setOrderDetails(orderDetails.toArray(new OrderDetail[orderDetails.size()]));
            return orderList;
        }
        else {
            ArrayList<OrderDetail> orderDetails=new ArrayList<OrderDetail>();
            Order[] orders=ordersMapper.getByUserid(uid).toArray(new Order[ordersMapper.getByUserid(uid).size()]);
            for(Order order:orders){
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
                orderDetail.setOrderNo(order.getOrderNo());
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
                if(orderDetail.getStatus()==status){
                    orderDetails.add(orderDetail);
                }
            }
            OrderList orderList=new OrderList();
            orderList.setOrderDetails(orderDetails.toArray(new OrderDetail[orderDetails.size()]));
            return orderList;
        }
    }

    public OrderDetail createOrder(int addrId, int uid){
        List<Cart> carts=cartsMapper.getByUid(uid);
        if(carts.size()==0){
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setStatus(-1);
            return orderDetail;
        }
        else{
            for(Cart cart:carts){
                if(productsMapper.getProductById(cart.getProductId()).getStatus()==3) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setStatus(-2);
                    return orderDetail;
                }
            }
            Order order=new Order();
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            String s = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);
            s += uid;
            Long orderNo = Long.parseLong(s);
            order.setOrderNo(orderNo);
            order.setAddrId(addrId);
            order.setCreated(date);
            order.setStatus(1);
            order.setType(1);
            order.setFreight(0);
            int amount=0;
            for(Cart cart:carts){
                amount+=(cart.getQuantity()*productsMapper.getProductById(cart.getProductId()).getPrice());
            }
            order.setAmount(amount);
            order.setUid(uid);
            ordersMapper.insertOrders(order);
            int orderid=ordersMapper.getByOrderno(orderNo).getId();
            for(Cart cart:carts){
                Item item=new Item();
                item.setUid(uid);
                item.setOrderId(orderid);
                item.setGoodsId(cart.getProductId());
                Products products=productsMapper.getProductById(cart.getProductId());
                item.setGoodsName(products.getName());
                item.setIconUrl(products.getIconUrl());
                item.setPrice(products.getPrice());
                item.setQuantity(cart.getQuantity());
                item.setTotalPrice(products.getPrice()*cart.getQuantity());
                item.setCreateTime(date);
                itemsMapper.insertItem(item);
            }
            Item[] items=itemsMapper.getByOrderid(orderid);
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

    public OrderDetail createOneOrder(int addrid,int productid,int quantity,int uid) {
        Order order = new Order();
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String s = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);
        s += uid;
        Long orderNo = Long.parseLong(s);
        order.setOrderNo(orderNo);
        order.setAddrId(addrid);
        order.setCreated(date);
        order.setStatus(1);
        order.setType(1);
        order.setFreight(0);
        int amount = 0;
        amount += (quantity * productsMapper.getProductById(productid).getPrice());
        order.setAmount(amount);
        order.setUid(uid);
        ordersMapper.insertOrders(order);
        int orderid = ordersMapper.getByOrderno(orderNo).getId();
        Item item = new Item();
        item.setUid(uid);
        item.setOrderId(orderid);
        item.setGoodsId(productid);
        Products products = productsMapper.getProductById(productid);
        item.setGoodsName(products.getName());
        item.setIconUrl(products.getIconUrl());
        item.setPrice(products.getPrice());
        item.setQuantity(quantity);
        item.setTotalPrice(products.getPrice() * quantity);
        item.setCreateTime(date);
        itemsMapper.insertItem(item);
        Item[] items = itemsMapper.getByOrderid(orderid);
        Address address = addressMapper.getByAddrid(addrid);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setAddress(address);
        orderDetail.setAddrId(addrid);
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
        if (order.getStatus() == 1) {
            orderDetail.setStatusDesc("未付款");
        } else if (order.getStatus() == 2) {
            orderDetail.setStatusDesc("已付款");
        } else if (order.getStatus() == 3) {
            orderDetail.setStatusDesc("已发货");
        } else if (order.getStatus() == 4) {
            orderDetail.setStatusDesc("交易成功");
        } else if (order.getStatus() == 5) {
            orderDetail.setStatusDesc("交易关闭");
        } else if (order.getStatus() == 6) {
            orderDetail.setStatusDesc("已取消");
        }
        orderDetail.setDeliveryName(address.getName());
        return orderDetail;
    }

    public OrderList getAllList(){
        ArrayList<OrderDetail> orderDetails=new ArrayList<OrderDetail>();
        Order[] orders=ordersMapper.getAll().toArray(new Order[ordersMapper.getAll().size()]);
        for(Order order:orders){
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
            orderDetail.setOrderNo(order.getOrderNo());
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
            orderDetails.add(orderDetail);
        }
        OrderList orderList=new OrderList();
        orderList.setOrderDetails(orderDetails.toArray(new OrderDetail[orderDetails.size()]));
        return orderList;
    }
}
