package beans;

import models.daos.LoginDao;
import models.services.LoginService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
public class LoginBean
{
    private String username;
    private String password;

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
        if (loginService.login(this.username, this.password))
        {
            authBean.setLoggedUsername(this.username);
            return "projects";
        }
        else
        {
            return "login";
        }
    }
}
