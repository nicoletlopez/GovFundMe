package tk.govfundme.models.services;

import tk.govfundme.models.entities.Category;
import tk.govfundme.models.entities.Project;

import java.util.List;

public interface ProjectService
{
    boolean createProject(String projectName, String projectCategory, String projectImage, String projectDesc, double projectTarget, String loggedInUserName);
    List<Project> getAllProjects();
    Project getProjectByName(String projectName);
    List<Project> getProjectsByCategory(String projectCategory);
}
