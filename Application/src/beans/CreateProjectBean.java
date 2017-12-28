package beans;

import models.daos.CategoryDao;
import models.daos.ProjectDao;
import models.services.CategoryService;
import models.services.ProjectService;

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

    private List<String> categoriesList;

    public CreateProjectBean()
    {
        CategoryService getCategoriesServiceObject = new CategoryDao();
        categoriesList = getCategoriesServiceObject.getCategories();
    }

    @ManagedProperty(value="#{authBean.loggedUsername}")
    private String loggedUsername;

    public List<String> getCategoriesList()
    {
        return categoriesList;
    }

    public void setCategoriesList(List<String> categoriesList)
    {
        this.categoriesList = categoriesList;
    }

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


    public String createProject()
    {
        ProjectService projectService = new ProjectDao();
        if(projectService.createProject(projectName,projectCategory,projectImage,projectDesc,projectTarget,loggedUsername))
        {
            return "index";
        }
        else
        {
            return "projects";
        }
    }
}
