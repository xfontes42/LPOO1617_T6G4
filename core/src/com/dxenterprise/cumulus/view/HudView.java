package com.dxenterprise.cumulus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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

    /**
     * The stage used to display the HUD.
     */
    public Stage stage;

    /**
     * The viewport used by the HUD.
     */
    private Viewport viewport;

    /**
     * The padding (in pixels) from the top of the screen to show the HUD elements.
     */
    private final int PADDING = 30;

    /**
     * The game timer (measured in seconds).
     */
    private Integer timer;

    /**
     * A timer used for measuring time somewhat more precisely.
     */
    private float timeCount;

    /**
     * The game score.
     */
    private static Integer score;

    /**
     * A label used to display the timer.
     */
    private Label timerLabel;

    /**
     * A label used to display the score.
     */
    private static Label scoreLabel;

    /**
     * A timer used to accompany the timer.
     */
    private Label timerNameLabel;

    /**
     * A timer used to accompany the score.
     */
    private Label scoreNameLabel;

    /**
     * A table used to display the HUD elements evenly.
     */
    private Table table;

    /**
     * The font used in the HUD.
     */
    private BitmapFont cloudsfont;

    /**
     * Creates the HUD.
     * @param sb used to show the sprites.
     */
    public HudView(SpriteBatch sb){
        initElements(sb);
        initTable();
        initFont();
        initLabels();
        fillTable();

        //add our table to the stage
        stage.addActor(table);
    }

    /**
     * Initialises the score, timer and viewports.
     * @param sb used to show the sprites.
     */
    private void initElements(SpriteBatch sb){
        //define our tracking variables
        score = 0;
        timer = 0;
        timeCount = 0;

        //setup the HUD viewport using a new camera seperate from our gamecam
        //define our stage using that viewport and our games spritebatch
        viewport = new FitViewport(PADDING*VIEWPORT_WIDTH, PADDING*VIEWPORT_WIDTH *((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));
        stage = new Stage(viewport, sb);
    }

    /**
     * Creates a table that fills the entire screen and is aligned to the top.
     */
    private void initTable(){
        table = new Table();
        table.top();
        table.setFillParent(true);
    }

    /**
     * Creates the BitmapFont used to display the HUD elements.
     */
    private void initFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/AlphaClouds.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        parameter.borderWidth = 0.5F;
        parameter.borderColor = Color.WHITE;
        cloudsfont = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();
    }

    /**
     * Creates the labels shown in the table.
     */
    private void initLabels(){
        //define our labels using the String, and a Label style consisting of a font and color
        timerLabel = new Label(String.format("%03d", timer), new Label.LabelStyle(cloudsfont, Color.WHITE));
        scoreLabel =new Label(String.format("%06d", score), new Label.LabelStyle(cloudsfont, Color.WHITE));
        timerNameLabel = new Label("Timer: ", new Label.LabelStyle(cloudsfont, Color.WHITE));
        scoreNameLabel = new Label("Score: ", new Label.LabelStyle(cloudsfont, Color.WHITE));
    }

    /**
     * Fills the table with the labels.
     */
    private void fillTable(){
        //add our labels to our table, padding the top, and giving them all equal width with expandX
        table.add(timerNameLabel).expandX().right().padTop(PADDING);
        table.add(timerLabel).expandX().left().padTop(PADDING);
        table.add().expandX().padTop(PADDING);
        table.add().expandX().padTop(PADDING);
        table.add(scoreNameLabel).expandX().right().padTop(PADDING);
        table.add(scoreLabel).expandX().left().padTop(PADDING);
        table.row();
    }

    /**
     * Updates the timer and the score.
     * @param dt difference of time between updates
     */
    public void update(float dt){
        timeCount += dt;
        if((int)(timeCount*100) >= 1)
            scoreLabel.setText(String.format("%06d", score));
        if(timeCount >= 1){
            timer++;
            timerLabel.setText(String.format("%03d", timer));
            timeCount = 0;
        }
    }

    /**
     * Sets the score to a specific value.
     * @param value the new score
     */
    public static void setScore(int value){
        score = value;
    }

    /**
     * Disposes the HUD.
     */
    @Override
    public void dispose() { stage.dispose(); }

}
