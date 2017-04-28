package com.dxenterprise.cumulus.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dxenterprise.cumulus.MyCumulusGame;

/**
 * Created by Xavier Fontes on 28/04/2017.
 */

public class MainMenuView extends ScreenAdapter {

    private final int MAIN_MENU_WIDTH = 1080;
    private final int MAIN_MENU_HEIGHT = 720;

    private MyCumulusGame game;
    private Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private Skin skinButtons;

    public MainMenuView(MyCumulusGame game){
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        stage = new Stage(viewport, game.getBatch());


        skinButtons = new Skin(Gdx.files.internal("SkinMainMenu/glassy-ui.json"));


        Gdx.input.setInputProcessor(stage);
    }

    private void loadAssets() {

    }

    @Override
    public void show() {
        //Create buttons
        TextButton singlePlayer = new TextButton("Singleplayer", skinButtons);
        singlePlayer.setSize(MAIN_MENU_WIDTH/3,MAIN_MENU_HEIGHT/6);
        singlePlayer.setPosition(MAIN_MENU_WIDTH/2-singlePlayer.getWidth()/2,3*MAIN_MENU_HEIGHT/5);

        TextButton multiplayer = new TextButton("Multiplayer", skinButtons);
        multiplayer.setSize(MAIN_MENU_WIDTH/3,MAIN_MENU_HEIGHT/6);
        multiplayer.setPosition(MAIN_MENU_WIDTH/2-multiplayer.getWidth()/2,2*MAIN_MENU_HEIGHT/5);

        TextButton settings = new TextButton("Settings", skinButtons);
        settings.setSize(MAIN_MENU_WIDTH/3,MAIN_MENU_HEIGHT/6);
        settings.setPosition(MAIN_MENU_WIDTH/2-settings.getWidth()/2,MAIN_MENU_HEIGHT/5);



        //Add listeners to buttons
        singlePlayer.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //((Game)Gdx.app.getApplicationListener()).setScreen(new PlayScreen());
            }
        });
        multiplayer.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        settings.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });


        //Add table to stage
        stage.addActor(singlePlayer);
        stage.addActor(multiplayer);
        stage.addActor(settings);
    }


    @Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0.4f, 0.737f, 0.929f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
