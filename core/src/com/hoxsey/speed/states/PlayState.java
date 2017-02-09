package com.hoxsey.speed.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hoxsey.speed.*;

/**
 * Created by Hoxsey on 2/9/2017.
 */
public class PlayState extends State {

    public Texture card;

    protected PlayState(StateManager sm) {
        super(sm);
        card = new Texture("back.png");
        cam.setToOrtho(false, card.getWidth() * 6f + 30*6, card.getHeight() * 3f + 50*3 );
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
            for(int i = 0 ; i < 3; i++)   {
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
