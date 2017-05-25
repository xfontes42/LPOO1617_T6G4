package com.dxenterprise.cumulus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dxenterprise.cumulus.MyCumulusGame;

/**
 * Created by Xavier Fontes on 28/04/2017.
 */

public class MainMenuView extends ScreenAdapter {
    private final int MAIN_MENU_WIDTH = 1080;
    private final int MAIN_MENU_HEIGHT = 720;
    private final int DELTA_Y_MENU = 100;
    private MyCumulusGame game;
    private Stage stage;
    private  TextButton singlePlayer, multiplayer;
    private ImageButton settings, share, highscores, settingsOver, shareOver, highscoresOver;
    private Viewport viewport;
    private OrthographicCamera camera;
    private Skin skinButtons;

    public MainMenuView(MyCumulusGame game){
        this.game = game;
        loadAssets();
        camera = new OrthographicCamera();
        viewport = new FitViewport(MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        stage = new Stage(viewport, game.getBatch());
        skinButtons = new Skin(Gdx.files.internal("SkinMainMenu/glassy-ui.json"));
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    private void loadAssets() {
        ///game.getAssetManager().load("iconCheck.png",Texture.class); //todo ver a necessidade isto
        loadMainMenuAssets();
        loadPauseMenuAssets();
        loadHUDAssets();
        loadSettingsMenuAssets();

        game.getAssetManager().finishLoading();
    }

    private void loadMainMenuAssets(){
        game.getAssetManager().load("Cumulus.png",Texture.class);
        game.getAssetManager().load("iconSettings.png",Texture.class);
        game.getAssetManager().load("iconShare.png",Texture.class);
        game.getAssetManager().load("iconController.png",Texture.class);
        game.getAssetManager().load("text_SP.png",Texture.class);
        game.getAssetManager().load("text_MP.png",Texture.class);
    }

    private void loadSettingsMenuAssets(){
        game.getAssetManager().load("textMusicOn.png",Texture.class);
        game.getAssetManager().load("textSoundOn.png",Texture.class);
        game.getAssetManager().load("text_sensitivity.png",Texture.class);
        game.getAssetManager().load("textMusicOff.png",Texture.class);
        game.getAssetManager().load("textSoundOff.png",Texture.class);
        game.getAssetManager().load("Settings.png",Texture.class);
        game.getAssetManager().load("text_back.png",Texture.class);
    }

    private void loadPauseMenuAssets(){
        game.getAssetManager().load("iconBack.png",Texture.class);
        game.getAssetManager().load("iconPlay.png",Texture.class);

    }

    private void loadHUDAssets(){
        game.getAssetManager().load("iconPause.png",Texture.class);
        game.getAssetManager().load("textGameOver.png",Texture.class);
        game.getAssetManager().load("textScore.png", Texture.class);

    }

    @Override
    public void show() {
        showSettings();
        showSPMPbuttons();
        showShare();
        showHiScores();
        showTitle();
    }

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

    private void showTitle(){
        Drawable buttonDrawableCumulus = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("Cumulus.png")));
        ImageButton cumulusButtonOver = new ImageButton(buttonDrawableCumulus);
        cumulusButtonOver.setSize(MAIN_MENU_WIDTH*1.5f,MAIN_MENU_HEIGHT/2);
        cumulusButtonOver.setPosition(MAIN_MENU_WIDTH/2-cumulusButtonOver.getWidth()/2,MAIN_MENU_HEIGHT/1.75f);
        stage.addActor(cumulusButtonOver);
    }

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

    @Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0.4f, 0.737f, 0.929f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(singlePlayer.isPressed()){
            dispose();
            game.setScreen(new SinglePGameView(game));
        }
        if(multiplayer.isPressed()){
            dispose();
            game.setScreen(new MultiPGameView(game));
        }
        if(settingsOver.isPressed()) {
            dispose();
            game.setScreen(new SettingsView(game));
        }
        if(highscoresOver.isPressed()){
            System.out.println("Highscores");
            dispose();
            game.setScreen(new GameOverView(game));
        }
        if(shareOver.isPressed())
            System.out.println("Share");
        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            dispose();
            Gdx.app.exit();
        }
        stage.act();
        stage.draw();
	}


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skinButtons.dispose();
    }

}
