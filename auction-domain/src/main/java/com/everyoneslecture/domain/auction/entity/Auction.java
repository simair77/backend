package com.everyoneslecture.domain.auction.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.everyoneslecture.domain.auction.enums.AuctionStatus;
import com.everyoneslecture.domain.auction.entity.Auction;
import com.everyoneslecture.domain.auction.repository.AuctionRepository;
import com.everyoneslecture.AuctionApplication;

import javax.persistence.*;

@Entity()
@Table(name = "auction")




public class Auction {     // Entity. Domain Class.

    // public Auction(Long lectId){

    //     this.lectId = lectId;
    // }

    // public Auction(){

    // }

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
        public Long getId() {
            return id;
        }

    Long lectId;

    public Long getLectId() {
        return lectId;
    }
    public void setLectId(Long lectId) {
        this.lectId = lectId;
    }

    String lectName;

    public String getLectName() {
        return lectName;
    }
    public void setLectName(String lectName) {
        this.lectName = lectName;
    }

    String lectStatus;

    public String getLectStatus() {
        return lectStatus;
    }
    public void setLectStatus(String lectStatus) {
        this.lectStatus = lectStatus;
    }





    Date startAuctionDate;

        public Date getStartAuctionDate() {
            return startAuctionDate;
        }
        public void setStartAuctionDate(Date startAuctionDate) {
            this.startAuctionDate = startAuctionDate;
        }

    Date endAuctionDate;
        public Date getEndAuctionDate() {
            return endAuctionDate;
        }
        public void setEndAuctionDate(Date endAuctionDate) {
            this.endAuctionDate = endAuctionDate;
        }
    //처음은 무조건 등록
    //AuctionStatus auctionStatus = AuctionStatus.REGIST;

    @Enumerated(EnumType.STRING)
    private AuctionStatus auctionStatus;
    public AuctionStatus getAuctionStatus() {
        return auctionStatus;
    }
    public void setAuctionStatus(AuctionStatus auctionStatus) {
        this.auctionStatus = auctionStatus;
    }


    public String cancel() {
        //answer must be obtained by UI

        setAuctionStatus(AuctionStatus.CANCEL); //취소
        return "경매가 취소되었읍니다.";
    }

    public String startAuction() {
        //answer must be obtained by UI

        setAuctionStatus(AuctionStatus.AUCTION); //취소
        return "경매가 시작되었읍니다.";
    }

    @Override
    public String toString() {

        return "<a href='./"+this.getClass().getSimpleName().toLowerCase()+"'" + ">" + this.getClass().getSimpleName() + "</a>";
    }

    public static AuctionRepository repository() {
        AuctionRepository auctionRepository = AuctionApplication.applicationContext.getBean(AuctionRepository.class);
        return auctionRepository;
    }

}
