package com.dishantpandauria.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dishantpandauria.flappybird.FlappyBirdGame;

public class GameOverState extends State{

    private Texture background;
    private Texture gameOver;
    private Texture playAgain;
    private BitmapFont font;

    protected GameOverState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBirdGame.WIDTH/2,FlappyBirdGame.HEIGHT/2);
        background = new Texture("bg.png");
        gameOver = new Texture("gameover.png");
        playAgain = new Texture("playagain.png");
        font = new BitmapFont();
        font.setColor(Color.NAVY);
        font.getData().setScale(1);


    }

    @Override
    protected void handleInput() {

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
        sb.draw(gameOver,cam.position.x - gameOver.getWidth()/2,cam.position.y - gameOver.getHeight()/2);
        sb.draw(playAgain,cam.position.x - playAgain.getWidth()/2, cam.position.y - playAgain.getHeight()/2 - 100);
        font.draw(sb,"Your Score : " + PlayState.score,cam.position.x,cam.position.y + 80);
        sb.end();
    }

    @Override
    public void dispose() {

        background.dispose();
        gameOver.dispose();
        font.dispose();
        playAgain.dispose();
    }


}
