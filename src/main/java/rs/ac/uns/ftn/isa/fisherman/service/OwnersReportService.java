package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.OwnersReport;

import java.util.Set;

public interface OwnersReportService {

    Set<OwnersReport> getAllReports();

    void sendReviewResponse(String clientUsername, String ownersUsername, String comment);

    void setReviewStatus(Long id);

}
