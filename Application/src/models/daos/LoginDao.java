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

/*    public static void main(String[] args) throws NoResultException
    {
        LoginDao loginDao = new LoginDao();
        boolean result = loginDao.login("user1", "user1");
    }*/

    @Override
    public boolean login(String username, String password)
    {
        try
        {
            validate(username, password);
        }
        catch (NoResultException ex)
        {
            System.out.println("error");
        }
        finally
        {
            System.out.println(result);
            return result;
        }
    }

    public void validate(String username, String password) throws NoResultException
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        TypedQuery<User> usersofUserAndPass = em.createQuery("select user from User user where user.userUsername = :usernameParam and user.userPassword = :passwordParam", User.class);

        usersofUserAndPass.setParameter("usernameParam", username);
        usersofUserAndPass.setParameter("passwordParam", password);

        List<User> userLogin = usersofUserAndPass.getResultList();

        em.getTransaction().commit();
        entityManagerFactory.close();

        if (userLogin.size() == 0)
        {
            result = false;
            throw new NoResultException("User does not exist");
        } else
        {
            result = true;
        }
    }
}
