package models;

import models.entities.Card;
import models.entities.CardType;

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

        Card silverCard1 = new Card();
        silverCard1.setCardBalance(2000);
        silverCard1.setCcNum("123459");
        silverCard1.setTypeId(silver);

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.persist(platinum);
        em.persist(gold);
        em.persist(silver);

        em.persist(platinumCard1);
        em.persist(platinumCard2);
        em.persist(goldCard1);
        em.persist(silverCard1);

        em.getTransaction().commit();
        entityManagerFactory.close();
    }
}
