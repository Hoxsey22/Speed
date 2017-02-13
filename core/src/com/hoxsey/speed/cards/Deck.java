package com.hoxsey.speed.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.awt.TextComponent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    public Deck()   {
        image = new Texture("back.png");
        deck = new Stack<Card>();
    }

    public Deck(ArrayList<Card> cardsContained, int id, int x, int y)   {
        this.id = id;
        image = new Texture("back.png");
        position = new Vector2(x,y);
        deck = new Stack<Card>();
    }

    public void shuffle()   {
        Collections.shuffle(deck);
    }

    public void changePosition(int x, int y)    {
        position.set(x,y);
    }

    public Vector2 getPostion() {
        return position;
    }

    public float getX()   {
        return position.x;
    }

    public float getY()   {
        return position.y;
    }

    public Texture getImage()   {
        return image;
    }

    public void changePosition(Vector2 position)    {
        this.position = position;
    }

    public int getCardCount()   {
        return deck.size();
    }

    public Card getTopCard()    {
        return deck.peek();
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

    public void flipTopCard()   {
        image = getTopCard().getImage();
    }

    public void push(Card card)  {
        deck.push(card);
    }

    public Card pop()   {
        return deck.pop();
    }

    public void set(Card card)  {
        deck.pop();
        deck.push(card);
    }

    public int size()   {
        return deck.size();
    }


    public String toString()   {
       return deck.toString();
    }

}
