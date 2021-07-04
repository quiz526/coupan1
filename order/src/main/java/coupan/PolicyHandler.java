package coupan;

import coupan.config.kafka.KafkaProcessor;

import java.util.Optional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayed_UpdateStatus(@Payload Payed payed){

        if(payed.isMe()){  
            if( payed.getPayCancelDate() == null )  //결제취소가 아닌 경우에만 결제시스템으로 status로 처리
            {    
                Optional<Order> optionalOrder = orderRepository.findById(payed.getOrderId());
                Order order = optionalOrder.get();
                order.setStatus(payed.getStatus());
                orderRepository.save(order);
            }
          }
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
