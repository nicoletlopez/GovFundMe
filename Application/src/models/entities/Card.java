package models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Card implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cardId;

    public int getCardId()
    {
        return cardId;
    }

    public void setCardId(int cardId)
    {
        this.cardId = cardId;
    }

    @Basic
    private Double cardBalance;

    public Double getCardBalance()
    {
        return cardBalance;
    }

    public void setCardBalance(Double cardBalance)
    {
        this.cardBalance = cardBalance;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="typeId")
    private CardType typeId;

    public CardType getTypeId()

    {
        return typeId;
    }

    public void setTypeId(CardType cardType)

    {
        this.typeId = cardType;
    }

}
