package beans;

import models.daos.SignupDao;
import models.services.SignupService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.servlet.http.Part;

@ManagedBean
public class SignupBean
{
    private String fname;
    private String lname;
    private String email;
    private String username;
    private String password;
    private String confPass;
    private String ccNum;
    private Part profilePic;



    private String infoMessage;

    @ManagedProperty(value = "#{authBean}")
    private AuthBean authBean;

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

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

    public String getConfPass()
    {
        return confPass;
    }

    public void setConfPass(String confPass)
    {
        this.confPass = confPass;
    }

    public String getCcNum()
    {
        return ccNum;
    }

    public void setCcNum(String ccNum)
    {
        this.ccNum = ccNum;
    }

    public String getInfoMessage()
    {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage)
    {
        this.infoMessage = infoMessage;
    }

    public AuthBean getAuthBean()
    {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean)
    {
        this.authBean = authBean;
    }

    public Part getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Part profilePic) {
        this.profilePic = profilePic;
    }
    private SignupService signupService = new SignupDao();

    public String signup()
    {
        UploadBean uploadBean = new UploadBean();
        String picture = uploadBean.doUpload(profilePic);
        if (this.password.equals(this.confPass))
        {
            if (signupService.signup(this.fname, this.lname, this.email, this.username, this.password, this.ccNum, picture))
            {
                authBean.setLoggedUsername(this.username);
                this.infoMessage = "Successfully registered";
                return "projects";
            }
            else
            {
                this.infoMessage = "Credit card invalid";
                return "create-account";
            }
        }
        else
        {
            this.infoMessage = "Passwords do not match";
            return "create-account";
        }
    }
}