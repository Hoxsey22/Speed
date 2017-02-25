package com.hoxsey.speed.players;

import com.badlogic.gdx.graphics.Cursor;
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
    private float timer;
    private Deck face1;
    private Deck face2;
    private int marked;

    public NPC(int difficulty)   {
        deck = new Deck();
        hand = new Hand(2);
        this.difficulty = difficulty;
        flipFlag = false;
        System.out.println(difficulty);

    }

    public void loadFaceDecks(Deck d1, Deck d2) {
        face1 = d1;
        face2 = d2;
    }

    public void setupHand() {
        hand.setDeck(deck);
        hand.init();
        selectedCard = hand.getCardAt(0);
        marked = 0;
    }

    public void update(float dt)    {
        timer += dt;

        switch (difficulty){
            case 1:
                if(timer > 2.5)
                    play();
                break;
            case 2:
                if(timer > 1.5)
                    play();
                break;
            case 3:
                if(timer > 0.5)
                    play();
                break;
        }




    }

    public void play()  {
        if(!playCard())
            nextCard();

        if(marked > hand.size())
            flagFlip();
        timer = 0;
    }


    public void draw()  {
        System.out.println(hand.size());
        if(hand.size() == 5)
            return;
        for(int i = hand.size(); i < 5; i++)
            hand.draw();
        marked = 0;
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

    public void nextCard()  {
        int position = hand.getHand().indexOf(selectedCard);

        if(deck.isEmpty() && hand.size() == 1) {
            marked++;
            return;
        }
        else if(hand.size() == 1) {
            draw();
            selectedCard = hand.getCardAt(position+1);
        }
        else if(position == hand.size()-1)  {
            selectedCard = hand.getCardAt(0);
            marked++;
        }
        else {
            selectedCard = hand.getCardAt(position+1);
            marked++;
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
            draw();
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

    public boolean playCard()  {

        if(selectedCard.isNeighbors(face1.getTopCard().getValue())) {
           face1.push(selectedCard);
            playCardHelper();
            draw();
            return true;
        }
        if(selectedCard.isNeighbors(face2.getTopCard().getValue())) {
            face2.push(selectedCard);
            playCardHelper();
            draw();
            return true;
        }
        return false;


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

    public Card getSelectedCard() {
        return selectedCard;
    }

    public boolean isDone()    {
        if(deck.isEmpty() && hand.isEmpty())
            return true;
        return false;
    }

    public boolean isFlipFlag() {
        return flipFlag;
    }


}
