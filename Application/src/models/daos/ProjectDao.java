package models.daos;

import models.entities.Category;
import models.entities.Project;
import models.entities.User;
import models.exceptions.DuplicateProjectNameException;
import models.services.ProjectService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectDao extends DonationServiceLayer implements ProjectService
{
/*    public static void main(String[] args)
    {
        ProjectDao createProjectDao = new ProjectDao();
        createProjectDao.createProject("HERO","DANGER","NO IMAGE","A beautiful project",200.89,"user2");
    }*/
    @Override
    public boolean createProject(String projectName, String projectCategory, String projectImage, String projectDesc, double projectTarget, String loggedInUserName)
    {
        try
        {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
            EntityManager em = entityManagerFactory.createEntityManager();

            /*Convert category name to its corresponding category object*/

            //instance of inner class CategoryHelper
            CategoryDao.CategoryHelper categoryHelper = new CategoryDao().new CategoryHelper();

            Project project = new Project();
            project.setProjectName(projectName);
            project.setCategoryId(categoryHelper.CategoryNameToObject(projectCategory));
            project.setProjectImage(projectImage);
            project.setProjectDesc(projectDesc);
            project.setProjectTarget(projectTarget);
            project.setUserId(new UserHelper().getUserByUserName(loggedInUserName));
            project.setProjectStatus("Ongoing");

            /*Check if project name has already been taken,
             * this method throws a DuplicateProjectNameException */
            checkProjectNameDuplicity(projectName);


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date creationDate = sdf.parse(sdf.format(new Date()));
            project.setDate(creationDate);


            em.getTransaction().begin();

            em.persist(project);

            em.getTransaction().commit();
            entityManagerFactory.close();

            return true;
        }
        catch(DuplicateProjectNameException duplicateProjectNameEx)
        {
            duplicateProjectNameEx.printStackTrace();
            System.out.println("A project with a similar name has already been created");
            return false;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Project> getAllProjects()
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<Project> projectQuery = em.createQuery("select project from Project project order by date desc ",Project.class);
        List<Project> projects = projectQuery.getResultList();

        em.getTransaction().commit();
        entityManagerFactory.close();

        return projects;
    }
    @Override
    public Project getProjectByName(String projectName)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<Project> singleProjectQuery = em.createQuery("select project from Project project where project.projectName = :projectNameParam", Project.class);
        singleProjectQuery.setParameter("projectNameParam",projectName);

        Project project = singleProjectQuery.getSingleResult();

        em.getTransaction().commit();
        entityManagerFactory.close();

        return project;
    }
    @Override
    public List<Project> getProjectsByCategory(String categoryName)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        /*I hate making these workArounds ;_;*/
        /*CONFOUNDED WORKAROUND*/
        CategoryDao.CategoryHelper categoryHelper = new CategoryDao().new CategoryHelper();
        Category tempCategory = categoryHelper.CategoryNameToObject(categoryName);
        /*CONFOUNDED WORKAROUND*/

        TypedQuery<Project> projectByCategoryQuery = em.createQuery("select projectWithCat from Project projectWithCat where projectWithCat.categoryId = :projectCategoryParam", Project.class);
        projectByCategoryQuery.setParameter("projectCategoryParam",tempCategory);

        List<Project> projectsByCategory = projectByCategoryQuery.getResultList();

        em.getTransaction().commit();
        entityManagerFactory.close();
        return projectsByCategory;
    }
    /*Adds a user object in the PROJECT entity's List<Project> a donor*/
    @Override
    public void userDonatesToProject(User user, Project project)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        List<User> userDonors = new ArrayList<>();

        userDonors = project.getUsersDonated();
        userDonors.add(user);

        em.getTransaction().commit();
        entityManagerFactory.close();
    }
    @Override
    public void addToProjectBalance(Project project, double donation)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<Project> projectQuery = em.createQuery("select project from Project project where project = :projectObjectParam", Project.class);
        projectQuery.setParameter("projectObjectParam", project);

        project = projectQuery.getSingleResult();

        double newProjectBalance = (project.getProjectBalance() + donation);
        project.setProjectBalance(newProjectBalance);

        em.getTransaction().commit();
        entityManagerFactory.close();

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
    }
    private void checkProjectNameDuplicity(String projectName) throws DuplicateProjectNameException
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<Project> singleProjectQuery = em.createQuery("select project from Project project where project.projectName = :projectNameParam", Project.class);
        singleProjectQuery.setParameter("projectNameParam",projectName);

        List<Project> projectList = singleProjectQuery.getResultList();

        em.getTransaction().commit();
        entityManagerFactory.close();

        if(projectList.size() > 0)
        {
            System.out.println("A project with a similar name already exists");
            throw new DuplicateProjectNameException();
        }
    }
/*    @Override
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
    }*/
}
