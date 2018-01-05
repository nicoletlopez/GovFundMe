package tk.govfundme.beans;

import tk.govfundme.models.daos.CategoryDao;
import tk.govfundme.models.daos.ProjectDao;
import tk.govfundme.models.services.CategoryService;
import tk.govfundme.models.services.ProjectService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class CreateProjectBean implements Serializable
{
    private String projectName;
    private String projectCategory;
    private String projectImage;
    private String projectDesc;
    private double projectTarget;
    private Part image;
    public ArrayList<String> infoMessage;
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

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    public ArrayList<String> getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(ArrayList<String> infoMessage) {
        this.infoMessage = infoMessage;
    }
    public String createProject()
    {
        ProjectService projectService = new ProjectDao();
        UploadBean uploadBean = new UploadBean();
        String projectImageFileName = uploadBean.doUpload(image);

        if(validateFields()) {
            if (projectImageFileName.equals("")) {
                if (projectService.createProject(projectName, projectCategory, "defaultproj.jpg", projectDesc, projectTarget, loggedUsername)) {
                    return "index";
                } else {
                    return "start-project";
                }
            } else {
                if (projectService.createProject(projectName, projectCategory, projectImageFileName, projectDesc, projectTarget, loggedUsername)) {
                    return "index";
                } else {
                    return "start-project";
                }
            }
        }else{
            return "start-project";
        }
    }
    private boolean validateFields() {
        if (this.projectName == null || this.image == null || this.projectDesc == null || (this.projectTarget <= 0)) {
            if (this.projectName == null) {
                this.infoMessage.add("Project name required");
            } else if (this.image == null) {
                this.infoMessage.add("Project image required");
            } else if (this.projectDesc == null) {
                this.infoMessage.add("Project description required");
            } else if (((Double) this.projectTarget).isNaN()) {
                this.infoMessage.add("Target must be at least 1");
            }
            return false;
        } else {
            return true;
        }
    }
}
