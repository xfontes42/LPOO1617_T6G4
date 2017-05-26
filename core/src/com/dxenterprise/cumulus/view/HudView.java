package com.dxenterprise.cumulus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dxenterprise.cumulus.controller.SinglePGameController;

import static com.dxenterprise.cumulus.view.SinglePGameView.PIXEL_TO_METER;
import static com.dxenterprise.cumulus.view.SinglePGameView.VIEWPORT_WIDTH;

/**
 * Created by Xavier Fontes on 26/05/2017.
 */

public class HudView implements Disposable{
    public Stage stage;
    private Viewport viewport;

    private Integer timer;
    private float timeCount;
    private static Integer score;

    private Label timerLabel;
    private static Label scoreLabel;

    public HudView(SpriteBatch sb){
        //define our tracking variables
        score = 0;
        timer = 0;
        timeCount = 0;

        //setup the HUD viewport using a new camera seperate from our gamecam
        //define our stage using that viewport and our games spritebatch
        viewport = new FitViewport(VIEWPORT_WIDTH/PIXEL_TO_METER, VIEWPORT_WIDTH/PIXEL_TO_METER* ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));
        stage = new Stage(viewport, sb);

        //define a table used to organize our hud's labels
        Table table = new Table();
        //Top-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);

        //define our labels using the String, and a Label style consisting of a font and color
        timerLabel = new Label(String.format("%03d", timer), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        scoreLabel =new Label(String.format("%09d", score), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        timerLabel.setSize(VIEWPORT_WIDTH/(PIXEL_TO_METER*3f), VIEWPORT_WIDTH/PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth())/4f);
        scoreLabel.setSize(VIEWPORT_WIDTH/3f, VIEWPORT_WIDTH* ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth())/4f);

        //add our labels to our table, padding the top, and giving them all equal width with expandX
        table.add(timerLabel).padTop(100);
        table.add(scoreLabel).padTop(100);

        //add our table to the stage
        stage.addActor(table);
    }

    public void update(float dt){
        timeCount += dt;
        if(timeCount >= 1){
            timer++;
            timerLabel.setText(String.format("%03d", timer));
            timeCount = 0;
        }
    }


    public static void addScore(int value){
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    @Override
    public void dispose() { stage.dispose(); }

}
