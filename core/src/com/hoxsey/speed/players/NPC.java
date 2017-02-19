package com.hoxsey.speed.players;

import com.hoxsey.speed.cards.Card;
import com.hoxsey.speed.cards.Deck;
import com.hoxsey.speed.cards.Hand;

/**
 * Created by Hoxsey on 2/19/2017.
 */
public class NPC {

    private Deck deck;
    private Hand hand;
    private int difficulty;
    private boolean flipFlag;
    private Card selectedCard;

    public NPC()    {
        deck = new Deck();
        hand = new Hand(2);
        difficulty = 1;
        flipFlag = false;
    }

    public void setupHand() {
        hand.init();
    }

    public void draw()  {
        hand.draw();
    }

    public void addToDeck(Card card)   {
        hand.addToDeck(card);
    }

    public void flagFlip()  {
        flipFlag = true;
    }

    public void unflagFlip()  {
        flipFlag = false;
    }

    public boolean isFlipFlagged()  {
        return flipFlag;
    }

    public void playCardHelper()  {
        int position = hand.getHand().indexOf(selectedCard);

        if(deck.isEmpty() && hand.size() == 1) {
            hand.remove(selectedCard);
            selectedCard = null;
        }
        else if(hand.size() == 1) {
            hand.remove(selectedCard);
            hand.draw();
            selectedCard = hand.getCardAt(0);
        }
        else if(position == hand.size()-1)  {
            hand.remove(selectedCard);
            selectedCard = hand.getCardAt(0);
        }
        else {
            hand.remove(selectedCard);
            selectedCard = hand.getCardAt(position);
        }

    }

    public void playCard(Deck playCard)  {
        if(selectedCard.isNeighbors(playCard.getTopCard().getValue())) {
            playCard.push(selectedCard);
            playCardHelper();
        }

    }

    public boolean isDone()    {
        if(deck.isEmpty() && hand.isEmpty())
            return true;
        return false;
    }


}
