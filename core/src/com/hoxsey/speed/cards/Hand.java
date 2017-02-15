package com.hoxsey.speed.cards;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 2/12/2017.
 */
public class Hand{

    private ArrayList<Card> hand;
    private Deck deck;
    private ArrayList<Vector2> p1Positions;
    private ArrayList<Vector2> p2Positions;

    public Hand(Deck deck, int player)   {
        this.deck = deck;
        hand = new ArrayList<Card>(5);
        if(player == 1)
            p1Positions = new ArrayList<Vector2>();
        else
            p2Positions = new ArrayList<Vector2>();
        loadHand();
        loadPositions(player);


    }

    public void loadPositions(int player) {

        // player 1
        if(player == 1) {
            p1Positions.add(new Vector2(25,25));
            p1Positions.add(new Vector2(257,25));
            p1Positions.add(new Vector2(489,25));
            p1Positions.add(new Vector2(721,25));
            p1Positions.add(new Vector2(953,25));
            p1Positions.add(new Vector2(1185,25));  // deck position

            for(int i = 0; i < hand.size(); i++)   {
                hand.get(i).setPosition(p1Positions.get(i));
            }
            deck.changePosition(p1Positions.get(5));
        }
        //player 2
        else    {
            p2Positions.add(new Vector2(25,776));
            p2Positions.add(new Vector2(257,776));
            p2Positions.add(new Vector2(489,776));
            p2Positions.add(new Vector2(721,776));
            p2Positions.add(new Vector2(953,776));
            p2Positions.add(new Vector2(1185,776));  // deck position

            for(int i = 0; i < hand.size(); i++)   {
                hand.get(i).changePosition(p2Positions.get(i));
            }
            deck.changePosition(p2Positions.get(5));

        }






    /*
        if(player == 1) {
            for (int i = 0; i < hand.size(); i++) {
                if (i == 0) {
                    hand.get(i).setPosition(new Vector2(25, 25));
                    System.out.println(hand.get(i).getX()+","+hand.get(i).getY());
                }
                else {
                    hand.get(i).setPosition(new Vector2(hand.get(i - 1).getX() + deck.getTopCard().getImage().getWidth() + 10, 25));
                    System.out.println(hand.get(i).getX()+","+hand.get(i).getY());
                }
            }
            deck.changePosition(new Vector2(hand.get(hand.size() - 1).getX() + deck.getTopCard().getImage().getWidth() + 10, 25));
            System.out.println(deck.getX()+","+deck.getY());
            System.out.println("=========== Player 2 =============");
        }
        else    {
            deck.changePosition(new Vector2(25, 2*(deck.getTopCard().getImage().getHeight() + 65)));
            System.out.println(deck.getX()+","+deck.getY());
            for (int i = 0; i < hand.size(); i++) {
                if (i == 0) {
                    hand.get(i).setPosition(new Vector2(deck.getX() + deck.getTopCard().getImage().getWidth() + 10, deck.getY()));
                    System.out.println(hand.get(i).getX()+","+hand.get(i).getY());
                }
                else {
                    hand.get(i).setPosition(new Vector2(hand.get(i - 1).getX() + deck.getTopCard().getImage().getWidth() + 10,
                            hand.get(i - 1).getY()));
                    System.out.println(hand.get(i).getX()+","+hand.get(i).getY());
                }
            }
        }
        */
    }


    public void loadHand()  {
        for(int i = 0; i < 5; i++)   {
            hand.add(deck.pop());
            //hand.get(i).changePosition(positions.get(i));
        }
    }

    public Card hit(float x, float y)   {
        for(int i = 0; i < hand.size(); i++)   {
            if(hand.get(i).getBounds().contains(x,y))
                return hand.get(i);
        }
        return null;
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

    public void remove(Card card)    {
        hand.remove(hand.indexOf(card));
    }




}
