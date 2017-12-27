package models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Date;

@Entity
public class Project implements Serializable
{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int projectId;

    public int getProjectId()
    {
        return projectId;
    }

    public void setProjectId(int orojectId)
    {
        this.projectId = orojectId;
    }

    @Basic
    private String projectName;

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    @Basic
    private String projectDesc;

    public String getProjectDesc()
    {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc)
    {
        this.projectDesc = projectDesc;
    }

    @Basic
    private double projectBalance;

    public double getProjectBalance()
    {
        return projectBalance;
    }

    public void setProjectBalance(double projectBalance)
    {
        this.projectBalance = projectBalance;
    }

    @Basic
    private double projectTarget;

    public double getProjectTarget()
    {
        return projectTarget;
    }

    public void setProjectTarget(double projectTarget)
    {
        this.projectTarget = projectTarget;
    }

    @Basic
    private String projectStatus;

    public String getProjectStatus()
    {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus)
    {
        this.projectStatus = projectStatus;
    }

    @Basic
    private String projectCategory;

    public String getProjectCategory()
    {
        return projectCategory;
    }

    public void setProjectCategory(String projectCategory)
    {
        this.projectCategory = projectCategory;
    }

    @Basic
    private String projectImage;

    public String getProjectImage()
    {
        return projectImage;
    }

    public void setProjectImage(String projectImage)
    {
        this.projectImage = projectImage;
    }

    @Temporal(TemporalType.DATE)
    private Date date;

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userId")
    private User userId;

    public User getUserId()
    {
        return userId;
    }

    public void setUserId(User userId)
    {
        this.userId = userId;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="DonationReceipt",
            joinColumns = {@JoinColumn(name="projectId")},
            inverseJoinColumns = {@JoinColumn(name="userId")})
    private List<User> usersDonated;

    public List<User> getUsersDonated()
    {
        return usersDonated;
    }

    public void setUsersDonated(List<User> usersDonated)
    {
        this.usersDonated = usersDonated;
    }


}
