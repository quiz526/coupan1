package coupan;

import coupan.config.kafka.KafkaProcessor;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_PreparePayment(@Payload Ordered ordered){

        if(ordered.isMe()){            
            Payment pay = new Payment();
            pay.setOrderId(ordered.getId());
            pay.setCouponId(ordered.getCouponId());        
            pay.setStatus("PayCompleted");
            pay.setPayAmt(ordered.getAmt() * ordered.getQty());

            SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHHmmss");
            String payDate = format1.format (System.currentTimeMillis());
            pay.setPayDate(payDate);
            
            paymentRepository.save(pay);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCancelled_CancelPayment(@Payload OrderCancelled orderCancelled){

        if(orderCancelled.isMe()) {
            Payment pay = paymentRepository.findByOrderId(Long.valueOf(orderCancelled.getId()));
            pay.setPayCancelDate(orderCancelled.getOrderCancelDate());
            paymentRepository.save(pay);  


        }

    }



    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
