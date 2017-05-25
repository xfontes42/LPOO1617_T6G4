package com.dxenterprise.cumulus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dxenterprise.cumulus.MyCumulusGame;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class SettingsView extends ScreenAdapter {

    /**
     * The game this screen belongs to.
     */
    private final MyCumulusGame game;

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public SettingsView(MyCumulusGame game) {
        this.game = game;
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
    }

    /**
     * Menu dimensions.
     */
    private final int MENU_WIDTH = 1080;
    private final int MENU_HEIGHT = 720;
    private final int DELTA_Y_MENU = 100;
    private Stage stage;
    private ImageButton soundToggle;
    private Viewport viewport;
    private OrthographicCamera camera;
    private Skin skinButtons;
    private ImageButton SoundTextOn, SoundTextOff,  MusicTextOn, MusicTextOff, BackButton;
    public static Slider SensitivitySlider;

    @Override
    public void show() {
        //Create buttons

        //title
        Drawable buttonDrawableSettings = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("Settings.png")));
        ImageButton SettingsOver = new ImageButton(buttonDrawableSettings);
        SettingsOver.setSize(MENU_WIDTH*1.5f,MENU_HEIGHT/3);
        SettingsOver.setPosition(MENU_WIDTH/2-SettingsOver.getWidth()/2, MENU_HEIGHT/1.9f +SettingsOver.getHeight()/2);
        stage.addActor(SettingsOver);

        //sound label
        Drawable buttonDrawableSound;
        buttonDrawableSound = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("textSoundOn.png")));
        SoundTextOn = new ImageButton(buttonDrawableSound);
        SoundTextOn.setSize(MENU_WIDTH/5,MENU_HEIGHT/6);
        SoundTextOn.setPosition(MENU_WIDTH/2-SoundTextOn.getWidth()/2,MENU_HEIGHT/1.5f-DELTA_Y_MENU);
        stage.addActor(SoundTextOn);


        buttonDrawableSound = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("textSoundOff.png")));
        SoundTextOff = new ImageButton(buttonDrawableSound);
        SoundTextOff.setSize(MENU_WIDTH/5,MENU_HEIGHT/6);
        SoundTextOff.setPosition(MENU_WIDTH/2-SoundTextOff.getWidth()/2,MENU_HEIGHT/1.5f-DELTA_Y_MENU);
        stage.addActor(SoundTextOff);


        //music label
        Drawable buttonDrawableMusic;
        buttonDrawableMusic = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("textMusicOn.png")));
        MusicTextOn = new ImageButton(buttonDrawableMusic);
        MusicTextOn.setSize(MENU_WIDTH/5,MENU_HEIGHT/6);
        MusicTextOn.setPosition(MENU_WIDTH/2-MusicTextOn.getWidth()/2,MENU_HEIGHT/2-DELTA_Y_MENU);
        stage.addActor(MusicTextOn);

        buttonDrawableMusic = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("textMusicOff.png")));
        MusicTextOff = new ImageButton(buttonDrawableMusic);
        MusicTextOff.setSize(MENU_WIDTH/5,MENU_HEIGHT/6);
        MusicTextOff.setPosition(MENU_WIDTH/2-MusicTextOff.getWidth()/2,MENU_HEIGHT/2-DELTA_Y_MENU);
        stage.addActor(MusicTextOff);

        if(game.isMusicOn())
            MusicTextOff.setVisible(false);
        else MusicTextOn.setVisible(false);

        if(game.isSoundOn())
            SoundTextOff.setVisible(false);
        else SoundTextOn.setVisible(false);

//       Drawable buttonDrawableSens = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("text_sensitivity.png")));
//        MusicText = new ImageButton(buttonDrawableSens);
//        MusicText.setSize(MENU_WIDTH/5,MENU_HEIGHT/6);
//        MusicText.setPosition(MENU_WIDTH/2-SoundText.getWidth()/2,MENU_HEIGHT/3-DELTA_Y_MENU);
//        stage.addActor(MusicText);

        SensitivitySlider = new Slider(1,5,0.5f,false,skinButtons);
        SensitivitySlider.setSize(MusicTextOn.getWidth(), MusicTextOn.getHeight());
        SensitivitySlider.setPosition(MENU_WIDTH/2-SensitivitySlider.getWidth()/2,MENU_HEIGHT/3f-DELTA_Y_MENU);
        SensitivitySlider.setValue(SinglePGameView.sensitivity);
        //SensitivitySlider.setDebug(true);
        stage.addActor(SensitivitySlider);


    }



    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0.4f, 0.737f, 0.929f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            dispose();
            game.setScreen(new MainMenuView(game));
        }


        if(MusicTextOn.isPressed() && game.isMusicOn()){
            Gdx.input.vibrate(50);
            System.out.println("turned music off");
            game.toggleMusic();

        }
        if(MusicTextOff.isPressed() && !game.isMusicOn()){
            Gdx.input.vibrate(50);
            System.out.println("turned music on");
            game.toggleMusic();

        }
        if(SoundTextOn.isPressed() && game.isSoundOn()){
            Gdx.input.vibrate(50);
            System.out.println("turned sound off");
            game.toggleSound();
        }
        if(SoundTextOff.isPressed() && !game.isSoundOn()){
            Gdx.input.vibrate(50);
            System.out.println("turned sound on");
            game.toggleSound();
        }

        if(game.isSoundOn()){
            SoundTextOff.setVisible(false);
            SoundTextOn.setVisible(true);
        }
        else {
            SoundTextOn.setVisible(false);
            SoundTextOff.setVisible(true);
        }

        if(game.isMusicOn()){
            MusicTextOff.setVisible(false);
            MusicTextOn.setVisible(true);
        }
        else {
            MusicTextOn.setVisible(false);
            MusicTextOff.setVisible(true);
        }






//        if (settingsOver.isPressed()) {
//            dispose();
//            game.setScreen(new SettingsView(game));
//        }
//        if (highscoresOver.isPressed())
//            System.out.println("Highscores");
//        if (shareOver.isPressed())
//            S ystem.out.println("Share");
//        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
//            dispose();
//            Gdx.app.exit();
//        }
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
