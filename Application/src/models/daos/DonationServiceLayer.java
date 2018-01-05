package models.daos;

import models.exceptions.InsufficientFundsException;
import models.entities.Project;
import models.entities.User;
import models.services.DonationService;

public abstract class DonationServiceLayer implements DonationService
{
    @Override
    public void userDonatesToProject(User user, Project project)
    {

    }
    @Override
    public boolean donate(String userName, Project project, double donation)
    {
        return false;
    }
    @Override
    public void deductFromUserCard(User user, double donation) throws InsufficientFundsException
    {

    }
    @Override
    public void addToProjectBalance(Project project, double donation)
    {

    }
}
