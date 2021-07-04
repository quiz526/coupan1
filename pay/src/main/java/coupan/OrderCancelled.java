package coupan;

public class OrderCancelled extends AbstractEvent {

    private Long id;
    private Long couponId;
    private Long customerId;
    private String status;
    private Integer qty;
    private String orderDate;
    private String orderCancelDate;
    private Integer amt;

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