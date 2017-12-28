package models.daos;

import models.entities.Category;
import models.services.CategoryService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements CategoryService
{
    @Override
    public List<String> getCategories()
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Category> getCategoriesQuery = em.createQuery("select category from Category category", Category.class);
        List<Category> categories = getCategoriesQuery.getResultList();

        List<String> categoryNameList = new ArrayList<>();

        for (Category category : categories)
        {
            categoryNameList.add(category.getCategoryName());
        }

        em.getTransaction().commit();
        entityManagerFactory.close();

        return categoryNameList;
    }
    class CategoryHelper
    {
        public Category CategoryNameToObject(String categoryNameString)
        {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
            EntityManager em = entityManagerFactory.createEntityManager();

            em.getTransaction().begin();

            TypedQuery<Category> categoryQuery = em.createQuery("select category from Category category where category.categoryName = :categoryNameParam", Category.class);
            categoryQuery.setParameter("categoryNameParam", categoryNameString);

            Category category = categoryQuery.getSingleResult();

            em.getTransaction().commit();
            entityManagerFactory.close();

            return category;
        }
    }

/*    @Override
    public Category CategoryNameToObject(String categoryNameString)
    {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
            EntityManager em = entityManagerFactory.createEntityManager();

            em.getTransaction().begin();

            TypedQuery<Category> categoryQuery = em.createQuery("select category from Category category where category.categoryName = :categoryNameParam", Category.class);
            categoryQuery.setParameter("categoryNameParam", categoryNameString);

            Category category = categoryQuery.getSingleResult();

            em.getTransaction().commit();
            entityManagerFactory.close();

            return category;
    }*/
}
