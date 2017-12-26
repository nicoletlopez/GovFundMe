package models.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {

        try
        {
            EntityManagerFactory entityManagerFactory =
                    Persistence.createEntityManagerFactory("tk.govfundme.jpa");

            Main main = new Main();
            main.setSampleData();


            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();


            TypedQuery<CardType> q1 = entityManager.createQuery("select p from CardType p", CardType.class);
            List<CardType> cardTypes = q1.getResultList();

            for (CardType ctype : cardTypes)
            {
                System.out.println(ctype.getTypeName() + " " + ctype.getTypeId());
            }

            CardType cardType = entityManager.find(CardType.class, 1);
            System.out.println(cardType.getTypeId() + "  " + cardType.getTypeName());

            entityManager.getTransaction().commit();
            entityManagerFactory.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }

    public void setSampleData()
    {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("tk.govfundme.jpa");


        CardType platinum = new CardType();
        platinum.setTypeName("Platinum");
        CardType gold = new CardType();
        gold.setTypeName("Gold");
        CardType silver = new CardType();
        silver.setTypeName("Silver");

        Card card1 = new Card();
        card1.setCardBalance(2000d);
        card1.setTypeId(platinum);

        Card card2 = new Card();
        card2.setCardBalance(1000d);
        card2.setTypeId(platinum);

        Card card3 = new Card();
        card3.setCardBalance(500d);
        card3.setTypeId(silver);


        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        /*PERSIST*/
        entityManager.persist(card1);
        entityManager.persist(card2);
        entityManager.persist(card3);
        entityManager.persist(platinum);
        entityManager.persist(gold);
        entityManager.persist(silver);

        /*PERSIST*/

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }


}
