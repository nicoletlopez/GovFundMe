package models.daos;

import models.entities.Project;
import models.entities.User;
import models.services.CreateProjectService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateProjectDao implements CreateProjectService
{
    @Override
    public boolean createProject(String projectName, String projectCategory, String projectImage, String projectDesc, String projectTarget, String loggedInUserName)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();

        Project project;

        em.getTransaction().begin();



        em.getTransaction().commit();
        entityManagerFactory.close();

        return false;
    }

}
