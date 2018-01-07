package tk.govfundme.models.daos;

import tk.govfundme.models.daos.servicelayers.DonationServiceLayer;
import tk.govfundme.models.exceptions.InsufficientFundsException;
import tk.govfundme.models.entities.Project;
import tk.govfundme.models.entities.User;
import tk.govfundme.models.services.DonationService;

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
