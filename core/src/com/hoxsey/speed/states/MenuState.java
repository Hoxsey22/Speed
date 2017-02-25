package com.hoxsey.speed.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.hoxsey.speed.Speed;

/**
 * Created by Hoxsey on 2/9/2017.
 */
public class MenuState extends State {
    private Texture background;
    private Texture onePlayerButton;
    private Texture twoPlayerButton;
    private Texture hardButton;
    private Texture normalButton;
    private Texture easyButton;
    private ShapeRenderer sr;
    private Rectangle pos1;
    private Rectangle pos2;
    private Rectangle pos3;

    public MenuState(StateManager sm) {
        super(sm);
        background = new Texture("Title_screen.png");
        onePlayerButton = new Texture("one_player_button.png");
        twoPlayerButton = new Texture("two_player_button.png");

        hardButton = new Texture("hard_button.png");
        normalButton = new Texture("normal_button.png");
        easyButton = new Texture("easy_button.png");
        pos1 = new Rectangle(299,215,easyButton.getWidth(),easyButton.getHeight());
        pos2 = new Rectangle(299,117,easyButton.getWidth(),easyButton.getHeight());
        pos3 = new Rectangle(299,19,easyButton.getWidth(),easyButton.getHeight());
        sr = new ShapeRenderer();
        cam.setToOrtho(false, Speed.WIDTH, Speed.HEIGHT);

    }

    @Override
    public void handleInput() {

        Vector3 mousePos = new Vector3( Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(mousePos);
        if(Gdx.input.justTouched()) {
            if (pos1.contains(mousePos.x, mousePos.y)) {
                sm.set(new PlayState(sm, 1));
                dispose();
            }
            else if (pos2.contains(mousePos.x, mousePos.y)) {
                sm.set(new PlayState(sm, 2));
                dispose();
            }
            else if (pos3.contains(mousePos.x, mousePos.y)) {
                sm.set(new PlayState(sm, 3));
                dispose();
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
            sb.draw(background, 0,0,Speed.WIDTH, Speed.HEIGHT);
            sb.draw(easyButton, pos1.x, pos1.y);
            sb.draw(normalButton, pos2.x, pos2.y);
            sb.draw(hardButton, pos3.x, pos3.y);
        sb.end();
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.RED);
        sr.rect(pos1.getX(),
                pos1.getY(),
                pos1.getWidth(),
                pos1.getHeight());
        sr.rect(pos2.getX(),
                pos2.getY(),
                pos2.getWidth(),
                pos2.getHeight());
        sr.rect(pos3.getX(),
                pos3.getY(),
                pos3.getWidth(),
                pos3.getHeight());
        sr.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        onePlayerButton.dispose();
        twoPlayerButton.dispose();
        easyButton.dispose();
        normalButton.dispose();
        hardButton.dispose();
    }
}
