package rs.ac.uns.ftn.isa.fisherman.dto;

public class ImageDto {
    private Long Id;
    private String url;
    private String cabin;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUrl() {
        return url;
    }
    public ImageDto(){
    }

    public ImageDto(Long id, String url, String cabin) {
        Id = id;
        this.url = url;
        this.cabin = cabin;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }
}
