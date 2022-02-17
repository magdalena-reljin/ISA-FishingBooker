package rs.ac.uns.ftn.isa.fisherman.dto;

public class ChangePasswordDto {
    private String oldPassword;
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public ChangePasswordDto() {
    }

    public ChangePasswordDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
