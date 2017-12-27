package models.daos;

import models.entities.Card;
import models.entities.User;
import models.services.SignupService;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.*;

public class SignupDao implements SignupService
{
    private boolean status;

    public static void main(String[] args)
    {
        SignupDao signupDao = new SignupDao();
        boolean result = signupDao.signup("admin", "admin", "admin@admin.com", "user4", "admin", "123459");
    }

    @Override
    public boolean signup(String fname, String lname, String email, String username, String password, String ccNum)
    {
        //create new user class
        User user = new User();
        user.setUserFname(fname);
        user.setUserLname(lname);
        user.setUserEmail(email);
        user.setUserUsername(username);
        user.setUserPassword(password);

        //heck i gotta find a way to reduce redundancy of this thing. An interfaces with an array return type
        //containing the two objects???
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();

        //get the corresponding card with the same credit card number
        try
        {
            assignCardSearchByCcNum(ccNum, user);
            status = true;
        }
        catch (NoResultException ex)
        {
            System.out.println("No such credit card number exists");
            ex.printStackTrace();
            status = false;
        }
        catch (ConstraintViolationException constViolationEx)
        {
            /*System.out.println("Users may have only one individual card - FK violation");*/
            constViolationEx.printStackTrace();
            status = false;
        }
        catch (HibernateException hibernateException)
        {
            System.out.println("Too many database connections");
            hibernateException.printStackTrace();
            status = false;
        }
        finally
        {
            System.out.println(status);
            return status;
        }
    }

    private void assignCardSearchByCcNum(String ccNum, User user) throws NoResultException, ConstraintViolationException
    {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Card> cardQuery = em.createQuery("select card from Card card where card.ccNum = :ccNumber", Card.class);
        cardQuery.setParameter("ccNumber", ccNum);

        Card card = cardQuery.getSingleResult();

        user.setCardId(card);

        em.persist(user);

        em.getTransaction().commit();
        entityManagerFactory.close();
    }
}
