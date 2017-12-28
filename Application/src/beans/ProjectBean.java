package beans;

import models.daos.ProjectDao;
import models.entities.Project;
import models.services.ProjectService;

import javax.faces.bean.ManagedBean;
import java.util.Date;

@ManagedBean
public class ProjectBean
{
    private int projectId;
    private Date date;
    private double projectBalance;
    private String projectDesc;
    private String projectImage;
    private String projectName;
    private String projectStatus;
    private double projectTarget;
    private String category;
    private String creatorUser;
    private String percentageCompletion;

    public String viewProject(String projectName)
    {
        ProjectService getSingleProjectService = new ProjectDao();
        Project project = getSingleProjectService.getProjectByName(projectName);
        this.projectId = project.getProjectId();
        this.date = project.getDate();
        this.projectBalance = project.getProjectBalance();
        this.projectDesc = project.getProjectDesc();
        this.projectImage = project.getProjectImage();
        this.projectName = project.getProjectName();
        this.projectStatus = project.getProjectStatus();
        this.projectTarget = project.getProjectTarget();
        this.category = project.getCategoryId().getCategoryName();
        this.creatorUser = project.getUserId().getUserUsername();
        this.percentageCompletion = Double.toString((this.projectBalance/this.projectTarget) * 100.00);
        return "single-project";
    }

    public int getProjectId()
    {
        return projectId;
    }

    public void setProjectId(int projectId)
    {
        this.projectId = projectId;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public double getProjectBalance()
    {
        return projectBalance;
    }

    public void setProjectBalance(double projectBalance)
    {
        this.projectBalance = projectBalance;
    }

    public String getProjectDesc()
    {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc)
    {
        this.projectDesc = projectDesc;
    }

    public String getProjectImage()
    {
        return projectImage;
    }

    public void setProjectImage(String projectImage)
    {
        this.projectImage = projectImage;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public String getProjectStatus()
    {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus)
    {
        this.projectStatus = projectStatus;
    }

    public double getProjectTarget()
    {
        return projectTarget;
    }

    public void setProjectTarget(double projectTarget)
    {
        this.projectTarget = projectTarget;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCreatorUser()
    {
        return creatorUser;
    }

    public void setCreatorUser(String creatorUser)
    {
        this.creatorUser = creatorUser;
    }

    public String getPercentageCompletion()
    {
        return percentageCompletion;
    }

    public void setPercentageCompletion(String percentageCompletion)
    {
        this.percentageCompletion = percentageCompletion;
    }
}
