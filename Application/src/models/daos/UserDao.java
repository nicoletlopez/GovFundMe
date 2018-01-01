package models.daos;

import models.entities.Card;
import models.entities.Project;
import models.entities.User;
import models.services.DonationService;
import models.services.ProfileService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends DonationServiceLayer implements ProfileService
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

    @Override
    public User viewProfile(String username)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        //get the user object by usename
        TypedQuery<User> userQuery = em.createQuery("select user from User user where user.userUsername = :usernameParam", User.class);
        userQuery.setParameter("usernameParam", username);

        //extract user object from query
        User userProfile = userQuery.getSingleResult();


        em.getTransaction().commit();
        entityManagerFactory.close();

        return userProfile;
    }


}
