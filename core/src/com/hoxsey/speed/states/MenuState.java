package com.hoxsey.speed.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hoxsey.speed.Speed;

/**
 * Created by Hoxsey on 2/9/2017.
 */
public class MenuState extends State {
    private Texture background;
    private Texture onePlayerButton;
    private Texture twoPlayerButton;

    public MenuState(StateManager sm) {
        super(sm);
        background = new Texture("Title_screen.png");
        onePlayerButton = new Texture("one_player_button.png");
        twoPlayerButton = new Texture("two_player_button.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched())    {
            sm.set(new PlayState(sm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
            sb.draw(background, 0,0,Speed.WIDTH, Speed.HEIGHT);
            sb.draw(onePlayerButton, (Speed.WIDTH/2)-(onePlayerButton.getWidth()/2), (Speed.HEIGHT/2)-25);
            sb.draw(twoPlayerButton, (Speed.WIDTH/2)-(onePlayerButton.getWidth()/2), (Speed.HEIGHT/2)- 50 - (onePlayerButton.getHeight()));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        onePlayerButton.dispose();
        twoPlayerButton.dispose();
    }
}
