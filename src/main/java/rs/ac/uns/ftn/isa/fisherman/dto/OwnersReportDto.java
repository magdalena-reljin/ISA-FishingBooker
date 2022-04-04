package rs.ac.uns.ftn.isa.fisherman.dto;

import javax.persistence.*;

public class OwnersReportDto {
    private boolean badComment;
    private String comment;

    public OwnersReportDto() {
    }

    public OwnersReportDto(boolean badComment, String comment) {
        this.badComment = badComment;
        this.comment = comment;
    }

    public boolean isBadComment() {
        return badComment;
    }

    public void setBadComment(boolean badComment) {
        this.badComment = badComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
