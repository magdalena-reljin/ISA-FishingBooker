package rs.ac.uns.ftn.isa.fisherman.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.mail.MailService;
import rs.ac.uns.ftn.isa.fisherman.mail.QuickActionCabinInfo;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;
import rs.ac.uns.ftn.isa.fisherman.model.CabinSubscription;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationBoat;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationCabin;
import rs.ac.uns.ftn.isa.fisherman.repository.QuickReservationCabinRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableCabinPeriodService;
import rs.ac.uns.ftn.isa.fisherman.service.CabinSubscriptionService;
import rs.ac.uns.ftn.isa.fisherman.service.QuickReservationCabinService;
import rs.ac.uns.ftn.isa.fisherman.service.ReservationCabinService;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class QuickReservationCabinServiceImpl implements QuickReservationCabinService {

    @Autowired
    private AvailableCabinPeriodService availableCabinPeriodService;
    @Autowired
    private ReservationCabinService reservationCabinService;
    @Autowired
    private QuickReservationCabinRepository quickReservationCabinRepository;
    @Autowired
    private CabinSubscriptionService cabinSubscriptionService;
    @Autowired
    private MailService mailService;
    private final Logger logger= LoggerFactory.getLogger(FirebaseServiceImpl.class);
    @Override
    public boolean ownerCreates(QuickReservationCabin quickReservationCabin) {
        if(!validateForReservation(quickReservationCabin)) return false;

        QuickReservationCabin successfullQuickReservation=new QuickReservationCabin(quickReservationCabin.getId(),quickReservationCabin.getStartDate(),
                quickReservationCabin.getEndDate(),null,quickReservationCabin.getPaymentInformation(),
                quickReservationCabin.getOwnersReport(),quickReservationCabin.getCabin(),quickReservationCabin.getDiscount(),null);
        quickReservationCabinRepository.save(successfullQuickReservation);
        if(quickReservationCabin.getAddedAdditionalServices()!=null){
            successfullQuickReservation.setAddedAdditionalServices(quickReservationCabin.getAddedAdditionalServices());
            quickReservationCabinRepository.save(successfullQuickReservation);
        }
        //TO DO: poslati mejl onima koji su pretplaceni na akcije od tog cabina
        sendMailNotificationToSubscribedUsers(successfullQuickReservation.getCabin().getId(),successfullQuickReservation.getCabin().getName());
        return true;
    }
    @Override
    public Set<QuickReservationCabin> getAllReports(){
        return  quickReservationCabinRepository.getAllReports();
    }

    @Override
    public Integer countReservationsInPeriod(LocalDateTime startWeek, LocalDateTime endWeek, Long ownerId) {
        return quickReservationCabinRepository.countReservationsOfThisWeek(startWeek,endWeek,ownerId);
    }

    @Override
    public Double sumProfit(Long ownerId, LocalDateTime start, LocalDateTime end) {
        return quickReservationCabinRepository.sumProfit(ownerId,start,end);
    }

    private void sendMailNotificationToSubscribedUsers(Long cabinId,String cabinName){
        Set<String> subscriptionEmails=cabinSubscriptionService.findCabinSubscribers(cabinId);
        for(String email: subscriptionEmails) {
            try {
                String message = cabinName;
                mailService.sendMail(email, message, new QuickActionCabinInfo());
            } catch (MessagingException e) {
                logger.error(e.toString());
            }
        }
    }

    @Override
    public Set<QuickReservationCabin> getByCabinId(Long cabinId) {
        return quickReservationCabinRepository.getByCabinId(cabinId);
    }

    @Override
    public boolean quickReservationExists(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        return quickReservationCabinRepository.quickReservationExists(id,startDate,endDate);
    }

    @Override
    public boolean futureQuickReservationsExist(LocalDateTime currentDate, Long id) {
        return quickReservationCabinRepository.futureQuickReservationsExist(currentDate,id);
    }

    @Override
    public Set<QuickReservationCabin> findReservationsByOwnerId(Long id) {
        return quickReservationCabinRepository.findReservationsByOwnerId(id,LocalDateTime.now());
    }

    @Override
    public Set<QuickReservationCabin> getPastReservations(Long id) {
        return quickReservationCabinRepository.getPastReservations(id,LocalDateTime.now());
    }

    @Override
    public void ownerCreatesReview(QuickReservationCabin reservation, boolean successfull) {
        QuickReservationCabin quickReservationCabin=quickReservationCabinRepository.getById(reservation.getId());
        quickReservationCabin.setSuccessfull(successfull);
        quickReservationCabin.getOwnersReport().setComment(reservation.getOwnersReport().getComment());
        quickReservationCabin.getOwnersReport().setBadComment(reservation.getOwnersReport().isBadComment());
        quickReservationCabinRepository.save(quickReservationCabin);
    }

    private boolean validateForReservation(QuickReservationCabin cabinQuickReservation){
        if(!availableCabinPeriodService.cabinIsAvailable(cabinQuickReservation.getCabin()
                .getId(),cabinQuickReservation.getStartDate(),cabinQuickReservation.getEndDate())) return false;

        if(reservationCabinService.reservationExists(cabinQuickReservation.getCabin()
                .getId(),cabinQuickReservation.getStartDate(),cabinQuickReservation.getEndDate())) return false;

        if(quickReservationCabinRepository.quickReservationExists(cabinQuickReservation.getCabin()
                .getId(),cabinQuickReservation.getStartDate(),cabinQuickReservation.getEndDate())) return false;

        return true;
    }
}
