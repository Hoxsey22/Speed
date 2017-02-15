package com.hoxsey.speed.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;

/**
 * Created by Hoxsey on 2/9/2017.
 */
public class Card{

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
    private Rectangle bounds;

    public Card(int suit, int value)   {
        this.suit = suit;
        this.value = value;
        loadImage();
        bounds = new Rectangle(0,0,getImage().getWidth(),getImage().getHeight());
    }

    public Card(int suit, int value, int x, int y)   {
        position = new Vector2(x,y);
        this.suit = suit;
        this.value = value;
        loadImage();
        bounds = new Rectangle(0,0,getImage().getWidth(),getImage().getHeight());
    }

    private void loadImage()    {
        String filename = "";
        filename += suit+""+value+".png";
        setImage(filename);
    }

    public int getSuit()    {
        return suit;
    }

    public int getValue()    {
        return value;
    }

    public void setPosition(Vector2 position)   {
        this.position = position;
        bounds.setPosition(position);
    }

    public void setPosition(float x, float y)    {
        position.set(x,y);
        bounds.setPosition(x,y);
    }

    public void changePosition(Vector2 position)    {
        this.position = position;
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
        switch(value) {
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
            default: return "null";
        }
    }

    public void setImage(String filename)  {
        if(value == 0)
            image = new Texture("back.png");
        else
            image = new Texture(filename);
    }

    public Rectangle getBounds()    {
        return bounds;
    }

    public Texture getImage()   {
        return image;
    }

    public boolean isNeighbors(int value)    {
        if(value == 1 && (this.value == 13 || this.value == 2 ))
            return true;
        else if(value == 13 && (this.value == 1 || this.value == 12))
            return true;
        else if(value == this.value +1 || value == this.value - 1)
            return true;
        else
            return false;
    }

    


    public String toString()   {
        return getValueString()+" of "+getSuitString();
    }


}
