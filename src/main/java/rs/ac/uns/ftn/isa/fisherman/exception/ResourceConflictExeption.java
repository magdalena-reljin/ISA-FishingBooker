package rs.ac.uns.ftn.isa.fisherman.exception;

// Custom izuzetak
public class ResourceConflictExeption extends RuntimeException {
    private static final long serialVersionUID = 1791564636123821405L;

    private Long resourceId;

    public ResourceConflictExeption(Long resourceId, String message) {
        super(message);
        this.setResourceId(resourceId);
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

}