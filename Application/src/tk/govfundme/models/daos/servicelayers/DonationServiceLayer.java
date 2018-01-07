package tk.govfundme.models.daos.servicelayers;

import tk.govfundme.models.exceptions.InsufficientFundsException;
import tk.govfundme.models.entities.Project;
import tk.govfundme.models.entities.User;
import tk.govfundme.models.services.DonationService;

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
