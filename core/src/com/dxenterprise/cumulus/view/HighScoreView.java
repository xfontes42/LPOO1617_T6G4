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

/**
 * Created by Daniel Pinho on 26/05/2017.
 */

public class HighScoreView extends ScreenAdapter {

    /**
     * The game this screen belongs to.
     */
    private final MyCumulusGame game;

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */

    public HighScoreView(MyCumulusGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(MENU_WIDTH, MENU_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        stage = new Stage(viewport, game.getBatch());
        skinButtons = new Skin(Gdx.files.internal("SkinMainMenu/glassy-ui.json"));
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        loadFonts();
        createTable();
        fillTable();
        stage.addActor(table);
        showBack();
    }


    /**
     * Menu dimensions.
     */
    private final int MENU_WIDTH = 1080;
    private final int MENU_HEIGHT = 720;
    private final int DELTA_Y_MENU = 100;
    private Stage stage;
    private ImageButton BackButton;
    private Viewport viewport;
    private OrthographicCamera camera;
    private Skin skinButtons;
    private Table table;
    private BitmapFont Rancho90;
    private BitmapFont Clouds90;
    private Label scoreRank, scoreValue;
    //private ImageButton GameOverLabel;

    /**
     * Loads the Rancho font used in this screen.
     */
    private void loadFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Rancho-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        parameter.borderWidth = 0.5F;
        parameter.borderColor = Color.WHITE;
        Rancho90 = generator.generateFont(parameter); // font size 12 pixels

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/AlphaClouds.ttf"));
        Clouds90 = generator.generateFont(parameter); // font size 12 pixels

        generator.dispose();
    }

    /**
     * Creates the table that will align the text in this screen.
     */
    private void createTable() {
        table = new Table();
        table.bottom();
        table.setFillParent(true);
    }

    private void fillTable() {

        table.row();
        table.add();//.pad()
        table.row();
        for (int i = 1; i <= game.getNum_scores(); i++) {
            scoreRank = new Label(String.format("%01d: ", i), new Label.LabelStyle(Clouds90, Color.WHITE));
            scoreValue = new Label(String.format("%06d", game.getScores().get(i-1)), new Label.LabelStyle(Rancho90, Color.WHITE));
            table.add(scoreRank).expandX().right();//.padTop(PADDING);
            table.add(scoreValue).expandX().left();//padTop(PADDING);
            table.row();
        }

//        table.add().expandX().padTop(PADDING);
//        table.add().expandX().padTop(PADDING);
//        table.add(scoreNameLabel).expandX().padTop(PADDING);
//        table.add(scoreLabel).expandX().padTop(PADDING);
    }

    private void showBack() {
        Drawable buttonDrawableBack = new TextureRegionDrawable(new TextureRegion((Texture) game.getAssetManager().get("text_back.png")));
        BackButton = new ImageButton(buttonDrawableBack);
        BackButton.setSize(MENU_WIDTH / 8, MENU_HEIGHT / 8);
        BackButton.setPosition(7 * MENU_WIDTH / 8 - BackButton.getWidth() / 2, MENU_HEIGHT / 8 - DELTA_Y_MENU);
        stage.addActor(BackButton);
    }

    @Override
    public void show() {
        Drawable buttonDrawableHoF = new TextureRegionDrawable(new TextureRegion((Texture) game.getAssetManager().get("textHallOfFame.png")));
        ImageButton HallOfFameLabel = new ImageButton(buttonDrawableHoF);
        HallOfFameLabel.setSize(MENU_WIDTH / 1.5f, MENU_HEIGHT / 3);
        HallOfFameLabel.setPosition(MENU_WIDTH / 2 - HallOfFameLabel.getWidth() / 2, MENU_HEIGHT / 1.9f + HallOfFameLabel.getHeight() / 2);
        stage.addActor(HallOfFameLabel);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.737f, 0.929f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        checkForBack();


        stage.act();
        stage.draw();
    }

    private void checkForBack() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) ||
                BackButton.isPressed()) {
            Gdx.input.vibrate(50);
            dispose();
            game.setScreen(new MainMenuView(game)); //todo acomodar para quando se vai para as settings durante o jogo
        }
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
