package models.daos;

import models.entities.Category;
import models.entities.Project;
import models.entities.User;
import models.services.CreateProjectService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            project.setCategoryId(new UserHelper().getCategoryByCategoryName(projectCategory));
            project.setProjectImage(projectImage);
            project.setProjectDesc(projectDesc);
            project.setProjectTarget(projectTarget);
            project.setUserId(new UserHelper().getUserByUserName(loggedInUserName));
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
    @Override
    public List<String> getAllCategories()
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Category> getCategoriesQuery = em.createQuery("select category from Category category", Category.class);
        List<Category> categories = getCategoriesQuery.getResultList();

        List<String> categoryNameList = new ArrayList<>();

        for(Category category:categories)
        {
            categoryNameList.add(category.getCategoryName());
        }

        em.getTransaction().commit();
        entityManagerFactory.close();

        return categoryNameList;
    }
    class UserHelper
    {
        public User getUserByUserName(String userName)
        {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
            EntityManager em = entityManagerFactory.createEntityManager();

            em.getTransaction().begin();


            TypedQuery<User> userLoggedInQuery = em.createNamedQuery("User.findUserByUsername", User.class);
            userLoggedInQuery.setParameter("userNameParam", userName);

            User userLoggedIn = userLoggedInQuery.getSingleResult();

            em.getTransaction().rollback();
            entityManagerFactory.close();

            return userLoggedIn;
        }

        public Category getCategoryByCategoryName(String categoryName)
        {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
            EntityManager em = entityManagerFactory.createEntityManager();

            em.getTransaction().begin();

            TypedQuery<Category> categoryQuery = em.createQuery("select category from Category category where category.categoryName = :categoryNameParam", Category.class);
            categoryQuery.setParameter("categoryNameParam", categoryName);

            Category category = categoryQuery.getSingleResult();

            em.getTransaction().commit();
            entityManagerFactory.close();

            return category;
        }
    }


}
