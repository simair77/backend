package com.everyoneslecture.domain.auction.dto;

import java.util.Date;

public interface AuctionTempDto {
  String getLectId();
  String getLectName();
  String getLectureStatus();
  int getCntStudent();
  Long getLectCost();
  Date getStartLecture();
  String getAuctionStatus();
  Long getAuctionId();
  Date getEndAuctionDate();
  Date getStartAuctionDate();
  int getLectureBidCnt();
  int getBidMinPrice();
}
