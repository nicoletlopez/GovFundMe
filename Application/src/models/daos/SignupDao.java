package models.daos;

import models.entities.Card;
import models.entities.User;
import models.services.SignupService;

import javax.persistence.*;

public class SignupDao implements SignupService
{
/*    public static void main(String[] args)
    {
        SignupDao signupDao = new SignupDao();
        boolean result = signupDao.signup("admin","admin","admin@admin.com","admin","admin","1234523");
    }*/
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
        EntityManagerFactory  entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();

        //get the corresponding card with the same credit card number
        try
        {
            em.getTransaction().begin();

            Card userCard = getCardByCcNum(ccNum);
            user.setCardId(userCard);
            em.persist(user);
            return true;

        }
        catch (NoResultException ex)
        {
            System.out.println("No such credit card number exists");
            ex.printStackTrace();
            return false;
        }
        finally
        {
            em.getTransaction().commit();
            entityManagerFactory.close();
        }
    }

    private Card getCardByCcNum(String ccNum) throws NoResultException
    {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Card> cardQuery = em.createQuery("select card from Card card where card.ccNum = :ccNumber", Card.class);
        cardQuery.setParameter("ccNumber", ccNum);

        Card card = cardQuery.getSingleResult();
        System.out.println(card.getClass());
        System.out.println(card.getCcNum());

        em.getTransaction().commit();
        entityManagerFactory.close();

        return card;
    }
}
