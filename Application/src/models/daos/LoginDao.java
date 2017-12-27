package models.daos;

import javassist.bytecode.stackmap.BasicBlock;
import models.entities.User;
import models.services.LoginService;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.*;
import java.util.List;

public class LoginDao implements LoginService
{
    private boolean result;

    public static void main(String[] args) throws NoResultException
    {
        LoginDao loginDao = new LoginDao();
        boolean result = loginDao.login("user1", "user1");
        System.out.println("Result at main: " + result);
    }

    @Override
    public boolean login(String username, String password)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();
        try
        {

            em.getTransaction().begin();
            TypedQuery<User> usersofUserAndPass = em.createQuery("select user from User user where user.userUsername = :usernameParam and user.userPassword = :passwordParam", User.class);

            usersofUserAndPass.setParameter("usernameParam", username);
            usersofUserAndPass.setParameter("passwordParam", password);

            User userLogin = usersofUserAndPass.getSingleResult();


            result = true;
        } catch (NoResultException ex)
        {
            ex.printStackTrace();
            result = false;
        } finally
        {
            em.getTransaction().commit();
            entityManagerFactory.close();
            System.out.println(result);
            return result;
        }
    }

}
