package beans;

import models.daos.UserDao;
import models.entities.User;
import models.services.ProfileService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
public class ProfilePictureBean {

    private String profilePic;

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
    @ManagedProperty(value = "#{authBean}")
    private AuthBean authBean;

    public AuthBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }

    public String profiler(){
        ProfileService profileService = new UserDao();
        User user = profileService.viewProfile(this.authBean.getLoggedUsername());
        this.profilePic = user.getUserProfilePic();
        if(profilePic == null || profilePic.isEmpty()){
            this.profilePic = "default.jpg";
        }
        //System.out.println("Output is below");
        return profilePic;
    }
/*
    public static void main(String[] args) {
        ProfilePictureBean yes = new ProfilePictureBean();
        System.out.println(yes.profiler());
    }
    */
}
