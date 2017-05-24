package com.dxenterprise.cumulus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
        //skinButtons = new Skin(Gdx.files.internal("SkinMainMenu/glassy-ui.json"));
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
    private ImageButton SoundText,  MusicText, Sensitivity, BackButton;

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
        if(game.isSoundOn())
            buttonDrawableSound = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("textSoundOn.png")));
        else
            buttonDrawableSound = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("textSoundOff.png")));
        SoundText = new ImageButton(buttonDrawableSound);
        SoundText.setSize(MENU_WIDTH/5,MENU_HEIGHT/6);
        SoundText.setPosition(MENU_WIDTH/2-SoundText.getWidth()/2,MENU_HEIGHT/1.5f-DELTA_Y_MENU);
        stage.addActor(SoundText);

        //music label
        Drawable buttonDrawableMusic;
        if(game.isMusicOn())
            buttonDrawableMusic = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("textMusicOn.png")));
        else
            buttonDrawableMusic = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("textMusicOff.png")));
        MusicText = new ImageButton(buttonDrawableMusic);
        MusicText.setSize(MENU_WIDTH/5,MENU_HEIGHT/6);
        MusicText.setPosition(MENU_WIDTH/2-SoundText.getWidth()/2,MENU_HEIGHT/2-DELTA_Y_MENU);
        stage.addActor(MusicText);

       Drawable buttonDrawableSens = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("text_sensitivity.png")));
        MusicText = new ImageButton(buttonDrawableSens);
        MusicText.setSize(MENU_WIDTH/5,MENU_HEIGHT/6);
        MusicText.setPosition(MENU_WIDTH/2-SoundText.getWidth()/2,MENU_HEIGHT/3-DELTA_Y_MENU);
        stage.addActor(MusicText);


    }


    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0.4f, 0.737f, 0.929f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
