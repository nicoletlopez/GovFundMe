package models.services;

import models.InsufficientFundsException;
import models.entities.Project;
import models.entities.User;

public interface DonationService
{
    void userDonatesToProject(User user, Project project);
    boolean donate(String userName, Project project, double donation);
    void deductFromUserCard(User user, double donation) throws InsufficientFundsException;
    void addToProjectBalance(Project project, double donation);
}
