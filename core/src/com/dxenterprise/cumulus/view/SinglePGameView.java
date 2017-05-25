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
import com.dxenterprise.cumulus.MyCumulusGame;
import com.dxenterprise.cumulus.controller.SinglePGameController;
import com.dxenterprise.cumulus.model.SinglePGameModel;
import com.dxenterprise.cumulus.model.entities.BirdModel;
import com.dxenterprise.cumulus.model.entities.CloudModel;
import com.dxenterprise.cumulus.view.entities.EntityView;
import com.dxenterprise.cumulus.view.entities.ViewFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class SinglePGameView extends ScreenAdapter {
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
    private static final float VIEWPORT_WIDTH = 18;

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


    //for the low pass filter
    //size of readings
    private final int size_readings = 5;
    //data of accelerometer in x
    private ArrayList<Float> X_values = new ArrayList<Float>(size_readings);
    //data of accelerometer in y
    private ArrayList<Float> Y_values = new ArrayList<Float>(size_readings);
    //readings
    private Float readingY = new Float(0);
    private Float readingX = new Float(0);

    //textels coordinates
    private float texCoordX = 0;
    private float texCoordY = 0;
    private float textDeltaX = 0.5f;
    private float texDeltaY = 0.5f;
    public static float sensitivity = 2.5f;

    public static Sound flap =  Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public SinglePGameView(MyCumulusGame game) {
        this.game = game;
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

        //SinglePGameController.getInstance().removeFlagged();
        //SinglePGameController.getInstance().createNewClouds();
        //maybe create new powerups and downs

        handleInputs(delta);

        SinglePGameController.getInstance().update(delta);
       //camera.position.set(SinglePGameModel.getInstance().getPlayer().getX() / PIXEL_TO_METER, SinglePGameModel.getInstance().getPlayer().getY() / PIXEL_TO_METER, 0);
       // camera.position.set(SinglePGameModel.getInstance().getPlayer().getX() / PIXEL_TO_METER, (SinglePGameModel.getInstance().getPlayer().getY()+SinglePGameController.WORLD_HEIGHT/3f) / PIXEL_TO_METER, 0);

        SinglePGameController.getInstance().setCamX(SinglePGameController.getInstance().getCamX()+ (delta*SinglePGameController.getInstance().getCamVX() / PIXEL_TO_METER));
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



        if (DEBUG_PHYSICS) {
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
            debugRenderer.render(SinglePGameController.getInstance().getWorld(), debugCamera);
        }

        if(SinglePGameController.getInstance().isGame_lost()){
            SinglePGameController.getInstance().clear();
            SinglePGameModel.getInstance().clear();
            game.setScreen(new GameOverView(game)); //todo clean the model and controller and store highscore
        }
    }

    /**
     * Handles any inputs and passes them to the controller.
     *
     * @param delta time since last time inputs where handled in seconds
     */
    private void handleInputs(float delta) {
        sensitivity = SettingsView.getSensitivity();

        if(Gdx.input.justTouched()){
            Gdx.input.vibrate(60);
            SinglePGameController.getInstance().jump(delta);
            if(game.isSoundOn())
                flap.play(0.5f);
            System.out.println("jump");
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

/*        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            GameController.getInstance().rotateLeft(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            GameController.getInstance().rotateRight(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            GameController.getInstance().accelerate(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            GameController.getInstance().shoot();
        }*/
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
//
//        List<BulletModel> bullets = GameModel.getInstance().getBullets();
//        for (BulletModel bullet : bullets) {
//            EntityView view = ViewFactory.makeView(game, bullet);
//            view.update(bullet);
//            view.draw(game.getBatch());
//        }
//
        BirdModel bird = SinglePGameModel.getInstance().getPlayer();
        EntityView view = ViewFactory.makeView(game, bird);
        view.update(bird);
        view.draw(game.getBatch());
    }

    /**
     * Draws the background
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("sky_background.jpg", Texture.class);
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
//        System.out.println("dX: " + readingX);
//        System.out.println("dY: " + readingY);
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

//        Texture background = game.getAssetManager().get("background.png", Texture.class);
//        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
//        game.getBatch().draw(background, 0, 0, 0, 0, (int)(ARENA_WIDTH / PIXEL_TO_METER), (int) (ARENA_HEIGHT / PIXEL_TO_METER));
    }

    public Float average(ArrayList<Float> arr){
        Float sum = new Float(0);
        for(Float f : arr)
            sum += f;
        return sum/arr.size();
    }
}
