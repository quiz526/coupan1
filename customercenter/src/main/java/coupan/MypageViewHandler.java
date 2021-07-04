package coupan;

import coupan.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MypageViewHandler {


    @Autowired
    private MypageRepository mypageRepository;

  
    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_CREATE_1 (@Payload Ordered ordered) {
        try {

            if (ordered.isMe()) {            

                // view 객체 생성
                Mypage mypage = new Mypage();
                // view 객체에 이벤트의 Value 를 set 함
                mypage.setOrderId(ordered.getId());
                mypage.setCustomerId(ordered.getCustomerId());
                mypage.setStatus(ordered.getStatus());
                mypage.setQty(ordered.getQty());
                mypage.setOrderDate(ordered.getOrderDate());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayed_then_UPDATE_1(@Payload Payed payed) {
        try {
            if (payed.isMe()) {
                // view 객체 조회
                List<Mypage> mypageList = mypageRepository.findByOrderId(payed.getOrderId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setPayAmt(payed.getPayAmt());
                    mypage.setPayDate(payed.getPayDate());
                    mypage.setStatus(payed.getStatus());
                    mypage.setPayCancelDate(payed.getPayCancelDate());
                    // view 레파지 토리에 save
                    mypageRepository.save(mypage);
                }
            }   

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCancelled_then_UPDATE_2(@Payload OrderCancelled orderCancelled) {
        try {
            if (orderCancelled.isMe()){
                // view 객체 조회
                List<Mypage> mypageList = mypageRepository.findByOrderId(orderCancelled.getId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setOrderCancelDate(orderCancelled.getOrderCancelDate());
                    mypage.setStatus(orderCancelled.getStatus());
                    // view 레파지 토리에 save
                    mypageRepository.save(mypage);
                }
            }    

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}