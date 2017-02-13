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

    public Hand(Deck deck, int player)   {
        this.deck = deck;
        hand = new ArrayList<Card>(5);
        positions = new ArrayList<Vector2>();
        loadHand();
        loadPositions(player);

    }

    public void loadPositions(int player) {

        if(player == 1) {
            for (int i = 0; i < hand.size(); i++) {
                if (i == 0)
                    hand.get(i).changePosition(new Vector2(25, 25));
                else
                    hand.get(i).changePosition(new Vector2(hand.get(i - 1).getX() + deck.getTopCard().getImage().getWidth() + 10, 25));
            }
            deck.changePosition(new Vector2(hand.get(hand.size() - 1).getX() + deck.getTopCard().getImage().getWidth() + 10, 25));
        }
        else    {
            deck.changePosition(new Vector2(25, 2*(deck.getTopCard().getImage().getHeight() + 65)));
            for (int i = 0; i < hand.size(); i++) {
                if (i == 0)
                    hand.get(i).changePosition(new Vector2(deck.getX() + deck.getTopCard().getImage().getWidth() + 10, deck.getY()));
                else
                    hand.get(i).changePosition(new Vector2(hand.get(i - 1).getX() + deck.getTopCard().getImage().getWidth() + 10,
                            hand.get(i - 1).getY()));
            }
            //deck.changePosition(new Vector2(hand.get(hand.size() - 1).getX() + deck.getTopCard().getImage().getWidth() + 10, 10+hand.get(hand.size()-1).getY()));

        }


        /*
        positions.add(new Vector2(25,25));
        positions.add(new Vector2(50+deck.getTopCard().getImage().getWidth(),25));
        positions.add(new Vector2(25*2+deck.getTopCard().getImage().getWidth()*2,25));
        positions.add(new Vector2(25*3+deck.getTopCard().getImage().getWidth()*3,25));
        positions.add(new Vector2(25*4+deck.getTopCard().getImage().getWidth()*4,25));
        */
       // positions.add(new Vector2(935,25));
    }


    public void loadHand()  {
        for(int i = 0; i < 5; i++)   {
            hand.add(deck.pop());
            //hand.get(i).changePosition(positions.get(i));
        }
    }

    public int size()   {
        return hand.size();
    }

    public void removeCard()    {

    }

    public Deck getDeck()   {
        return deck;
    }

    public void draw()  {
        if(hand.size() < 5 && deck.size() > 0)
            hand.add(deck.draw());
    }

    public Card getCardAt(int pos)   {
        return hand.get(pos);
    }



}
