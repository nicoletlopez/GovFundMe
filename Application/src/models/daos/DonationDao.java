package models.daos;

import models.exceptions.InsufficientFundsException;
import models.entities.Project;
import models.entities.User;
import models.services.DonationService;

public class DonationDao extends DonationServiceLayer
{
    @Override
    public boolean donate(String userName, Project project, double donation)
    {
        //get a User object derived from the User's username
        User user = new ProjectDao().new UserHelper().getUserByUserName(userName);
        try
        {
            DonationService deductFromCardObject = new CardDao();
            deductFromCardObject.deductFromUserCard(user, donation);

/*            DonationService addUserToProjectsDonorListObject = new ProjectDao();
            addUserToProjectsDonorListObject.userDonatesToProject(user, project);

            DonationService addProjectToUserDonatedToList = new UserDao();
            addProjectToUserDonatedToList.userDonatesToProject(user,project);*/

            return true;
        }
        catch (InsufficientFundsException ex)
        {
            System.out.println("Insufficient Funds");
            ex.printStackTrace();
            return false;
        }
    }
}
