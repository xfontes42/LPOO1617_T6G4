package com.dxenterprise.cumulus.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
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
import com.dxenterprise.cumulus.view.entities.EntityView;
import com.dxenterprise.cumulus.view.entities.ViewFactory;

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
    private static final float VIEWPORT_WIDTH = 20;

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
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public SinglePGameView(MyCumulusGame game) {
        this.game = game;
        loadAssets();
        camera = createCamera();
    }

    /**
     * Creates the camera used to show the viewport.
     *
     * @return the camera
     */
    private OrthographicCamera createCamera() {
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
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
        //put clouds here

        //this.game.getAssetManager().finishLoading();
    }

    /**
     * Renders this screen.
     *
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta) {
        SinglePGameController.getInstance().removeFlagged();
        SinglePGameController.getInstance().createNewClouds();
        //maybe create new powerups and downs

        handleInputs(delta);

        SinglePGameController.getInstance().update(delta);

        camera.position.set(SinglePGameModel.getInstance().getPlayer().getX() / PIXEL_TO_METER, SinglePGameModel.getInstance().getPlayer().getY() / PIXEL_TO_METER, 0);
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
    }

    /**
     * Handles any inputs and passes them to the controller.
     *
     * @param delta time since last time inputs where handled in seconds
     */
    private void handleInputs(float delta) {

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
       // List<AsteroidModel> asteroids = GameModel.getInstance().getAsteroids();
//        for (AsteroidModel asteroid : asteroids) {
//            EntityView view = ViewFactory.makeView(game, asteroid);
//            view.update(asteroid);
//            view.draw(game.getBatch());
//        }
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
//        Texture background = game.getAssetManager().get("background.png", Texture.class);
//        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
//        game.getBatch().draw(background, 0, 0, 0, 0, (int)(ARENA_WIDTH / PIXEL_TO_METER), (int) (ARENA_HEIGHT / PIXEL_TO_METER));
    }
}
