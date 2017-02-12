package com.hoxsey.speed.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hoxsey.speed.*;
import com.hoxsey.speed.cards.Card;
import com.hoxsey.speed.cards.Deck;
import com.hoxsey.speed.cards.Hand;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Hoxsey on 2/9/2017.
 */
public class PlayState extends State {

    public Texture card;
    public Deck gameCards;
    public Deck player1;
    public Deck player2;
    public Deck flip1;
    public Deck flip2;
    public Deck playable1;
    public Deck playable2;
    public Hand player1Hand;
    public Hand player2Hand;


    protected PlayState(StateManager sm) {
        super(sm);
        card = new Texture("back.png");
        cam.setToOrtho(false, card.getWidth() * 6f + 30*6, card.getHeight() * 3f + 50*3 );
        loadGame();
    }

    public void loadGame()  {
        gameCards = new Deck();
        player1 = new Deck();
        player2 = new Deck();
        flip1 = new Deck();
        flip2 = new Deck();
        playable1 = new Deck();
        playable2 = new Deck();


        for(int i = 0; i < 4; i++)
            for(int j = 1; j <= 13; j++)
                gameCards.push(new Card(i,j));
        gameCards.shuffle();

        for (int i = 0; i < 20; i++)
            player1.push(gameCards.pop());

        for (int i = 0; i < 20; i++)
            player2.push(gameCards.pop());

        for (int i = 0; i < 5; i++)
            flip1.push(gameCards.pop());

        for (int i = 0; i < 5; i++)
            flip2.push(gameCards.pop());

        playable1.push(gameCards.pop());
        playable1.flipTopCard();
        playable2.push(gameCards.pop());
        playable2.flipTopCard();

        player1Hand = new Hand(player1);
        player2Hand = new Hand(player2);



        System.out.print(gameCards.toString());
    }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float v) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
            for(int i = 0 ; i < 1; i++)   {
                for(int j = 0; j < 6; j++)   {
                    sb.draw(card, card.getWidth()*j + 25*(j+1), card.getHeight()*i +25*(i+1), card.getWidth(), card.getHeight() );
                }
            }

        sb.end();
    }

    @Override
    public void dispose() {
        card.dispose();
    }
}
