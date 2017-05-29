package com.dxenterprise.cumulus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dxenterprise.cumulus.MyCumulusGame;
import com.dxenterprise.cumulus.controller.SinglePGameController;
import com.dxenterprise.cumulus.model.SinglePGameModel;
import com.dxenterprise.cumulus.model.entities.BirdModel;
import com.dxenterprise.cumulus.model.entities.CloudModel;
import com.dxenterprise.cumulus.model.entities.EntityModel;
import com.dxenterprise.cumulus.view.entities.EntityView;
import com.dxenterprise.cumulus.view.entities.ViewFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class SinglePGameView extends ScreenAdapter {

    /**
     * The game HUD.
     */
    private HudView hud;

    /**
     * Used to debug the position of the physics fixtures
     */
    private static final boolean DEBUG_PHYSICS = true;

    /**
     * How much meters does a pixel represent.
     */
    public final static float PIXEL_TO_METER = 1/450f;

    /**
     * The width of the viewport in meters. The height is
     * automatically calculated using the screen ratio.
     */
    public static final float VIEWPORT_WIDTH = 18;

    /**
     * The game this screen belongs to.
     */
    private final MyCumulusGame game;

    /**
     * The camera used to show the viewport.
     */
    private final OrthographicCamera camera;

    /**
     * A renderer used to debug the physical fixtures.
     */
    private Box2DDebugRenderer debugRenderer;

    /**
     * The transformation matrix used to transform meters into
     * pixels in order to show fixtures in their correct places.
     */
    private Matrix4 debugCamera;

    /**
     * Size of readings for the accelerometer low pass filter.
     */
    private final int size_readings = 5;

     /**
     * The accelerometer's data for the X-axis.
     */
    private ArrayList<Float> X_values = new ArrayList<Float>(size_readings);

    /**
     * The accelerometer's data for the Y-axis.
     */
    private ArrayList<Float> Y_values = new ArrayList<Float>(size_readings);

    /**
     * The accelerometer's readings for the X-axis.
     */
    private Float readingY = new Float(0);

    /**
     * The accelerometer's readings for the Y-axis.
     */
    private Float readingX = new Float(0);

    /**
     * The texel coordinates for the X axis.
     */
    private float texCoordX = 0;

    /**
     * The texel coordinates for the Y axis.
     */
    private float texCoordY = 0;

    /**
     * The percentage of the texture appearing (x axis)
     */
    private float textDeltaX = 0.5f;
    /**
     * The percentage of the texture appearing (y axis)
     */
    private float texDeltaY = 0.5f;

    /**
     * The accelerometer sensitivity.
     */
    public static float sensitivity = 2.5f;

    /**
     * A sound for the bird flapping its wings.
     */
    public static Sound flap =  Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public SinglePGameView(MyCumulusGame game) {
        this.game = game;
        hud = new HudView(this.game.getBatch());
        loadAssets();
        camera = createCamera();

        //init for low pass filter
        for(int i = 0; i < size_readings; i++){
            X_values.add(new Float(0));
            Y_values.add(new Float(0));
        }

    }

    /**
     * Creates the camera used to show the viewport.
     *
     * @return the camera
     */
    private OrthographicCamera createCamera() {
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);

        SinglePGameController.getInstance().setCamX(camera.viewportWidth / 2f);
        SinglePGameController.getInstance().setCamY(camera.viewportWidth / 2f);
        camera.update();

        if (DEBUG_PHYSICS) {
            debugRenderer = new Box2DDebugRenderer();
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
        }

        return camera;
    }

    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        game.getAssetManager().load("bird.png",Texture.class);
        game.getAssetManager().load("cloudSmall.png",Texture.class);
        game.getAssetManager().load("cloudMedium.png",Texture.class);
        game.getAssetManager().load("cloudBig.png",Texture.class);
        game.getAssetManager().load("sky_background.jpg",Texture.class);
        game.getAssetManager().finishLoading();
    }

    /**
     * Renders this screen.
     *
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta) {
        //TODO: separar esta função em funções mais pequenas se possível

        handleInputs(delta);

        SinglePGameController.getInstance().update(delta);
        hud.update(delta);
        hud.setScore(SinglePGameController.getInstance().getScore());

        SinglePGameController.getInstance().setCamX(
                Math.max(SinglePGameModel.getInstance().getPlayer().getX()/PIXEL_TO_METER/*-(SinglePGameController.WORLD_WIDTH/4f)/PIXEL_TO_METER-1/PIXEL_TO_METER*/,SinglePGameController.getInstance().getCamX()+ (delta*SinglePGameController.getInstance().getCamVX() / PIXEL_TO_METER))
                                                                                            //todo ^ this right here serves to adjust the bird when he's to fast
        );

        SinglePGameController.getInstance().setCamY((SinglePGameModel.getInstance().getPlayer().getY()+SinglePGameController.WORLD_HEIGHT/3f) / PIXEL_TO_METER);
        camera.position.set( SinglePGameController.getInstance().getCamX(),
                SinglePGameController.getInstance().getCamY(),
                0);
        SinglePGameController.getInstance().setCamVX(SinglePGameController.getInstance().getCamVX()+SinglePGameController.getInstance().getCamAX());
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0.4f, 0.737f, 0.929f, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        game.getBatch().begin();
        drawBackground();
        drawEntities();
        game.getBatch().end();

        hud.stage.act();
        hud.stage.draw();

        if (DEBUG_PHYSICS) {
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
            debugRenderer.render(SinglePGameController.getInstance().getWorld(), debugCamera);
        }

        if(SinglePGameController.getInstance().isGame_lost()){
            int score = SinglePGameController.getInstance().getScore();
            SinglePGameController.getInstance().clear();
            SinglePGameModel.getInstance().clear();
            game.setScreen(new GameOverView(game, score)); //todo clean the model and controller and store highscore
        }

    }

    /**
     * Handles any inputs and passes them to the controller.
     *
     * @param delta time since last time inputs where handled in seconds
     */
    private void handleInputs(float delta) {
        //TODO: separar em funçoes mais pequenas
        sensitivity = SettingsView.getSensitivity();

        if(Gdx.input.justTouched()){
            Gdx.input.vibrate(60);
            SinglePGameController.getInstance().jump(delta);
            if(game.isSoundOn())
                flap.play(0.5f);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            dispose();
            SinglePGameController.getInstance().clear();
            SinglePGameModel.getInstance().clear();
            game.setScreen(new MainMenuView(game));
        }

        X_values.remove(0);
        X_values.add(new Float(Gdx.input.getAccelerometerY()*delta));
        Y_values.remove(0);
        Y_values.add(new Float(Gdx.input.getAccelerometerX()*delta));

        readingX = sensitivity*average(X_values);
        readingY = sensitivity*average(Y_values);
    }

    /**
     * Draws the entities to the screen.
     */
    private void drawEntities() {
        List<CloudModel> clouds = SinglePGameModel.getInstance().getClouds();
        for (CloudModel cloud : clouds) {
            EntityView view = ViewFactory.makeView(game, cloud);
            view.update(cloud);
            view.draw(game.getBatch());
        }

        BirdModel bird = SinglePGameModel.getInstance().getPlayer();
        EntityView view = ViewFactory.makeView(game, bird);
        view.update(bird);
        view.draw(game.getBatch());
    }

    /**
     * Draws the background.
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("sky_background.jpg", Texture.class);
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        game.getBatch().draw(background,
                SinglePGameController.getInstance().getCamX()-(SinglePGameController.WORLD_WIDTH/4f)/PIXEL_TO_METER-1/PIXEL_TO_METER,// - (SinglePGameController.WORLD_WIDTH / 2f)) ,  //x
                SinglePGameController.getInstance().getCamY()-(SinglePGameController.WORLD_HEIGHT/2f) /PIXEL_TO_METER-1/PIXEL_TO_METER,// - (SinglePGameController.WORLD_HEIGHT / 2f)),  //y
                VIEWPORT_WIDTH / PIXEL_TO_METER,
                VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()),
                texCoordY,
                texCoordX+textDeltaX,
                texCoordY+texDeltaY,
                texCoordX);  //mexer nestes ultimos com acelerometro

                texCoordX += readingX*Gdx.graphics.getDeltaTime();
                texCoordY += readingY*Gdx.graphics.getDeltaTime();
    }

    /**
     * Calculates the average value of a provided arraylist.
     * @param arr the ArrayList whose average is being calculated
     * @return the average value of the argument
     */
    public Float average(ArrayList<Float> arr){
        Float sum = new Float(0);
        for(Float f : arr)
            sum += f;
        return sum/arr.size();
    }
}
