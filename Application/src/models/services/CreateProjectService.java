package models.services;

import models.entities.Category;

import java.util.List;

public interface CreateProjectService
{
    public boolean createProject(String projectName, String projectCategory, String projectImage, String projectDesc, double projectTarget, String loggedInUserName);
    public List<String> getAllCategories();
}
