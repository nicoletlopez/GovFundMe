package models;

import models.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Test
{
    public static void main(String[] args)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<User> userTypedQuery = em.createQuery("select user from User user", User.class);

        System.out.println(userTypedQuery.getResultList().size());
//        try
//        {
//            userTypedQuery.getSingleResult();
//        }
//        catch(Exception ex)
//        {
//            System.out.println("SAME PROJECT NAME");
//        }

        em.getTransaction().commit();
        entityManagerFactory.close();
    }
}
