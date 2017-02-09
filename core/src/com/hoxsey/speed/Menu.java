package com.hoxsey.speed;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Hoxsey on 2/9/2017.
 */
public class Menu extends State {
    private Texture background;
    private Texture onePlayerButton;
    private Texture twoPlayerButton;

    public Menu(StateManager sm) {
        super(sm);
        background = new Texture("Title_screen.png");
        onePlayerButton = new Texture("one_player_button.png");
        twoPlayerButton = new Texture("two_player_button.png");
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
            sb.draw(background, 0,0,background.getWidth(), background.getHeight());
            sb.draw(onePlayerButton, (background.getWidth()/2)-(onePlayerButton.getWidth()/2), (Speed.HEIGHT/2)-25);
            sb.draw(twoPlayerButton, (background.getWidth()/2)-(onePlayerButton.getWidth()/2), (Speed.HEIGHT/2)- 50 - (onePlayerButton.getHeight()));
        sb.end();
    }
}
