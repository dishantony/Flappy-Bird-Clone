package com.dishantpandauria.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dishantpandauria.flappybird.FlappyBirdGame;

public class MenuState extends State{

    private Texture background;
    private Texture playButton;
    private Texture gameTitle;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBirdGame.WIDTH/2,FlappyBirdGame.HEIGHT/2);
        background = new Texture("bg.png");
        playButton = new Texture("playbutton.png");
        gameTitle = new Texture("gametitle.png");
    }

    @Override
    public void handleInput() {

        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));

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
        sb.draw(background,0,0);
        sb.draw(playButton,cam.position.x - playButton.getWidth()/2,cam.position.y - playButton.getHeight()/2);
        sb.draw(gameTitle, cam.position.x - gameTitle.getWidth()/2,  cam.position.y + cam.position.y / 2  - gameTitle.getHeight()/2);
        sb.end();
    }

    @Override
    public void dispose() {

        background.dispose();
        playButton.dispose();
        gameTitle.dispose();

        System.out.println("Menu State disposed");
    }
}
