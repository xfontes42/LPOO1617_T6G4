package com.dxenterprise.cumulus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dxenterprise.cumulus.MyCumulusGame;

/**
 * Created by Xavier Fontes on 28/04/2017.
 */

public class MainMenuView extends ScreenAdapter {

    /**
     * Menu width (in pixels).
     */
    private final int MAIN_MENU_WIDTH = 1080;

    /**
     * Menu height (in pixels).
     */
    private final int MAIN_MENU_HEIGHT = 720;

    /**
     * A vertical menu padding.
     */
    private final int DELTA_Y_MENU = 100;

    /**
     * A horizontal menu padding.
     */
    private final int DELTA_X_MENU = 150;

    /**
     * Button maximum width.
     */
    private final int BUTTON_SIZE_X = MAIN_MENU_WIDTH/5;

    /**
     * Button maximum height.
     */
    private final int BUTTON_SIZE_Y = MAIN_MENU_HEIGHT/4;

    /**
     * The game this menu belongs to.
     */
    private MyCumulusGame game;

    /**
     * The stage used to display the menu.
     */
    private Stage stage;

    /**
     * The buttons used to launch the game and other menus.
     */
    private ImageButton credits, highscores, singlePlayer, settings, title;

    /**
     * The table used to display the buttons evenly on the screen.
     */
    private Table table;

    /**
     * The viewport used to display the menu.
     */
    private Viewport viewport;

    /**
     * The camera used to display the menu.
     */
    private OrthographicCamera camera;

    /**
     * The buttons used in the menu.
     */
    private Skin skinButtons;

    /**
     * The name of the music that plays in the menu.
     */
    private final String musicName = "BitQuest.mp3";

    /**
     * The music that plays in the menu.
     */
    private Music music;

    /**
     * Creates the main menu.
     * @param game the game instance this menu belongs to.
     */
    public MainMenuView(MyCumulusGame game){
        this.game = game;
       // skinButtons = new Skin(Gdx.files.internal("SkinMainMenu/glassy-ui.json"));
        loadAssets();
        camera = new OrthographicCamera();
        viewport = new FitViewport(MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        stage = new Stage(viewport, game.getBatch());
        loadMusic();
    }

    /**
     * Loads the menu music.
     */
    private void loadMusic(){
        music = game.getAssetManager().get(musicName);
        music.setLooping(true);
        music.setVolume(0.8f);
        music.play();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    /**
     * Loads the menu assets.
     */
    private void loadAssets() {
        ///game.getAssetManager().load("iconCheck.png",Texture.class); //todo ver a necessidade isto
        loadMainMenuAssets();
        loadPauseMenuAssets();
        loadOtherAssets();
        loadSettingsMenuAssets();

        game.getAssetManager().finishLoading();
    }

    /**
     * Loads the main menu assets.
     */
    private void loadMainMenuAssets(){
        game.getAssetManager().load(musicName, Music.class);
        game.getAssetManager().load("Cumulus.png",Texture.class);
        game.getAssetManager().load("buttonSettings.png",Texture.class);
        game.getAssetManager().load("buttonCredits.png",Texture.class);
        game.getAssetManager().load("buttonHOF.png",Texture.class);
        game.getAssetManager().load("buttonPlay.png",Texture.class);
    }

    /**
     * Loads the settings menu assets.
     */
    private void loadSettingsMenuAssets(){
        game.getAssetManager().load("textMusicOn.png",Texture.class);
        game.getAssetManager().load("textSoundOn.png",Texture.class);
        game.getAssetManager().load("text_sensitivity.png",Texture.class);
        game.getAssetManager().load("textMusicOff.png",Texture.class);
        game.getAssetManager().load("textSoundOff.png",Texture.class);
        game.getAssetManager().load("Settings.png",Texture.class);
        game.getAssetManager().load("text_back.png",Texture.class);
    }

    /**
     * Loads the pause menu assets.
     */
    private void loadPauseMenuAssets(){
        game.getAssetManager().load("iconBack.png",Texture.class);
        game.getAssetManager().load("iconPlay.png",Texture.class);

    }

    /**
     * Loads other assets.
     */
    private void loadOtherAssets(){
        game.getAssetManager().load("iconPause.png",Texture.class);
        game.getAssetManager().load("textGameOver.png",Texture.class);
        game.getAssetManager().load("textScore.png", Texture.class);
        game.getAssetManager().load("textHallOfFame.png", Texture.class);
        game.getAssetManager().load("textCredits.png", Texture.class);

    }

    /**
     * Shows the menu and the buttons.
     */
    @Override
    public void show() {
        createTable();
        createButtons();
        showTitle();
        fillTable();
    }

    /**
     * Creates the table where the buttons are appearing.
     */
    private void createTable() {
        table = new Table();
        table.center();
        table.padLeft(20);
        table.padRight(20);
        table.setFillParent(true);
    }

    /**
     * Creates the buttons.
     */
    private void createButtons(){
        Drawable buttonSP = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("buttonPlay.png")));
        singlePlayer = new ImageButton(buttonSP);
        singlePlayer.setSize(BUTTON_SIZE_X,BUTTON_SIZE_Y);

        Drawable buttonSettings = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("buttonSettings.png")));
        settings = new ImageButton(buttonSettings);
        settings.setSize(BUTTON_SIZE_X,BUTTON_SIZE_Y);

        Drawable buttonHOF = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("buttonHOF.png")));
        highscores = new ImageButton(buttonHOF);
        highscores.setSize(BUTTON_SIZE_X,BUTTON_SIZE_Y);

        Drawable buttonCredits = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("buttonCredits.png")));
        credits = new ImageButton(buttonCredits);
        credits.setSize(BUTTON_SIZE_X,BUTTON_SIZE_Y);
    }

    /**
     * Shows the title.
     */
    private void showTitle(){
        Drawable buttonDrawableCumulus = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("Cumulus.png")));
        title = new ImageButton(buttonDrawableCumulus);
        title.setSize(MAIN_MENU_WIDTH*1.5f,MAIN_MENU_HEIGHT/2);
        title.setPosition(MAIN_MENU_WIDTH/2-title.getWidth()/2,MAIN_MENU_HEIGHT/2f);
        stage.addActor(title);
    }

    /**
     * Fills the table with the buttons.
     */
    private void fillTable(){
        table.padTop(DELTA_Y_MENU);
        table.row();
        table.padLeft(DELTA_X_MENU);
        table.add(singlePlayer).expandX().pad(DELTA_Y_MENU).maxWidth(BUTTON_SIZE_X);
        table.add(settings).expandX().pad(DELTA_Y_MENU).maxWidth(BUTTON_SIZE_X);
        table.add(highscores).expandX().pad(DELTA_Y_MENU).maxWidth(BUTTON_SIZE_X);
        table.add(credits).expandX().pad(DELTA_Y_MENU).maxWidth(BUTTON_SIZE_X);
        table.padRight(DELTA_X_MENU);
        table.row();
        stage.addActor(table);
    }

    /**
     * Renders the screen, waiting for input.
     * @param delta time between frames
     */
    @Override
	public void render (float delta) {
        if(game.isMusicOn())
            music.play();
        else {
            music.pause();
        }
		Gdx.gl.glClearColor(0.4f, 0.737f, 0.929f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        checkForSPMP();
        checkForSettingsHiScores();
        checkForCreditsBack();

        stage.act();
        stage.draw();
	}

    /**
     * Checks for input in the play button.
     */
	private void checkForSPMP(){
        if(singlePlayer.isPressed()){
            dispose();
            game.setScreen(new SinglePGameView(game));
        }
    }

    /**
     * Checks for input in the settings or highscores buttons.
     */
    private void checkForSettingsHiScores(){
        if(settings.isPressed()) {
            dispose();
            game.setScreen(new SettingsView(game));
        }
        if(highscores.isPressed()){
            dispose();
            game.setScreen(new HighScoreView(game));
        }
    }

    /**
     * Checks for the credits or back buttons.
     */
    private void checkForCreditsBack(){
        if(credits.isPressed()){
            dispose();
            game.setScreen(new CreditsView(game));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            for(int i = 1; i <= game.getNum_scores();i++){
                game.getPreferences().setHighscore(game.getScores().get(i-1),i);
            }
            dispose();
            //Gdx.app.exit();
            System.exit(0);
        }
    }

    /**
     * Resizes the window.
     * @param width the new width
     * @param height the new height
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
       // skinButtons.dispose();
    }

}
