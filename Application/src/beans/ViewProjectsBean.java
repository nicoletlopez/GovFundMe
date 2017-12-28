package beans;

import models.daos.ProjectDao;
import models.entities.Project;
import models.services.ProjectService;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class ViewProjectsBean implements Serializable
{
    private List<Project> projects;

    public String viewAllProjects()
    {
        ProjectService getProjects = new ProjectDao();
        projects = getProjects.getAllProjects();
        return "projects";
    }

    public String viewProjectByCategory(String categoryName)
    {
        ProjectService projectByCategory = new ProjectDao();
        projects = projectByCategory.getProjectsByCategory(categoryName);
        return "projects";
    }

    public List<Project> getProjects()
    {
        return projects;
    }

    public void setProjects(List<Project> projects)
    {
        this.projects = projects;
    }
}
