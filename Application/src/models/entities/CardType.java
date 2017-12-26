package models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class CardType implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int typeId;

    public int getTypeId()
    {
        return typeId;
    }

    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
    }

    @Basic
    private String typeName;

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typeId", cascade = CascadeType.ALL)
    private List<Card> cards;

    public List<Card> getCards()
    {
        return cards;
    }

    public void setCards(List<Card> cards)
    {
        this.cards = cards;
    }

    public CardType(){}

    public CardType(String typeName)
    {
        this.typeName = typeName;
    }
}
