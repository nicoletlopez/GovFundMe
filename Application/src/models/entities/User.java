package models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable
{
    @GeneratedValue
    @Id
    private int userId;

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    @Basic
    private String userLname;

    public String getUserLname()
    {
        return userLname;
    }

    public void setUserLname(String userLname)
    {
        this.userLname = userLname;
    }

    @Basic
    private String userFname;

    public String getUserFname()
    {
        return userFname;
    }

    public void setUserFname(String userFname)
    {
        this.userFname = userFname;
    }

    @Basic
    @Column(unique = true)
    private String userUsername;

    public String getUserUsername()
    {
        return userUsername;
    }

    public void setUserUsername(String userUsername)
    {
        this.userUsername = userUsername;
    }

    @Basic
    private String userPassword;

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    @Basic
    private String userEmail;

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Project> projects;

    public List<Project> getProjects()
    {
        return projects;
    }

    public void setProjects(List<Project> projects)
    {
        this.projects = projects;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="DonationReceipt",
    joinColumns = {@JoinColumn(name="userId")},
    inverseJoinColumns = {@JoinColumn(name="projectId")})
    private List<Project> projectsDonatedTo;

    public List<Project> getProjectsDonatedTo()
    {
        return projectsDonatedTo;
    }

    public void setProjectsDonatedTo(List<Project> projectsDonatedTo)
    {
        this.projectsDonatedTo = projectsDonatedTo;
    }

    /*@Basic
    private boolean isAuthenticated;

    public boolean isAuthenticated()
    {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated)
    {
        isAuthenticated = authenticated;
    }*/

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cardId", unique = true)
    private Card cardId;

    public Card getCardId()
    {
        return cardId;
    }

    public void setCardId(Card cardId)
    {
        this.cardId = cardId;
    }
}
