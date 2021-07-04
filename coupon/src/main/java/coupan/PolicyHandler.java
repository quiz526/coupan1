package coupan;

import coupan.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired CouponRepository couponRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCancelled_IncreaseStock(@Payload OrderCancelled orderCancelled){

        if(orderCancelled.isMe()) {        
            Coupon coupon = couponRepository.findByCouponId(Long.valueOf(orderCancelled.getCouponId()));
            coupon.setStock(coupon.getStock() + orderCancelled.getQty());
            couponRepository.save(coupon);  
          }
      
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
