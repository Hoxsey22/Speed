package com.hoxsey.speed.players;

import com.badlogic.gdx.utils.Timer;
import com.hoxsey.speed.cards.Card;
import com.hoxsey.speed.cards.Deck;
import com.hoxsey.speed.cards.Hand;

import java.awt.event.ActionListener;

/**
 * Created by Hoxsey on 2/19/2017.
 * testing branch change
 */
public class NPC {

    private Deck deck;
    private Hand hand;
    private int difficulty;
    private boolean flipFlag;
    private Card selectedCard;
    private Timer timer;

    public NPC()   {
        deck = new Deck();
        hand = new Hand(2);
        difficulty = 1;
        flipFlag = false;

    }

    public void setupHand() {
        hand.setDeck(deck);
        hand.init();
    }

    public void draw()  {
        hand.draw();
    }

    public void addToDeck(Card card)   {
        deck.push(card);
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

    public void nextCard()  {
        int position = hand.getHand().indexOf(selectedCard);

        if(deck.isEmpty() && hand.size() == 1) {
            return;
        }
        else if(hand.size() == 1) {
            hand.draw();
            selectedCard = hand.getCardAt(position+1);
        }
        else if(position == hand.size()-1)  {
            selectedCard = hand.getCardAt(0);
        }
        else {
            selectedCard = hand.getCardAt(position+1);
        }
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

    public Deck getDeck() {
        return deck;
    }

    public Hand getHand() {
        return hand;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public boolean isDone()    {
        if(deck.isEmpty() && hand.isEmpty())
            return true;
        return false;
    }


}
