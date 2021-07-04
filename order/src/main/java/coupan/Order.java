package coupan;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long couponId;
    private Long customerId;
    private String status;
    private Integer qty;
    private String orderDate;
    private String orderCancelDate;
    private Integer amt;

    @PostPersist
    public void onPostPersist(){
        boolean rslt = OrderApplication.applicationContext.getBean(coupan.external.CouponService.class)
        .modifyStock(this.getCouponId(), this.getQty());

        if (rslt) {
            this.setStatus("ordered");
            //this.setStatus(System.getenv("STATUS"));

            Ordered ordered = new Ordered();
            SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHHmmss");
            String orderDate = format1.format (System.currentTimeMillis());
            this.setOrderDate(orderDate);
            BeanUtils.copyProperties(this, ordered);
            ordered.publishAfterCommit();            
        }


        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.


    }

    @PreUpdate
    public void onPreUpdate(){
        if(this.getOrderCancelDate() != null)
        {
            OrderCancelled orderCancelled = new OrderCancelled();
            this.setStatus("OrderCancelled");

            SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHHmmss");
            String orderCancelDate = format1.format (System.currentTimeMillis());
            this.setOrderCancelDate(orderCancelDate);
            
            BeanUtils.copyProperties(this, orderCancelled);
            orderCancelled.publishAfterCommit();
        }

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public String getOrderCancelDate() {
        return orderCancelDate;
    }

    public void setOrderCancelDate(String orderCancelDate) {
        this.orderCancelDate = orderCancelDate;
    }
    public Integer getAmt() {
        return amt;
    }

    public void setAmt(Integer amt) {
        this.amt = amt;
    }




}
