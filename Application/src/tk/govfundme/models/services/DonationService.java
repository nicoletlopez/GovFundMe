package tk.govfundme.models.services;

import tk.govfundme.models.exceptions.InsufficientFundsException;
import tk.govfundme.models.entities.Project;
import tk.govfundme.models.entities.User;

public interface DonationService
{
    void userDonatesToProject(User user, Project project);
    boolean donate(String userName, Project project, double donation);
    void deductFromUserCard(User user, double donation) throws InsufficientFundsException;
    void addToProjectBalance(Project project, double donation);
}
