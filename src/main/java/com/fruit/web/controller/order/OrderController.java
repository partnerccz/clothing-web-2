package com.fruit.web.controller.order;

import com.fruit.web.base.BaseController;
import com.fruit.web.model.Order;
import com.fruit.web.model.Product;
import com.fruit.web.util.Constant;
import com.fruit.web.util.ConvertUtils;
import com.jfinal.ext2.kit.DateTimeKit;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.*;

/**
 * @Author: ZGC and CCZ
 * @Date Created in 15:52 2018/1/3
 */
public class OrderController extends BaseController {
    private static Logger log = Logger.getLogger(OrderController.class);

    private static final int PAGE_SIZE = 10;

    // 有序id计数
    public static long COUNT = 10000L;

    /**
     * 生成订单(下单)
     */
    public void createOrder(){
        System.out.println("------------生成订单-----------------");
        try {
            //获取前端传过来的商品规格id
            Integer[] ids = getParaValuesToInt("ids");
            Integer uid = getSessionAttr(Constant.SESSION_UID);
            //临时限定100页数的购物车内容的获取
            List<Product> products = Product.dao.listCartProduct(uid, 100, 1);

            //订单总金额
            BigDecimal TotalPay = new BigDecimal(0.00d);
            //生成唯一有序id
            String orderId;
            synchronized (OrderController.class) {
                orderId = DateTimeKit.formatDateToStyle("yyyyMMddhhmmss", new Date()) + "-" + COUNT + Math.random()*10000;
                COUNT++;
            }
            for (Product product : products) {
                //获取购物车商品id
                int standardId = Integer.parseInt(product.get("standard_id").toString());
                for (Integer id : ids) {
                    if (id.equals(standardId)){
                        //buy_num能被取出来是因为所有取出来的内容都被封装在实体对象中了
                        int buy_num = Integer.parseInt(product.get("buy_num").toString());
                        BigDecimal sell_price = ConvertUtils.toBigDecimal(product.get("sell_price"));
                        BigDecimal original_price = ConvertUtils.toBigDecimal(product.get("original_price"));
                        String remark = product.get("remark").toString();
                        String standard_name = product.get("standard_name").toString();
                        //总支付金额,目前只是简单计算,没有加入抵用券等金额修改的操作
                        BigDecimal totalPay =new BigDecimal(buy_num).multiply(sell_price);
                        //添加到总金额中 TODO 未完成
                        TotalPay.add(totalPay);

                        Order order = new Order();
                        //未支付(已下单)
                        order.setStatus(0);
                        //未支付
                        order.setPayStatus(0);
                        order.setProductId(product.getId());
                        order.setProductStandardId(id);
                        order.setProductName(product.getName());
                        order.setProductStandardName(standard_name);
                        order.setCreateTime(new Date());
                        order.setUpdateTime(new Date());
                        order.setNum(buy_num);
                        order.setOrderId(orderId);
                        order.setSellPrice(sell_price);
                        order.setTotalPay(totalPay);
                        order.setFruitType(product.getFruitType());
                        order.setOriginalPrice(original_price);
                        order.setMeasureUnit(product.getMeasureUnit());
                        //暂时为空,等用户确认下单后再调整
                        order.setBuyAddress("");
                        order.setBuyPhone("");
                        order.setBuyUid(uid);
                        order.setBuyRemark(remark);

                        order.save();

                    }
                }
            }
            String totalpay = TotalPay.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "";
            renderText(totalpay);
        } catch (Exception e) {
            renderText("后台异常!!!");
            e.printStackTrace();
        } finally {
        }
    }

    //跳转页面

    public static void main(String[] args) {

    }

    /**
     * 获取到订单列表
     */
    public void getOderList(){
        String order_status=getPara("order_status");
        if("one".equals(order_status)){
            order_status="'0'";
        }else if("two".equals(order_status)){
            order_status="'0','5'";
        }else if("three".equals(order_status)){
            order_status="'10'";
        }else if("four".equals(order_status)){
            order_status="'0','5','10','20','30','40'";
        }

        List<Order> orderList=Order.dao.getOrderListByStatus("1",order_status);
        Map<String,List<Order>> map =new HashMap<String,List<Order>>();
        for (Order order : orderList) {
            if(map.containsKey(order.getOrderId())){
                List<Order> orders=map.get(order.getOrderId());
                orders.add(order);
                map.put(order.getOrderId(),orders);
            }else{
                List<Order> orders=new ArrayList<Order>();
                orders.add(order);
                map.put(order.getOrderId(),orders);
            }
        }
        renderJson(map);
    }

    public void getOrderListDetail(){
        String orderId=getPara("orderId");

    }


}