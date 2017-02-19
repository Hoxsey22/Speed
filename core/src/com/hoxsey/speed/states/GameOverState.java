package com.hoxsey.speed.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hoxsey.speed.Speed;
import com.hoxsey.speed.states.State;

/**
 * Created by Hoxsey on 2/16/2017.
 */
public class GameOverState extends State {

    public Texture background;
    public Texture statusImage;


    public GameOverState(StateManager sm, int status)  {
        super(sm);
        background = new Texture("playing_table.png");
        if(status < 1)    {
            statusImage = new Texture("winner.png");
        }
        else
            statusImage = new Texture("lose.png");
        cam.setToOrtho(false, Speed.WIDTH, Speed.HEIGHT);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
            sb.draw(background, 0,0);
            sb.draw(statusImage, Speed.WIDTH/2-statusImage.getWidth()/4, Speed.HEIGHT/2);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
