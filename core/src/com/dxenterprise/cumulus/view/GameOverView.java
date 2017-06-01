package com.dxenterprise.cumulus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dxenterprise.cumulus.MyCumulusGame;
import com.dxenterprise.cumulus.utils.fonts;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Xavier Fontes on 23/05/2017.
 */

public class GameOverView extends ScreenAdapter {
    int highscore = 0;
    /**
     * The game this screen belongs to.
     */
    private final MyCumulusGame game;

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public GameOverView(MyCumulusGame game, int score) {

        this.game = game;
        highscore = score;
        //loadAssets();
        camera = new OrthographicCamera();
        viewport = new FitViewport(MENU_WIDTH, MENU_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        stage = new Stage(viewport, game.getBatch());
        skinButtons = new Skin(Gdx.files.internal("SkinMainMenu/glassy-ui.json"));
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        createTable();
        fillTable(score);
        stage.addActor(table);
        showBack();

    }

    /**
     * Menu width (in pixels).
     */
    private final int MENU_WIDTH = 1080;

    /**
     * Menu height (in pixels).
     */
    private final int MENU_HEIGHT = 720;

    /**
     * Vertical menu padding.
     */
    private final int DELTA_Y_MENU = 100;

    /**
     * The staged used to display the menu.
     */
    private Stage stage;

    /**
     * The back button present in the lower right corner.
     */
    private ImageButton BackButton;

    /**
     * The viewport used to display the menu.
     */
    private Viewport viewport;

    /**
     * The camera used to display the menu.
     */
    private OrthographicCamera camera;

    /**
     * The skin buttons used in the menu.
     */
    private Skin skinButtons;

    /**
     * The table used to show the score.
     */
    private Table table;

    /**
     * The labels used to show the player's score.
     */
    private Label scoreString, scoreValue;

    /**
     * Creates the table that will align the text in this screen.
     */
    private void createTable(){
        table = new Table();
        table.center();
        table.setFillParent(true);
    }

    /**
     * Fills the table with the user's score.
     * @param score the score achieved by the player this run.
     */
    private void fillTable(int score){
        scoreString = new Label("Score: ", new Label.LabelStyle(fonts.getInstance().getClouds(), Color.WHITE));
        scoreValue = new Label(String.format("%06d", score), new Label.LabelStyle(fonts.getInstance().getRancho(), Color.WHITE));
        table.row();

        table.add(scoreString).expandX().right();
        table.add(scoreValue).expandX().left();
    }

    /**
     * Displays the back label.
     */
    private void showBack(){
        Drawable buttonDrawableBack = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("buttonBack.png")));
        BackButton = new ImageButton(buttonDrawableBack);
        BackButton.setSize(MENU_WIDTH/7,MENU_HEIGHT/7);
        BackButton.setPosition(7*MENU_WIDTH/8 - BackButton.getWidth()/2, MENU_HEIGHT/7 -DELTA_Y_MENU);
        stage.addActor(BackButton);
    }

    /**
     * Shows the screen and the menu title.
     */
    @Override
    public void show() {
        Drawable buttonDrawableGameOver = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("textGameOver.png")));
        ImageButton GameOverLabel = new ImageButton(buttonDrawableGameOver);
        GameOverLabel.setSize(MENU_WIDTH/1.5f,MENU_HEIGHT/3);
        GameOverLabel.setPosition(MENU_WIDTH/2-GameOverLabel.getWidth()/2, MENU_HEIGHT/1.9f +GameOverLabel.getHeight()/2);
        stage.addActor(GameOverLabel);
    }

    /**
     * Renders the screen and waits for any input to return to the previous screen.
     * @param delta time interval between frames
     */
    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0.4f, 0.737f, 0.929f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        checkForBack();


        stage.act();
        stage.draw();
    }

    /**
     * Waits for any input that is used to return back (ESC [PC]/Back [Android] and Back label)
     */
    private void checkForBack(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) ||
                BackButton.isPressed()){
            Gdx.input.vibrate(50);
            storeHighscore(highscore);
            dispose();
            game.setScreen(new MainMenuView(game));
        }
    }

    /**
     * Stores the score in the high scores table.
     * @param highscore the new high score achieved
     */
    private void storeHighscore(int highscore) {
        ArrayList<Integer> scores = game.getScores();
        scores.add(highscore);
        Collections.sort(scores);
        Collections.reverse(scores);
        scores.remove(scores.size()-1);
        game.setScores(scores);

    }

    /**
     * Resizes the screen.
     * @param width the new screen width
     * @param height the new screen height
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }

    /**
     * Pauses the app.
     */
    @Override
    public void pause() {

    }

    /**
     * Resumes the app.
     */
    @Override
    public void resume() {

    }

    /**
     * Hides the app.
     */
    @Override
    public void hide() {

    }

    /**
     * Disposes this screen.
     */
    @Override
    public void dispose() {
        stage.dispose();
        skinButtons.dispose();
    }
}
