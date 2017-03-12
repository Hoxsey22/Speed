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
    private int player;

    public Hand(int player)   {
        deck = new Deck();
        hand = new ArrayList<Card>(5);
        this.player = player;

        if(player == 1)
            p1Positions = new ArrayList<Vector2>();
        else
            p2Positions = new ArrayList<Vector2>();

    }

    public Hand(Deck deck, int player)   {
        this.deck = deck;
        hand = new ArrayList<Card>(5);
        this.player = player;
        if(player == 1)
            p1Positions = new ArrayList<Vector2>();
        else
            p2Positions = new ArrayList<Vector2>();
        init();

    }

    public void init()  {
        loadHand();
        loadPositions();
    }

    public void loadPositions() {

        // player 1
        if(player == 1) {
            p1Positions.add(new Vector2(25,25));
            p1Positions.add(new Vector2(257,25));
            p1Positions.add(new Vector2(489,25));
            p1Positions.add(new Vector2(721,25));
            p1Positions.add(new Vector2(953,25));
            p1Positions.add(new Vector2(1185,25));  // deck position

            for(int i = 0; i < hand.size(); i++)   {
                hand.get(i).setPosition(new Vector2(p1Positions.get(i)));
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
                hand.get(i).setPosition(p2Positions.get(i));
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

    public void addToDeck(Card card)    {
        deck.push(card);
    }


    public void setDeck(Deck deck)   {
        this.deck = deck;
    }

    public Card hit(float x, float y)   {
        for(int i = 0; i < hand.size(); i++)   {
            if(hand.get(i).getBounds().contains(x,y))
                return hand.get(i);
        }
        if(deck.getBounds().contains(x,y))    {
            draw();
            return null;
        }
        return null;
    }

    public int size()   {
        return hand.size();
    }

    public Deck getDeck()   {
        return deck;
    }

    public void draw()  {
        if(hand.size() < 5 && deck.size() > 0) {
            hand.add(deck.draw());
            reposition();
        }

    }

    public Card getCardAt(int pos)   {
        return hand.get(pos);
    }

    public void setHand()   {
        hand.clear();
        for(int i = 0; i < 5; i++)   {
            hand.add(new Card(0,(i+1)));
        }
        reposition();
    }

    public void remove(Card card)    {
        hand.remove(hand.indexOf(card));
        reposition();
    }

    public ArrayList<Card> getHand()    {
        return hand;
    }

    public boolean isEmpty()   {
        if(hand.isEmpty())
            return true;
        return false;
    }

    public void reposition()    {
        for (int i = 0; i < hand.size(); i++)
            if(player == 1 && hand.get(i) != null)
                hand.get(i).setPosition(new Vector2(p1Positions.get(i)));
            else if (player == 2 && hand.get(i) != null)
                hand.get(i).setPosition(new Vector2(p2Positions.get(i)));
    }
    public void reposition(Card card)    {
        System.out.println("repos card: "+hand.indexOf(card));
        hand.get(hand.indexOf(card)).setPosition(new Vector2(p1Positions.get(hand.indexOf(card))));
        for(int i = 0; i < p1Positions.size(); i++)   {
            System.out.println(p1Positions.get(i).toString());
        }
    }

    public void dispose()   {
        for(int i = 0; i < hand.size(); i++)   {
            hand.remove(i);
        }
        deck.dispose();

    }


}
