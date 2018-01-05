package tk.govfundme.models;

import tk.govfundme.models.entities.Card;
import tk.govfundme.models.entities.CardType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DirectQueries
{
    public static void main(String[] args)
    {
        DirectQueries dq = new DirectQueries();
        List<Card> cards = dq.getCardFromTypes();

        for(Card card: cards)
        {
            System.out.println(card.getCardId() + " " + card.getCardBalance() + " " + card.getCcNum() + card.getTypeId().getTypeName());
        }

    }
    public List<Card> getCardFromTypes()
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        CardType cardType1 = em.find(CardType.class,1);

        List<Card> cards = cardType1.getCards();

        em.getTransaction().commit();
        entityManagerFactory.close();

        return cards;
    }
}
