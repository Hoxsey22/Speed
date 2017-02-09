package com.hoxsey.speed.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Hoxsey on 2/9/2017.
 */
public class Card {

    public final static int SPADES = 0;
    public final static int HEARTS = 1;
    public final static int DIAMONDS = 2;
    public final static int CLUBS = 3;

    public final static int ACE = 1;
    public final static int JACK = 11;
    public final static int QUEEN = 12;
    public final static int KING = 13;

    private Vector2 position;
    private Texture image;
    private int suit;
    private int value;

    public Card(int suit, int value, int x, int y)   {
        position = new Vector2(x,y);
        this.suit = suit;
        this.value = value;
    }

    public int getSuit()    {
        return suit;
    }

    public int getValue()    {
        return value;
    }

    public String getSuitString()  {
        switch(suit) {
            case SPADES:
                return "Spades";
            case HEARTS:
                return "Hearts";
            case DIAMONDS:
                return "Diamonds";
            case CLUBS:
                return "Clubs";
        }
        return "null";
    }

    public String getValueString()  {
        switch(suit) {
            case ACE:   return "Ace";
            case 2:   return "2";
            case 3:   return "3";
            case 4:   return "4";
            case 5:   return "5";
            case 6:   return "6";
            case 7:   return "7";
            case 8:   return "8";
            case 9:   return "9";
            case 10:  return "10";
            case JACK:  return "Jack";
            case QUEEN:  return "Queen";
            case KING:  return "King";
        }
        return "null";
    }

    public Texture getImage()   {
        return image;
    }

    public String toString()   {
        return getValueString()+" of "+getSuitString();
    }
}
