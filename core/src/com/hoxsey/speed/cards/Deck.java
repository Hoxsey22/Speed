package com.hoxsey.speed.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

/**
 * Created by Hoxsey on 2/9/2017.
 */
public class Deck {
    public final static int PLAYER1_DECK = 0;
    public final static int PLAYER2_DECK = 1;
    public final static int FLIP1_DECK = 2;
    public final static int FLIP2_DECK = 3;
    public final static int PLAYABLE1_DECK = 4;
    public final static int PLAYABLE2_DECK = 5;

    private Vector2 position;
    private Texture image;
    private Stack<Card> deck = new Stack<Card>();
    private int id;

    public Deck(ArrayList<Card> cardsContained, int id, int x, int y)   {
        this.id = id;
        image = new Texture("back.png");
        position = new Vector2(x,y);
    }

    public void shuffle(ArrayList<Card> cards)   {
        int random;
        for(int i = 0; i < cards.size(); i++)   {
            random = (int)(Math.random()*cards.size())+0;
            deck.push(cards.get(random));
            cards.remove(random);
        }

    }

    public int getCardCount()   {
        return deck.size();
    }

    public Card draw()  {
        if(deck.size() > 1)
            return deck.pop();
        else
            return null;
    }

    public void addToDeck(Card card) {
        deck.push(card);
        image = card.getImage();
    }

    public void flipDeck()  {
        Stack<Card> temp = new Stack<Card>();
        while(!deck.isEmpty()) {
            temp.push(deck.pop());
        }
        deck = temp;
    }

    public String toString()   {
       return deck.toString();
    }

}
