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
    private double cardBalance;

    public double getCardBalance()
    {
        return cardBalance;
    }

    public void setCardBalance(double cardBalance)
    {
        this.cardBalance = cardBalance;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeName", referencedColumnName = "typeName")
    private CardType typeId;

    public CardType getTypeId()

    {
        return typeId;
    }

    public void setTypeId(CardType cardType)

    {
        this.typeId = cardType;
    }

    @Basic
    @Column(unique = true)
    private String ccNum;

    public String getCcNum()
    {
        return ccNum;
    }

    public void setCcNum(String ccNum)
    {
        this.ccNum = ccNum;
    }

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "cardId", cascade = CascadeType.ALL)
/*    @JoinColumn(name="userId")*/
    private User userId;

    public User getUserId()
    {
        return userId;
    }

    public void setUserId(User userId)
    {
        this.userId = userId;
    }



    public Card()
    {}

    public Card(double cardBalance, CardType typeId)
    {
        this.cardBalance = cardBalance;
        this.typeId = typeId;
    }


}
