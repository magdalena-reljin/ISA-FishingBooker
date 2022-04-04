package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;
@Embeddable
public class OwnersReport {

    @Column(name="badComment")
    private boolean badComment;
    @Column(name="comment")
    private String comment;

    public OwnersReport() {

    }

    public OwnersReport(boolean badComment, String comment) {
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
