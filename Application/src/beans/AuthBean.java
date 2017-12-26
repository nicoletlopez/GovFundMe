package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class AuthBean implements Serializable
{
    private String loggedUsername;

    public String getLoggedUsername()
    {
        return loggedUsername;
    }

    public void setLoggedUsername(String loggedUsername)
    {
        this.loggedUsername = loggedUsername;
    }

    public String logout()
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        System.out.println("Logged out");
        return "index?faces-redirect=true";
    }
}
