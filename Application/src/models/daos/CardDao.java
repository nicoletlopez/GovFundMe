package models.daos;

import models.exceptions.InsufficientFundsException;
import models.entities.Card;
import models.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class CardDao extends DonationServiceLayer
{
/*
    public static void main(String[] args)
    {

        CardDao cardDao = new CardDao();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        User user = em.find(User.class,10);

        try
        {
            cardDao.deductFromUserCard(user, 200);
            em.getTransaction().commit();
        }
        catch (InsufficientFundsException ex)
        {
            System.out.println("Insufficient funds");
            em.getTransaction().rollback();
        }
        finally
        {
            entityManagerFactory.close();
        }


    }
    */
    @Override
    public void deductFromUserCard(User user, double donation) throws InsufficientFundsException
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Card card = user.getCardId();

        TypedQuery<Card> cardQuery = em.createQuery("select card from Card card where card = :cardObjectParam", Card.class);
        cardQuery.setParameter("cardObjectParam", card);

        card = cardQuery.getSingleResult();


        System.out.println(card.getCardId());

        if(card.getCardBalance() < donation)
        {

            System.out.println("Card has insufficient funds");

            em.getTransaction().rollback();
            entityManagerFactory.close();

            throw new InsufficientFundsException();
        }
        else
        {
            System.out.println("Old balance" + card.getCardBalance());
            System.out.println("success");
            double currentBalance = card.getCardBalance();
            double newBalance = currentBalance - donation;
            card.setCardBalance(newBalance);

            System.out.println("New Balance " + card.getCardBalance());

/*            DonationService donationServiceToProject = new UserDao();
            donationServiceToProject.userDonatesToProject(user,project);

            DonationService donationServiceByUser = new ProjectDao();
            donationServiceByUser.userDonatesToProject(user,project);*/


            em.getTransaction().commit();
            entityManagerFactory.close();
        }
    }
}
