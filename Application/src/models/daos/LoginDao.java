package models.daos;

import javassist.bytecode.stackmap.BasicBlock;
import models.entities.User;
import models.services.LoginService;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.*;
import java.util.List;

public class LoginDao implements LoginService
{
/*    private EntityManagerFactory entityManagerFactory;
    private EntityManager em;*/

    public static void main(String[] args)
    {
        LoginDao loginDao = new LoginDao();
        boolean result = loginDao.login("admin", "admindaw");
    }

    @Override
    public boolean login(String username, String password)
    {
        boolean status = false;

/*        try
        {*/
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<User> userLoginQuery = em.createQuery("select userLogin from User userLogin where userLogin.userUsername = :usernameParam and userLogin.userPassword = :passwordParam", User.class);
        userLoginQuery.setParameter("usernameParam", username);
        userLoginQuery.setParameter("passwordParam", password);

        List<User> userLogin = userLoginQuery.getResultList();

        if(userLogin == null)
        {
            System.out.println(false);
        }
        else
        {
            System.out.println(true);
        }

        em.getTransaction().commit();
        entityManagerFactory.close();


/*        }
        catch (NoResultException ex)
        {
            ex.printStackTrace();
            System.out.println("No such user");
            status = false;
        }
        finally
        {
            System.out.println(status);
            return status;
        }*/
return true;
    }
}
