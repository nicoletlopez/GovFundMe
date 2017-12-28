package beans;

import com.sun.source.doctree.SerialDataTree;
import models.daos.CreateProjectDao;
import models.entities.Category;
import models.services.CreateProjectService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.List;

@ManagedBean
public class CreateProjectBean implements Serializable
{
    private String projectName;
    private String projectCategory;
    private String projectImage;
    private String projectDesc;
    private double projectTarget;


    public CreateProjectBean()
    {
        CreateProjectService retrieveAllCategories = new CreateProjectDao();
        categories = retrieveAllCategories.getAllCategories();
    }


    @ManagedProperty(value="#{authBean.loggedUsername}")
    private String loggedUsername;

    private List<String> categories;

    public String getLoggedUsername()
    {
        return loggedUsername;
    }

    public void setLoggedUsername(String loggedUsername)
    {
        this.loggedUsername = loggedUsername;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public String getProjectCategory()
    {
        return projectCategory;
    }

    public void setProjectCategory(String projectCategory)
    {
        this.projectCategory = projectCategory;
    }

    public String getProjectImage()
    {
        return projectImage;
    }

    public void setProjectImage(String projectImage)
    {
        this.projectImage = projectImage;
    }

    public String getProjectDesc()
    {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc)
    {
        this.projectDesc = projectDesc;
    }

    public double getProjectTarget()
    {
        return projectTarget;
    }

    public void setProjectTarget(double projectTarget)
    {
        this.projectTarget = projectTarget;
    }

    public List<String> getCategories()
    {
        return categories;
    }

    public void setCategories(List<String> categories)
    {
        this.categories = categories;
    }


    public String createProject()
    {
        CreateProjectService createProjectService = new CreateProjectDao();
        if(createProjectService.createProject(projectName,projectCategory,projectImage,projectDesc,projectTarget,loggedUsername))
        {
            return "index";
        }
        else
        {
            return "projects";
        }
    }
}
