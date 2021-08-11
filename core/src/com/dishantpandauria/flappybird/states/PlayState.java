package com.dishantpandauria.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dishantpandauria.flappybird.FlappyBirdGame;
import com.dishantpandauria.flappybird.sprites.Bird;
import com.dishantpandauria.flappybird.sprites.Tube;

public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -80;
    public static int score;
    private Bird bird;
    private Texture background;
    private Texture ground;
    private Vector2 groundPos1,groundPos2;

    BitmapFont scoreFont;


    private Array<Tube> tubes;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        cam.setToOrtho(false, FlappyBirdGame.WIDTH/2,FlappyBirdGame.HEIGHT/2);
        background = new Texture("bg.png");
        ground = new Texture("newground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth/2,GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth/2) + ground.getWidth(),GROUND_Y_OFFSET);
        score = 0;
        scoreFont = new BitmapFont();
        scoreFont.setColor(Color.NAVY);
        scoreFont.getData().setScale(1);

        tubes = new Array<Tube>();

        for(int i=1; i<=TUBE_COUNT; i++){
            tubes.add(new Tube(i*(TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    public void handleInput() {

        if (Gdx.input.justTouched()){
            bird.jump();
        }

    }

    @Override
    public void update(float dt) {

        handleInput();
        updateGround();
        bird.update(dt);

        cam.position.x = bird.getPosition().x + 80;

        for(int i=0;i<tubes.size;i++){
            Tube tube = tubes.get(i);
            if(tube.getPosTopTube().x + tube.getTopTube().getWidth() < (cam.position.x - cam.viewportWidth/2)){
                tube.reposition(tube.getPosBotTube().x + (TUBE_COUNT * (TUBE_SPACING + Tube.TUBE_WIDTH)));
                score++;
                System.out.println(score);
            }

            if(tube.collides(bird.getBounds())){
                gsm.set(new GameOverState(gsm));
            }

            if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
                gsm.set(new GameOverState(gsm));
            }

        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x - (cam.viewportWidth/2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x,bird.getPosition().y);
        for(Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground,groundPos1.x,groundPos1.y);
        sb.draw(ground,groundPos2.x,groundPos2.y);
        scoreFont.draw(sb,"Score : " + Integer.toString(score),cam.position.x -(cam.viewportWidth/2) + 30,30);

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        for(Tube tube : tubes){
            tube.dispose();
        }
        ground.dispose();
        scoreFont.dispose();
        System.out.println("Play State disposed");
    }

    public void updateGround(){
        if(cam.position.x - (cam.viewportWidth/2) > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth() * 2,0);
        }
        if(cam.position.x - (cam.viewportWidth/2) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() * 2,0);
        }
    }


}
