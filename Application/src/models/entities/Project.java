package models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @ManyToOne
    private User userId;

    public User getUserId()
    {
        return userId;
    }

    public void setUserId(User userId)
    {
        this.userId = userId;
    }

    @ManyToMany
    @JoinTable(name="DonationReceipt",
            joinColumns = {@JoinColumn(name="projectId")},
            inverseJoinColumns = {@JoinColumn(name="UserId")})
    private List<User> usersDonated;

    public List<User> getUsersDonated()
    {
        return usersDonated;
    }

    public void setUsersDonated(List<User> usersDonated)
    {
        this.usersDonated = usersDonated;
    }

    public Project()
    {

    }

    public Project(double projectBalance, String projectCategory, String projectDesc, String projectImage, String projectName, String projectStatus, double projectTarget)
    {

    }
}