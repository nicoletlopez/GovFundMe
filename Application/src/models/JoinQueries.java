package models;

import models.entities.Card;
import models.entities.CardType;

import javax.persistence.*;
import java.util.List;

public class JoinQueries
{
    public static void main(String[] args)
    {
        JoinQueries jq = new JoinQueries();
        jq.getCardsByType();
    }
    public void getCardsByType()
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        CardType platinumCard = em.find(CardType.class,1);

        TypedQuery<Card> cards = em.createQuery("select card from Card card where card.typeId = :typeIdParam", Card.class);
        cards.setParameter("typeIdParam",platinumCard);

        List<Card> cardResults = cards.getResultList();

        for(Card card: cardResults)
        {
            System.out.println(card.getCardId() + " " + card.getCardBalance() + " " + card.getCcNum() + " " + card.getTypeId().getTypeName());
        }

        em.getTransaction().commit();
        entityManagerFactory.close();
    }
}
