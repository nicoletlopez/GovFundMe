package models.daos;

import models.entities.Project;
import models.entities.User;
import models.services.DonationService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends DonationServiceLayer
{
    /*Adds a project to the USER entity's list of projects which it has donated to */
    @Override
    public void userDonatesToProject(User user, Project project)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        List<Project> projectsUserDonatedTo;

        projectsUserDonatedTo = user.getProjectsDonatedTo();
        projectsUserDonatedTo.add(project);

        em.getTransaction().commit();
        entityManagerFactory.close();
    }
}
