package coupan;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Mypage_table")
public class Mypage {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long orderId;
        private Long customerId;
        private Integer qty;
        private String orderDate;
        private String orderCancelDate;
        private Integer payAmt;
        private String payDate;
        private String payCancelDate;
        private String status;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }
        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
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
        public Integer getPayAmt() {
            return payAmt;
        }

        public void setPayAmt(Integer payAmt) {
            this.payAmt = payAmt;
        }
        public String getPayDate() {
            return payDate;
        }

        public void setPayDate(String payDate) {
            this.payDate = payDate;
        }
        public String getPayCancelDate() {
            return payCancelDate;
        }

        public void setPayCancelDate(String payCancelDate) {
            this.payCancelDate = payCancelDate;
        }
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

}
