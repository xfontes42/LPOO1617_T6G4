package com.dxenterprise.cumulus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

/**
 * Created by danie on 29/05/2017.
 */

public class CreditsView extends ScreenAdapter {

    /**
     * The game this screen belongs to.
     */
    private final MyCumulusGame game;

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public CreditsView(MyCumulusGame game) {

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
        createTable();
        fillTable();
        stage.addActor(table);
        showBack();
    }

    /**
     * Padding between table rows.
     */
    private final int PADDING = 20;

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
     * The table used to display the highscores evenly on the screen.
     */
    private Table table;

    /**
     * Table elements.
     */
    private Label line1, line2, line3, line4, line5, line6, line7, title;

    /**
     * Creates the table that will align the text in this screen.
     */
    private void createTable() {
        table = new Table();
        table.center();
        table.setFillParent(true);
        createLabels();
    }

    /**
     * Creates the labels with the credits text.
     */
    private void createLabels(){
        title = new Label("Credits", new Label.LabelStyle(fonts.getInstance().getClouds(), Color.WHITE));
        title.scaleBy(0.8f);
        line1 = new Label("Game design and programming:", new Label.LabelStyle(fonts.getInstance().getRancho(), Color.WHITE));
        line2 = new Label("Daniel Pinho, Xavier Fontes", new Label.LabelStyle(fonts.getInstance().getRancho40(), Color.WHITE));
        line3 = new Label("Faculdade de Engenharia da Universidade do Porto", new Label.LabelStyle(fonts.getInstance().getRancho40(), Color.WHITE));
        line4 = new Label("Special thanks: Nuno Flores, André Restivo, Jorge Barbosa", new Label.LabelStyle(fonts.getInstance().getRancho40(), Color.WHITE));
        line5 = new Label("Music:", new Label.LabelStyle(fonts.getInstance().getRancho(), Color.WHITE));
        line6 = new Label("Bit Quest Kevin MacLeod (incompetech.com)", new Label.LabelStyle(fonts.getInstance().getRancho40(), Color.WHITE));
        line7 = new Label("Licensed under Creative Commons: By Attribution 3.0 License", new Label.LabelStyle(fonts.getInstance().getRancho40(), Color.WHITE));

    }

    /**
     * Fills the table with the credits.
     */
    private void fillTable() {
        table.row();
        table.add(title);
        table.row();

        table.add(line1).expandX(); table.row();
        table.add(line2).expandX(); table.row();
        table.add(line3).expandX(); table.row();
        table.add(line4).expandX(); table.row();
        table.add();                table.row();
        table.add(line5).expandX(); table.row();
        table.add(line6).expandX(); table.row();
        table.add(line7).expandX(); table.row();

    }

    /**
     * Displays the back button.
     */
    private void showBack() {
        Drawable buttonDrawableBack = new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("buttonBack.png")));
        BackButton = new ImageButton(buttonDrawableBack);
        BackButton.setSize(MENU_WIDTH/7,MENU_HEIGHT/7);
        BackButton.setPosition(9*MENU_WIDTH/10 - BackButton.getWidth()/2, MENU_HEIGHT/7 -DELTA_Y_MENU);
        stage.addActor(BackButton);
    }

    /**
     * Shows the screen and the menu title.
     */
    @Override
    public void show() {
    }

    /**
     * Renders the screen and waits for any input to return to the previous screen.
     * @param delta time interval between frames
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.737f, 0.929f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        checkForBack();

        stage.act();
        stage.draw();
    }

    /**
     * Waits for any input that is used to return back (ESC [PC]/Back [Android] and Back label)
     */
    private void checkForBack() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) ||
                BackButton.isPressed()) {
            Gdx.input.vibrate(50);
            dispose();
            game.setScreen(new MainMenuView(game));
        }
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
