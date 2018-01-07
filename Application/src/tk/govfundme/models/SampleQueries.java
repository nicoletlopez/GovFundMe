package tk.govfundme.models;

import tk.govfundme.models.entities.CardType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class SampleQueries
{
    public static void main(String[] args)
    {
/*        SampleInserts sampleInserts = new SampleInserts();
        sampleInserts.setSampleData();*/
        SampleQueries sampleQueries = new SampleQueries();
        sampleQueries.query();
    }
    private void query()
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tk.govfundme.jpa");

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<CardType> query1 = em.createQuery("select c from CardType c where c.typeId = 200", CardType.class);
        CardType cardType = query1.getSingleResult();

        System.out.println(cardType.getTypeName() + " " + cardType.getTypeId());

        em.getTransaction().commit();
        entityManagerFactory.close();
    }
}
