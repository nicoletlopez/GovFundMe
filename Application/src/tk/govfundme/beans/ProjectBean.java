package tk.govfundme.beans;

import tk.govfundme.models.daos.DonationDao;
import tk.govfundme.models.daos.servicelayers.DonationServiceLayer;
import tk.govfundme.models.daos.ProjectDao;
import tk.govfundme.models.entities.Project;
import tk.govfundme.models.services.DonationService;
import tk.govfundme.models.services.ProjectService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.Date;

@ManagedBean
@SessionScoped
public class ProjectBean implements Serializable
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
    private double percentageCompletion;
    private double donation;
    private String infoMessage;
    private String creatorEmail;

    @ManagedProperty(value = "#{authBean}")
    private AuthBean user;

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
        this.percentageCompletion = this.projectBalance/this.projectTarget * 100.00;
        this.creatorEmail = project.getUserId().getUserEmail();
        return "single-project";
    }

    public void donateToProject(String projectName)
    {
        DonationServiceLayer donationObjectInBean = new DonationDao();

        /*ANOTHER DUMB WORKAROUND*/
        ProjectService getSingleProjectService = new ProjectDao();
        Project project = getSingleProjectService.getProjectByName(projectName);
        /*ANOTHER DUMB WORKAROUND*/

        if(donationObjectInBean.donate(this.user.getLoggedUsername(),project,this.donation))
        {
            /*Add the funds deducted from the card to the project*/
            DonationService addToBalanceObject = new ProjectDao();
            addToBalanceObject.addToProjectBalance(project,donation);

            this.infoMessage = "Successfully donated " + donation + " to project "  + projectName;
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
            this.creatorEmail = project.getUserId().getUserEmail();
            this.percentageCompletion =this.projectBalance/this.projectTarget * 100.00;
            //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            viewProject(this.projectName);
        }
        else
        {
            this.infoMessage = "Insufficient Funds for project "+projectName;
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
            this.creatorEmail = project.getUserId().getUserEmail();
            this.percentageCompletion = this.projectBalance/this.projectTarget * 100.00;
            viewProject(this.projectName);
        }

    }
    public String redirect()
    {
        return "index";
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

    public double getPercentageCompletion()
    {
        this.percentageCompletion = (this.projectBalance/this.projectTarget) * 100;
        return percentageCompletion;
    }

    public void setPercentageCompletion(double percentageCompletion)
    {
        this.percentageCompletion = percentageCompletion;
    }

    public AuthBean getUser()
    {
        return user;
    }

    public void setUser(AuthBean user)
    {
        this.user = user;
    }

    public double getDonation()
    {
        return donation;
    }

    public void setDonation(double donation)
    {
        this.donation = donation;
    }

    public String getInfoMessage()
    {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage)
    {
        this.infoMessage = infoMessage;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }
}
