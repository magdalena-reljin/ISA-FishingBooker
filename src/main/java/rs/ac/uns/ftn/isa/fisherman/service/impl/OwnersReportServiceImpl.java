package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.mail.AdminReviewResponse;
import rs.ac.uns.ftn.isa.fisherman.mail.MailService;
import rs.ac.uns.ftn.isa.fisherman.model.OwnersReport;
import rs.ac.uns.ftn.isa.fisherman.repository.OwnersReportRepository;
import rs.ac.uns.ftn.isa.fisherman.service.OwnersReportService;

import javax.mail.MessagingException;
import java.util.Set;

@Service
public class OwnersReportServiceImpl implements OwnersReportService {

    @Autowired
    private MailService mailService;

    private final Logger logger= LoggerFactory.getLogger(FirebaseServiceImpl.class);
 @Autowired
 private OwnersReportRepository ownersReportRepository;
    @Override
    public Set<OwnersReport> getAllReports() {
        return  ownersReportRepository.getAllReports();
    }

    @Override
    public void sendReviewResponse(String clientUsername, String ownersUsername, String comment) {
        sendMailNotification("dajanazlokapa1@gmail.com",comment);
        sendMailNotification("reljin.magdalena@gmail.com",comment);
    }


    @Override
    public void setReviewStatus(Long id){
       OwnersReport ownersReport= ownersReportRepository.getById(id);
       ownersReport.setApproved(true);
       ownersReportRepository.save(ownersReport);
    }


    private void sendMailNotification(String username,String adminMessage) {
        {
            try {
                String message = adminMessage;
                mailService.sendMail(username, message, new AdminReviewResponse());
            } catch (MessagingException e) {
                logger.error(e.toString());
            }

        }
    }
}
