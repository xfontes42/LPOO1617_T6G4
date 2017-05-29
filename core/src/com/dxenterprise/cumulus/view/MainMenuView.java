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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
    private final int DELTA_Y_MENU = 100;

    /**
     * The game this menu belongs to.
     */
    private MyCumulusGame game;

    /**
     * The stage used to display the menu.
     */
    private Stage stage;

    /**
     * The buttons used for the game modes.
     */
    private TextButton singlePlayer, multiplayer;

    /**
     * The buttons used for the auxiliary menus.
     */
    private ImageButton settings, share, highscores, settingsOver, shareOver, highscoresOver;

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
        skinButtons = new Skin(Gdx.files.internal("SkinMainMenu/glassy-ui.json"));
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
        game.getAssetManager().load("iconSettings.png",Texture.class);
        game.getAssetManager().load("iconShare.png",Texture.class);
        game.getAssetManager().load("iconController.png",Texture.class);
        game.getAssetManager().load("text_SP.png",Texture.class);
        game.getAssetManager().load("text_MP.png",Texture.class);
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
        showSettings();
        showSPMPbuttons();
        showShare();
        showHiScores();
        showTitle();
    }

    /**
     * Shows the single and multiplayer buttons.
     */
    private void showSPMPbuttons(){
        singlePlayer = new TextButton("Singleplayer", skinButtons);
        singlePlayer.setSize(MAIN_MENU_WIDTH/2.5f,MAIN_MENU_HEIGHT/6+10);
        singlePlayer.setPosition(MAIN_MENU_WIDTH/2-singlePlayer.getWidth()/2,3*MAIN_MENU_HEIGHT/5-DELTA_Y_MENU);
        stage.addActor(singlePlayer);

        multiplayer = new TextButton("Multiplayer", skinButtons);
        multiplayer.setSize(MAIN_MENU_WIDTH/2.5f,MAIN_MENU_HEIGHT/6);
        multiplayer.setPosition(MAIN_MENU_WIDTH/2-multiplayer.getWidth()/2,2*MAIN_MENU_HEIGHT/5-DELTA_Y_MENU);
        stage.addActor(multiplayer);
    }

    /**
     * Shows the settings button.
     */
    private void showSettings(){
        settings = new ImageButton(skinButtons);
        settings.setSize(MAIN_MENU_WIDTH/9,MAIN_MENU_HEIGHT/6);
        settings.setPosition(MAIN_MENU_WIDTH/2-settings.getWidth()/2,MAIN_MENU_HEIGHT/5-DELTA_Y_MENU);
        stage.addActor(settings);

        Drawable buttonDrawableSet = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("iconSettings.png")));
        settingsOver = new ImageButton(buttonDrawableSet);
        settingsOver.setSize(MAIN_MENU_WIDTH/9,MAIN_MENU_HEIGHT/6);
        settingsOver.setPosition(MAIN_MENU_WIDTH/2-settings.getWidth()/2,MAIN_MENU_HEIGHT/5-DELTA_Y_MENU);
        stage.addActor(settingsOver);
    }

    /**
     * Shows the share button.
     */
    private void showShare(){
        share = new ImageButton(skinButtons);
        share.setSize(MAIN_MENU_WIDTH/9,MAIN_MENU_HEIGHT/6);
        share.setPosition(MAIN_MENU_WIDTH/2-share.getWidth()/2+MAIN_MENU_WIDTH/7.5f,MAIN_MENU_HEIGHT/5-DELTA_Y_MENU);
        stage.addActor(share);

        Drawable buttonDrawableSha = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("iconShare.png")));
        shareOver = new ImageButton(buttonDrawableSha);
        shareOver.setSize(MAIN_MENU_WIDTH/9,MAIN_MENU_HEIGHT/6);
        shareOver.setPosition(MAIN_MENU_WIDTH/2-share.getWidth()/2+MAIN_MENU_WIDTH/7.5f,MAIN_MENU_HEIGHT/5-DELTA_Y_MENU);
        stage.addActor(shareOver);
    }

    /**
     * Shows the title.
     */
    private void showTitle(){
        Drawable buttonDrawableCumulus = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("Cumulus.png")));
        ImageButton cumulusButtonOver = new ImageButton(buttonDrawableCumulus);
        cumulusButtonOver.setSize(MAIN_MENU_WIDTH*1.5f,MAIN_MENU_HEIGHT/2);
        cumulusButtonOver.setPosition(MAIN_MENU_WIDTH/2-cumulusButtonOver.getWidth()/2,MAIN_MENU_HEIGHT/1.75f);
        stage.addActor(cumulusButtonOver);
    }

    /**
     * Shows the highscores button.
     */
    private void showHiScores(){
        highscores = new ImageButton(skinButtons);
        highscores.setSize(MAIN_MENU_WIDTH/9,MAIN_MENU_HEIGHT/6);
        highscores.setPosition(MAIN_MENU_WIDTH/2-highscores.getWidth()/2-MAIN_MENU_WIDTH/7.5f,MAIN_MENU_HEIGHT/5-DELTA_Y_MENU);
        stage.addActor(highscores);

        Drawable  buttonDrawableHigh= new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("iconController.png")));
        highscoresOver = new ImageButton(buttonDrawableHigh);
        highscoresOver.setSize(MAIN_MENU_WIDTH/9,MAIN_MENU_HEIGHT/6);
        highscoresOver.setPosition(MAIN_MENU_WIDTH/2-highscores.getWidth()/2-MAIN_MENU_WIDTH/7.5f,MAIN_MENU_HEIGHT/5-DELTA_Y_MENU);
        stage.addActor(highscoresOver);
    }

    /**
     * Renders the screen, waiting for input.
     * @param delta
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
        checkForShareBack();

        stage.act();
        stage.draw();
	}

    /**
     * Checks for input in the single/multiplayer buttons.
     */
	private void checkForSPMP(){
        if(singlePlayer.isPressed()){
            dispose();
            game.setScreen(new SinglePGameView(game));
        }
        if(multiplayer.isPressed()){
            dispose();
            game.setScreen(new GameOverView(game,0));
        }
    }

    /**
     * Checks for input in the settings or highscores buttons.
     */
    private void checkForSettingsHiScores(){
        if(settingsOver.isPressed()) {
            dispose();
            game.setScreen(new SettingsView(game));
        }
        if(highscoresOver.isPressed()){
            dispose();
            game.setScreen(new HighScoreView(game));
        }
    }

    /**
     * Checks for the share or back buttons.
     */
    private void checkForShareBack(){
        if(shareOver.isPressed()){
            dispose();
            game.setScreen(new CreditsView(game));
//            System.out.println("Share");
//            if (!Gdx.net.openURI("fb://page/<page_id>")) { // opens app
//                Gdx.net.openURI("https://facebook.com/<page_name>"); // opens site if app not installed
//            } //check this out later
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            for(int i = 1; i <= game.getNum_scores();i++){
                game.getPreferences().setHighscore(game.getScores().get(i-1),i);
            }
            dispose();
            Gdx.app.exit();
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
        skinButtons.dispose();
    }

}
