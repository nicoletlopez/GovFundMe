package beans;

import models.daos.LoginDao;
import models.daos.UserDao;
import models.entities.User;
import models.services.LoginService;
import models.services.ProfileService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

@ManagedBean
public class LoginBean
{
    private String username;
    private String password;

    private String profilePic;

    @ManagedProperty(value = "#{authBean}")
    private AuthBean authBean;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public AuthBean getAuthBean()
    {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean)
    {
        this.authBean = authBean;
    }

    private LoginService loginService = new LoginDao();

    public String login()
    {
        ProfileService profileService = new UserDao();
        if (loginService.login(this.username, this.password))
        {
            authBean.setLoggedUsername(this.username);
            User user = profileService.viewProfile(this.authBean.getLoggedUsername());
            this.profilePic = user.getUserProfilePic();
            System.out.println(profilePic);
            return "index";
        }
        else
        {
            return "login";
        }
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

}
