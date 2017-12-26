package models.daos;

import javassist.bytecode.stackmap.BasicBlock;
import models.entities.User;
import models.services.LoginService;

import javax.persistence.*;

public class LoginDao implements LoginService
{
    public static void main(String[] args)
    {
        LoginDao loginDao = new LoginDao();
        boolean result = loginDao.login("admin","admin");

        System.out.println(result);
    }
    @Override
    public boolean login(String username, String password)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        try
        {
            TypedQuery<User> userLoginQuery = em.createQuery("select userLogin from User userLogin where userLogin.userUsername = :usernameParam and userLogin.userPassword = :passwordParam", User.class);
            userLoginQuery.setParameter("usernameParam", username);
            userLoginQuery.setParameter("passwordParam", password);

            User loginUser = userLoginQuery.getSingleResult();
            System.out.println("Logged in");
            return true;
        }
        catch(NoResultException ex)
        {
            ex.printStackTrace();
            System.out.println("No such user");
            return false;
        }
        finally
        {
            em.getTransaction().commit();
            entityManagerFactory.close();
        }
    }
}
