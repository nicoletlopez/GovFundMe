package models.services;

import models.entities.User;

public interface CreateProjectService
{
    public boolean createProject(String projectName, String projectCategory, String projectImage, String projectDesc, double projectTarget, String loggedInUserName);
}
