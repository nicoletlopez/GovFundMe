package models.daos;

import models.entities.Project;
import models.entities.User;
import models.services.CreateProjectService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateProjectDao implements CreateProjectService
{
/*    public static void main(String[] args)
    {
        CreateProjectDao createProjectDao = new CreateProjectDao();
        createProjectDao.createProject("HERO","DANGER","NO IMAGE","A beautiful project",200.89,"user2");
    }*/
    @Override
    public boolean createProject(String projectName, String projectCategory, String projectImage, String projectDesc, double projectTarget, String loggedInUserName)
    {
        try
        {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
            EntityManager em = entityManagerFactory.createEntityManager();

            Project project = new Project();
            project.setProjectName(projectName);
            project.setProjectCategory(projectCategory);
            project.setProjectImage(projectImage);
            project.setProjectDesc(projectDesc);
            project.setProjectTarget(projectTarget);
            project.setUserId(getUserByUserName(loggedInUserName));
            project.setProjectStatus("Ongoing");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date creationDate = sdf.parse(sdf.format(new Date()));
            project.setDate(creationDate);


            em.getTransaction().begin();

            em.persist(project);

            em.getTransaction().commit();
            entityManagerFactory.close();

            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }

    }
    public User getUserByUserName(String userName)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();


        TypedQuery<User> userLoggedInQuery = em.createNamedQuery("User.findUserByUsername", User.class);
        userLoggedInQuery.setParameter("userNameParam", userName);

        User userLoggedIn = userLoggedInQuery.getSingleResult();

        em.getTransaction().commit();
        entityManagerFactory.close();

        return userLoggedIn;
    }

}
