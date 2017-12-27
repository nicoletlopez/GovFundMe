package models;

import models.entities.Card;
import models.entities.CardType;
import models.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SampleInserts
{
    public static void main(String[] args)
    {
        SampleInserts sampleInserts = new SampleInserts();
        sampleInserts.setSampleData();
    }

    public void setSampleData()
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();

        try
        {
            CardType platinum = new CardType("Platinum");
            CardType silver = new CardType("Silver");
            CardType gold = new CardType("gold");

            Card platinumCard1 = new Card();
            platinumCard1.setCardBalance(5000);
            platinumCard1.setCcNum("123456");
            platinumCard1.setTypeId(platinum);

            Card platinumCard2 = new Card();
            platinumCard2.setCardBalance(4000);
            platinumCard2.setCcNum("123457");
            platinumCard2.setTypeId(platinum);

            Card goldCard1 = new Card();
            goldCard1.setCardBalance(2500);
            goldCard1.setCcNum("123458");
            goldCard1.setTypeId(gold);

            Card goldCard2 = new Card();
            goldCard2.setCardBalance(250);
            goldCard2.setCcNum("111111");
            goldCard2.setTypeId(gold);

            Card goldCard3 = new Card();
            goldCard3.setCardBalance(400);
            goldCard2.setCcNum("222222");
            goldCard2.setTypeId(gold);

            Card silverCard1 = new Card();
            silverCard1.setCardBalance(2000);
            silverCard1.setCcNum("123459");
            silverCard1.setTypeId(silver);


            User user1 = new User();
            user1.setUserEmail("user1@email.com");
            user1.setUserFname("Johnny");
            user1.setUserLname("Fins");
            user1.setUserPassword("user1");
            user1.setUserUsername("user1");
            user1.setCardId(platinumCard1);

            User user2 = new User();
            user2.setUserEmail("user2@email.com");
            user2.setUserFname("Fred");
            user2.setUserLname("Andrews");
            user2.setUserPassword("user2");
            user2.setUserUsername("user2");
            user2.setCardId(platinumCard2);

            User user3 = new User();
            user3.setUserEmail("user3@email.com");
            user3.setUserFname("Natasha");
            user3.setUserLname("Gray");
            user3.setUserPassword("user3");
            user3.setUserUsername("user3");
            user3.setCardId(goldCard1);

            User user4 = new User();
            user4.setUserEmail("user4@email.com");
            user4.setUserFname("Shania");
            user4.setUserLname("Khalifa");
            user4.setUserPassword("user4");
            user4.setUserUsername("user4");
            user4.setCardId(silverCard1);


            em.getTransaction().begin();

            em.persist(platinum);
            em.persist(gold);
            em.persist(silver);

            em.persist(platinumCard1);
            em.persist(platinumCard2);
            em.persist(goldCard1);
            em.persist(goldCard2);
            em.persist(goldCard3);
            em.persist(silverCard1);

            em.persist(user1);
            em.persist(user2);
            em.persist(user3);
            em.persist(user4);
        } catch (Exception ex)
        {
            em.getTransaction().rollback();
            ex.printStackTrace();
        }
        finally
        {
            em.getTransaction().commit();
            entityManagerFactory.close();
            System.out.println("connection closed");
        }
    }
}
