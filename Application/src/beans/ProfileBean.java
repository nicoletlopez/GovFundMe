package beans;

import models.daos.SignupDao;
import models.daos.UserDao;
import models.entities.Card;
import models.entities.User;
import models.services.ProfileService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;

@ManagedBean
public class ProfileBean implements Serializable
{
    private String userName;
    private String fName;
    private String lName;
    private String email;
    private String ccNum;
    private String cardType;
    private double cardBalance;

    public User getProjects() {
        return projects;
    }

    public void setProjects(User projects) {
        this.projects = projects;
    }

    private User projects;

    @ManagedProperty(value="#{authBean}")
    private AuthBean authBean;

    public String viewProfile()
    {
        ProfileService profileService = new UserDao();
        User user = profileService.viewProfile(this.authBean.getLoggedUsername());

        /*get the card object from the user object*/
        Card userCard = user.getCardId();

        this.fName = user.getUserFname();
        this.lName = user.getUserLname();
        this.email = user.getUserEmail();

        this.ccNum = userCard.getCcNum();
        this.cardType = userCard.getTypeId().getTypeName();
        this.cardBalance = userCard.getCardBalance();
        return "my-account";
    }

    public String getfName()
    {
        return fName;
    }

    public void setfName(String fName)
    {
        this.fName = fName;
    }

    public String getlName()
    {
        return lName;
    }

    public void setlName(String lName)
    {
        this.lName = lName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getCcNum()
    {
        return ccNum;
    }

    public void setCcNum(String ccNum)
    {
        this.ccNum = ccNum;
    }

    public String getCardType()
    {
        return cardType;
    }

    public void setCardType(String cardType)
    {
        this.cardType = cardType;
    }

    public double getCardBalance()
    {
        return cardBalance;
    }

    public void setCardBalance(double cardBalance)
    {
        this.cardBalance = cardBalance;
    }

    public AuthBean getAuthBean()
    {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean)
    {
        this.authBean = authBean;
    }
}
