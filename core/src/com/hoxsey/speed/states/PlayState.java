package com.hoxsey.speed.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hoxsey.speed.*;
import com.hoxsey.speed.cards.Card;
import com.hoxsey.speed.cards.Deck;
import com.hoxsey.speed.cards.Hand;
import com.hoxsey.speed.players.NPC;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Hoxsey on 2/9/2017.
 */
public class PlayState extends State {

    public Texture card;
    public NPC npc;
    public Texture background;
    public Deck gameCards;
    public Deck player1;
    public Deck player2;
    public Deck flip1;
    public Deck flip2;
    public Deck playable1;
    public Deck playable2;
    public Hand player1Hand;
    public Hand player2Hand;
    public boolean isSelected;
    private ShapeRenderer sr;
    private Card selectedCard;
    private boolean flipFlag;
    //debugg
    private float time;
    //


    protected PlayState(StateManager sm, int difficulty) {
        super(sm);
        card = new Texture("back.png");
        background = new Texture("playing_table.png");
        cam.setToOrtho(false, card.getWidth() * 6f + 30*6, card.getHeight() * 3f + 50*3 );
        npc = new NPC(difficulty);

        loadGame();
        isSelected = false;
        Card selectedCard = new Card(0,0);
        sr = new ShapeRenderer();
        flipFlag = false;

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
            npc.addToDeck(gameCards.pop());
        npc.setupHand();

        for (int i = 0; i < 5; i++)
            flip1.push(gameCards.pop());
        flip1.changePosition(new Vector2( flip1.getTopCard().getImage().getWidth()-15, flip1.getTopCard().getImage().getHeight() + 65));

        for (int i = 0; i < 5; i++)
            flip2.push(gameCards.pop());
        flip2.changePosition(new Vector2(65 + 4 * (flip2.getTopCard().getImage().getWidth() + 10),
                flip2.getTopCard().getImage().getHeight() + 65));

        playable1.push(gameCards.pop());
        playable1.flipTopCard();
        playable1.changePosition(new Vector2(flip1.getX()+playable1.getImage().getWidth()+40,flip1.getY()));

        playable2.push(gameCards.pop());
        //debug
        playable2.push(new Card(0,2));
        //=========//
        playable2.flipTopCard();
        playable2.changePosition(new Vector2(playable1.getX()+playable2.getImage().getWidth()+40,flip1.getY()));

        player1Hand = new Hand(player1, 1);
        //player2Hand = new Hand(player2, 2);
        npc.loadFaceDecks(playable1,playable2);



        System.out.print(gameCards.toString());
    }


    @Override
    protected void handleInput() {

        Gdx.input.setInputProcessor(new InputProcessor() {

            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                if(keycode == Input.Keys.A)
                    gameover();
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                Vector3 mousePos = new Vector3( Gdx.input.getX(), Gdx.input.getY(), 0);
                cam.unproject(mousePos);
                if(player1Hand.hit(mousePos.x, mousePos.y) != null && isSelected == false) {
                    selectedCard = player1Hand.hit(mousePos.x, mousePos.y);
                    isSelected = true;
                }
                if(flip2.getBounds().contains(mousePos.x, mousePos.y))    {
                    flipFlag = true;
                    isSelected = false;
                }
                /*debug
                if(flip2.getBounds().contains(mousePos.x, mousePos.y))    {
                    playable2.push(flip2.draw());
                    isSelected = false;
                }*/
                System.out.println(flipFlag+":"+npc.isFlipFlag());
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {

                if(isSelected) {
                    if (checkCardCollision(selectedCard, playable1)) {
                        playable1.push(selectedCard);
                        player1Hand.remove(selectedCard);
                        player1Hand.reposition();
                        isSelected = false;
                        return false;
                    } else if (checkCardCollision(selectedCard, playable2)) {
                        playable2.push(selectedCard);
                        player1Hand.remove(selectedCard);
                        player1Hand.reposition();
                        isSelected = false;
                        return false;
                    }
                }
                if(isSelected) {
                    System.out.println("Fire");
                    player1Hand.reposition(selectedCard);
                    selectedCard = null;
                }
                isSelected = false;
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {

                if(isSelected && selectedCard != null) {
                    Vector3 mousePos = new Vector3( Gdx.input.getX(), Gdx.input.getY(), 0); //Get the mouse-x and y like in your code
                    cam.unproject(mousePos);
                    selectedCard.setPosition(mousePos.x-selectedCard.getImage().getWidth()/2, (mousePos.y-selectedCard.getImage().getHeight()/2));

                }
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        });
    }

    public boolean checkCardCollision(Card card, Deck deck)    {
        if(card.getBounds().overlaps(deck.getBounds()))    {
            System.out.println(card.getValue()+" : "+deck.getTopCard().getValue());
            return deck.getTopCard().isNeighbors(card.getValue());
        }
        else    {
            System.out.println(card.getValue()+" : "+deck.getTopCard().getValue());
            return false;
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        time +=dt;

        if(npc.isDone())    {
            sm.set(new GameOverState(sm,1));
        }
        if(player1Hand.size() == 0 && player1Hand.getDeck().size() == 0)    {
            sm.set(new GameOverState(sm,0));
        }

        npc.update(dt);

        if(flipFlag && npc.isFlipFlag())    {
            if(flip1.isEmpty()) {
                flip1 = playable1;
                flip1.flipDeck();
                playable1.emptyDeck();

                flip2 = playable2;
                flip2.flipDeck();
                playable2.emptyDeck();
            }
            playable1.push(flip1.draw());
            playable2.push(flip2.draw());
            flipFlag = false;
            npc.unflagFlip();
        }


    }

    public void gameover()  {
        sm.set(new GameOverState(sm,0));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);

        sb.begin();
            sb.draw(background,0,0);

            sb.draw(flip1.getImage(),
                    flip1.getX(),
                    flip1.getY(),
                    flip1.getImage().getWidth(),
                    flip1.getImage().getHeight());

            sb.draw(flip2.getImage(),
                    flip2.getX(),
                    flip2.getY(),
                    flip2.getImage().getWidth(),
                    flip2.getImage().getHeight());

            sb.draw(playable1.getTopCard().getImage(),
                    playable1.getX(),
                    playable1.getY(),
                    playable1.getTopCard().getImage().getWidth(),
                    playable1.getTopCard().getImage().getHeight());

            sb.draw(playable2.getTopCard().getImage(),
                    playable2.getX(),
                    playable2.getY(),
                    playable2.getTopCard().getImage().getWidth(),
                    playable2.getTopCard().getImage().getHeight());

            sb.draw(player1Hand.getDeck().getImage(),
                    player1Hand.getDeck().getX(),
                    player1Hand.getDeck().getY(),
                    player1Hand.getDeck().getImage().getWidth(),
                    player1Hand.getDeck().getImage().getHeight());

            sb.draw(npc.getDeck().getImage(),
                    npc.getDeck().getX(),
                    npc.getDeck().getY(),
                    npc.getDeck().getImage().getWidth(),
                    npc.getDeck().getImage().getHeight());

            for(int i = 0 ; i < player1Hand.size(); i++)   {
                sb.draw(player1Hand.getCardAt(i).getImage(),
                        player1Hand.getCardAt(i).getX(),
                        player1Hand.getCardAt(i).getY(),
                        player1Hand.getCardAt(i).getImage().getWidth(),
                        player1Hand.getCardAt(i).getImage().getHeight() );

            }

            for(int i = 0 ; i < npc.getHand().size(); i++)   {
                sb.draw(npc.getHand().getCardAt(i).getImage(),
                        npc.getHand().getCardAt(i).getX(),
                        npc.getHand().getCardAt(i).getY(),
                        npc.getHand().getCardAt(i).getImage().getWidth(),
                        npc.getHand().getCardAt(i).getImage().getHeight() );

            }
        sb.end();

        sr.setProjectionMatrix(cam.combined);
        if(npc.getSelectedCard() != null) {
            sr.begin(ShapeRenderer.ShapeType.Line);
            sr.setColor(Color.RED);
            sr.rect(npc.getSelectedCard().getBounds().getX(),
                    npc.getSelectedCard().getBounds().getY(),
                    npc.getSelectedCard().getBounds().getWidth(),
                    npc.getSelectedCard().getBounds().getHeight());
            sr.end();
        }



    }

    @Override
    public void dispose() {
        card.dispose();
        background.dispose();
        player1Hand.dispose();
        player2Hand.dispose();
        player1.dispose();
        player2.dispose();
        flip1.dispose();
        flip2.dispose();
        playable1.dispose();
        playable2.dispose();
    }
}
