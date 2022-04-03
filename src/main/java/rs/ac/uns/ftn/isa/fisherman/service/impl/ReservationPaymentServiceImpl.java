package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.enums.RankType;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.service.RankService;
import rs.ac.uns.ftn.isa.fisherman.service.ReservationPaymentService;
import rs.ac.uns.ftn.isa.fisherman.service.ReservationPointsService;
import rs.ac.uns.ftn.isa.fisherman.service.UserService;

@Service
public class ReservationPaymentServiceImpl implements ReservationPaymentService {
    @Autowired
    private ReservationPointsService reservationPointsService;
    @Autowired
    private RankService rankService;
    @Autowired
    private UserService userService;

    @Override
    public PaymentInformation setTotalPaymentAmount(Reservation reservation,User user){
        PaymentInformation paymentInformation = new PaymentInformation();
        Double newTotalPrice = calculateClientsDiscount(reservation.getClient(),reservation.getPaymentInformation().getTotalPrice());
        paymentInformation.setTotalPrice(newTotalPrice);


        Double ownersPart= calculateOwnersPart(newTotalPrice,user);
        paymentInformation.setOwnersPart(ownersPart);
        paymentInformation.setCompanysPart(newTotalPrice-ownersPart);
        return  paymentInformation;
    }

    private Double calculateOwnersPart(Double newTotalPrice, User user) {
        ReservationPoints points = reservationPointsService.get();
        Integer instructorsDiscount= rankService.getDiscountByRank(user.getUserRank().getRankType().ordinal());
        Integer appPercentage= points.getAppProfitPercentage();
        Double appsPartWithoutDiscount = appPercentage/100.00 * newTotalPrice;
        if(instructorsDiscount==0)
            return newTotalPrice-appsPartWithoutDiscount;
        return  newTotalPrice -(appsPartWithoutDiscount-instructorsDiscount/100.00* appsPartWithoutDiscount);
    }

    private Double calculateClientsDiscount(Client client, Double totalPrice) {
        Integer clientDiscount= rankService.getDiscountByRank(client.getUserRank().getRankType().ordinal());
        if(clientDiscount==0)
            return  totalPrice;

        return totalPrice-clientDiscount/100.00 * totalPrice;
    }

    @Override
    public void updateUserRankAfterReservation(Client client, User user) {
        ReservationPoints points = reservationPointsService.get();
        Integer clientPoints= client.getUserRank().getCurrentPoints()+points.getClientPoints();
        Integer userPoints = user.getUserRank().getCurrentPoints()+points.getOwnerPoints();
        user.getUserRank().setCurrentPoints(userPoints);
        client.getUserRank().setCurrentPoints(clientPoints);
        Integer silverPoints= rankService.getPointsByRank(1);
        Integer goldPoints = rankService.getPointsByRank(2);
        client.getUserRank().setRankType(updateRankType(clientPoints,silverPoints,goldPoints));
        user.getUserRank().setRankType(updateRankType(userPoints,silverPoints,goldPoints));
        userService.save(client);
        userService.save(user);
    }

    @Override
    public void resetLoyaltyStatusAfterCancellation(Client client, User user) {
        ReservationPoints points = reservationPointsService.get();
        Integer clientPoints= 0;
        Integer userPoints =0;
        if(clientPoints > 0)
            clientPoints=client.getUserRank().getCurrentPoints()-points.getClientPoints();

        if(userPoints>0)
            userPoints = user.getUserRank().getCurrentPoints()-points.getOwnerPoints();
        
        client.getUserRank().setCurrentPoints(clientPoints);
        user.getUserRank().setCurrentPoints(userPoints);

        Integer silverPoints= rankService.getPointsByRank(1);
        Integer goldPoints = rankService.getPointsByRank(2);
        client.getUserRank().setRankType(updateRankType(clientPoints,silverPoints,goldPoints));
        user.getUserRank().setRankType(updateRankType(userPoints,silverPoints,goldPoints));
        userService.save(client);
        userService.save(user);

    }

    private RankType updateRankType(Integer points, Integer silverPoints, Integer goldPoints){
        if(points>=silverPoints  && points<goldPoints){
            return  RankType.SILVER;
        }else if( points >= goldPoints){
            return  RankType.GOLD;
        }
        return  RankType.BRONZE;
    }
}
