package com.everyoneslecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.everyoneslecture.domain.LectureAuctioned;
import com.everyoneslecture.domain.auction.entity.Auction;
import com.everyoneslecture.domain.auction.repository.AuctionRepository;
import com.everyoneslecture.domain.auction.vo.*;
import com.everyoneslecture.kafka.KafkaProcessor;

@Service
public class PolicyHandler {

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @Autowired
    AuctionRepository auctionRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverLectureAuctioned_displayOnTheStore(@Payload LectureAuctioned lectureAuctioned){
        if(!lectureAuctioned.validate())
            return;
        Auction auction = new Auction();
        auction.setLectId(lectureAuctioned.getLectId());
        auction.setLectName(lectureAuctioned.getLectName());
        auction.setLectStatus(lectureAuctioned.getLectStatus());
        auctionRepository.save(auction);
    }

    //회원 정보 변경시 업데이트
    @Autowired
    MemberRepository memberRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMemberJoined(@Payload MemberJoined memberJoined){
        if(!memberJoined.validate())
            return;

        MemberVo memberVo = new MemberVo();
        memberVo.setMemberId(memberJoined.getMemberId());
        memberVo.setLoginId(memberJoined.getLoginId());
        memberVo.setMemberType(memberJoined.getMemberType());
        memberVo.setName(memberJoined.getName());
        memberVo.setMobile(memberJoined.getMobile());
        memberVo.setBirth(memberJoined.getBirth());
        memberRepository.save(memberVo);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMemberUpdated(@Payload MemberUpdated memberUpdated){
        if(!memberUpdated.validate())
            return;

        memberRepository.findByMemberId(memberUpdated.getMemberId()).ifPresent(memberVo->{
            memberVo.setLoginId(memberUpdated.getLoginId());
            memberVo.setMemberType(memberUpdated.getMemberType());
            memberVo.setName(memberUpdated.getName());
            memberVo.setMobile(memberUpdated.getMobile());
            memberVo.setBirth(memberUpdated.getBirth());
            memberRepository.save(memberVo);
        });
    }


    //강의 정보 변경시 업데이트
    @Autowired
    LectureRepository lectureRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverLectureAdded(@Payload LectureAdded lectureAdded){
        if(!lectureAdded.validate())
            return;

        LectureVo lectureVo = new LectureVo();
        lectureVo.setLectId(lectureAdded.getLectId());
        lectureVo.setLectName(lectureAdded.getLectName());
        lectureVo.setLectStatus(lectureAdded.getLectStatus());
        lectureVo.setLectCost(lectureAdded.getLectCost());
        lectureVo.setCntStudent(lectureAdded.getCntStudent());
        lectureVo.setStartLecture(lectureAdded.getStartLecture());
        lectureRepository.save(lectureVo);

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverLectureUpdated(@Payload LectureUpdated lectureUpdated){
        if(!lectureUpdated.validate())
            return;

        LectureVo lectureVo = new LectureVo();
        lectureVo.setLectId(lectureUpdated.getLectId());
        lectureVo.setLectName(lectureUpdated.getLectName());
        lectureVo.setLectStatus(lectureUpdated.getLectStatus());
        lectureVo.setLectCost(lectureUpdated.getLectCost());
        lectureVo.setCntStudent(lectureUpdated.getCntStudent());
        lectureVo.setStartLecture(lectureUpdated.getStartLecture());
        lectureRepository.save(lectureVo);

    }

    ///// *** Example ****

    // @StreamListener(KafkaProcessor.INPUT)
    // public void wheneverChargeStarted_recordHistory(@Payload ChargeStarted chargeStarted){
    //     if(!chargeStarted.validate())
    //         return;

    //     ChargedHistory chargedHistory = new ChargedHistory();

    //     chargedHistory.setChargedCustomer(new ChargedCustomer());
    //     chargedHistory.getChargedCustomer().setCustomerId(chargeStarted.getCustomerId());
    //     chargedHistory.getChargedCustomer().setCustomerName(chargeStarted.getCustomerName());

    //     chargedHistory.setChargerId(chargeStarted.getChargerId());
    //     chargedHistory.setStartTime(chargeStarted.getTimestamp());


    //     chargedHistoryRepository.save(chargedHistory);
    // }


    // @StreamListener(KafkaProcessor.INPUT)
    // public void wheneverChargeEnded_recordHistory(@Payload ChargeEnded chargeEnded){
    //     if(!chargeEnded.validate())
    //         return;

    //     //변경 case
    //     chargedHistoryRepository.findChargerId(chargeEnded.getId()).ifPresent(chargedHistory->{
    //         chargedHistory.setEndTime(chargeEnded.getTimestamp());
    //         chargedHistory.setStatus(ChargeStatus.ENDED);
    //         chargedHistoryRepository.save(item);
    //     });


    //     // 계속 누적

    //     ChargedHistory chargedHistory = new ChargedHistory();

    //     chargedHistory.setChargedCustomer(new ChargedCustomer());
    //     chargedHistory.getChargedCustomer().setCustomerId(chargeStarted.getCustomerId());
    //     chargedHistory.getChargedCustomer().setCustomerName(chargeStarted.getCustomerName());

    //     chargedHistory.setChargerId(chargeStarted.getChargerId());
    //     chargedHistory.setTime(chargeStarted.getTimestamp());
    //     chargedHistory.setHistoryType(HistoryType.CHARGE_ENDED);


    //     chargedHistoryRepository.save(chargedHistory);

    // }



}
