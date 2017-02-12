package com.hoxsey.speed.cards;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 2/12/2017.
 */
public class Hand {

    private ArrayList<Card> hand;
    private Deck deck;
    private ArrayList<Vector2> positions;

    public Hand(Deck deck)   {
        this.deck = deck;
        hand = new ArrayList<Card>(5);
        positions = new ArrayList<Vector2>();
        loadPositions();
        loadHand();
    }

    public void loadPositions() {
        positions.add(new Vector2(207,25));
        positions.add(new Vector2(389,25));
        positions.add(new Vector2(571,25));
        positions.add(new Vector2(753,25));
        positions.add(new Vector2(935,25));
    }


    public void loadHand()  {
        for(int i = 0; i < 5; i++)   {
            hand.add(deck.pop());
            hand.get(i).changePosition(positions.get(i));
        }
    }

    public void draw()  {
        if(hand.size() < 5 && deck.size() > 0)
            hand.add(deck.draw());
    }

    public Card getCardAt(int pos)   {
        return hand.get(pos);
    }



}
