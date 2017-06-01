package com.dxenterprise.cumulus.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.dxenterprise.cumulus.controller.entities.BigCloudBody;
import com.dxenterprise.cumulus.controller.entities.BirdBody;
import com.dxenterprise.cumulus.controller.entities.EntityBody;
import com.dxenterprise.cumulus.controller.entities.MediumCloudBody;
import com.dxenterprise.cumulus.controller.entities.SmallCloudBody;
import com.dxenterprise.cumulus.model.SinglePGameModel;
import com.dxenterprise.cumulus.model.entities.BirdModel;
import com.dxenterprise.cumulus.model.entities.CloudModel;
import com.dxenterprise.cumulus.model.entities.EntityModel;
import com.dxenterprise.cumulus.view.SinglePGameView;

import java.util.ArrayList;
import java.util.List;

import static com.dxenterprise.cumulus.model.SinglePGameModel.NUMBER_CLOUDS;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class SinglePGameController implements ContactListener {
    /**
     * The camera position's x value.
     */
    private float camX;

    /**
     * The camera position's y value.
     */
    private float camY;

    /**
     * The camera velocity's x value.
     */
    private float camVX = 4f;

    /**
     * The camera acceleration's x value.
     */
    private float camAX = 0f;

    /**
     * Gets the camera position's x value.
     * @return the camera position's x value.
     */
    public float getCamX() {
        return camX;
    }

    /**
     * Sets the camera position's x value.
     * @param camX the camera position's new x value.
     */
    public void setCamX(float camX) {
        this.camX = camX;
    }

    /**
     * Gets the camera position's y value.
     * @return the camera position's y value.
     */
    public float getCamY() {
        return camY;
    }

    /**
     * Sets the camera position's y value.
     * @param camY the camera position's new y value.
     */
    public void setCamY(float camY) {
        this.camY = camY;
    }

    /**
     * Gets the camera velocity's x value.
     * @return the camera velocity's x value.
     */
    public float getCamVX() {
        return camVX;
    }

    /**
     * Sets the camera velocity's x value.
     * @param camVX the camera velocity's new x value.
     */
    public void setCamVX(float camVX) {
        this.camVX = camVX;
    }

    /**
     * Gets the camera acceleration's x value.
     * @return the camera acceleration's x value.
     */
    public float getCamAX() {
        return camAX;
    }

    /**
     * Sets the camera acceleration's x value.
     * @param camAX the camera acceleration's new x value.
     */
    public void setCamAX(float camAX) {
        this.camAX = camAX;
    }

    /**
     * Flag indicating if the game has been lost.
     */
    public boolean game_lost = false;

    /**
     * Checks if the game has been lost.
     * @return true if the game was lost, false if otherwise
     */
    public boolean isGame_lost() {
        return game_lost;
    }

    /**
     * The singleton instance of this controller
     */
    private static SinglePGameController instance;

    /**
     * The WORLD width in meters.
     */
    public static final int WORLD_WIDTH = 32;

    /**
     * The WORLD height in meters.
     */
    public static final int WORLD_HEIGHT = 9;

    /**
     * The force in y applied by jumping
     */
    public static final float JUMP_Y = 240f;

    /**
     * The force in x applied by jumping
     */
    public static final float JUMP_X = 15f;

    /**
     * The physics world controlled by this controller.
     */
    private final World world;

    /**
     * The spaceship body.
     */
    private final BirdBody playerBody;

    /**
     * Gets the current player body.
     * @return the player body
     */
    public BirdBody getPlayerBody(){
        return playerBody;
    }

    /**
     * Clouds that are visible to the player
     */
    private List<CloudModel> clouds = new ArrayList<CloudModel>();

    /**
     * Returns a singleton instance of a game controller
     *
     * @return the singleton instance
     */
    public static SinglePGameController getInstance() {
        if (instance == null)
            instance = new SinglePGameController();
        return instance;
    }

    /**
     * Creates a new SinglePGameController that controls the physics of a certain SinglePGameMode.
     */
    private SinglePGameController(){
        world = new World(new Vector2(0.6f,-10f), true);
        playerBody = new BirdBody(world, SinglePGameModel.getInstance().getPlayer());
        //instanciate clouds
        clouds = SinglePGameModel.getInstance().getClouds();
        for(CloudModel cloud : clouds){
            if(cloud.getSize() == CloudModel.CloudSize.BIG)
                new BigCloudBody(world,cloud);
            else if(cloud.getSize() == CloudModel.CloudSize.MEDIUM)
                new MediumCloudBody(world, cloud);
            else if(cloud.getSize() == CloudModel.CloudSize.SMALL)
                new SmallCloudBody(world, cloud);
        }

        world.setContactListener(this);
    }


    /**
     * This function is called every time two objects collide.
     * @param contact the contact that happened
     */
    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if (bodyA.getUserData() instanceof CloudModel && bodyB.getUserData() instanceof BirdModel){
            ((BirdModel) bodyB.getUserData()).setWalking(true);
            SinglePGameModel.getInstance().resetJumps();
        }

        if (bodyB.getUserData() instanceof CloudModel && bodyA.getUserData() instanceof BirdModel){
            ((BirdModel) bodyA.getUserData()).setWalking(true);
            SinglePGameModel.getInstance().resetJumps();
        }
    }

    /**
     * This function is called when two objects stop being in contact.
     * @param contact the contact
     */
    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    /**
     * Updates the game.
     * @param delta time interval between updates
     */
    public void update(float delta) {
        SinglePGameModel.getInstance().update(delta);
        playerBody.setTransform(playerBody.getX(),playerBody.getY(),0); //force rotation zero

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body body : bodies) {
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
            //((EntityModel) body.getUserData()).setRotation(body.getAngle());
            verifyBounds(body);
        }

        SinglePGameModel.getInstance().updateLostCondition((((EntityModel)playerBody.getUserData()).getX()/(SinglePGameView.PIXEL_TO_METER)) ,(float) (camX - WORLD_WIDTH/(3.35*SinglePGameView.PIXEL_TO_METER)));
        if( SinglePGameModel.getInstance().isGame_lost())
            game_lost = true;

    }

    /**
     * Verifies if the body is inside the arena bounds and if not
     * wraps it around to the other side.
     *
     * @param body The body to be verified.
     */
    private void verifyBounds(Body body) { //todo check if this works perfectly
        if (body.getUserData() instanceof CloudModel){
            if (body.getPosition().x < playerBody.getX()-WORLD_WIDTH/2){
               // body.setTransform(playerBody.getX()+WORLD_WIDTH/2, body.getPosition().y, body.getAngle());
                //^ COMO ESTAVA
                //apagar nuvens que sairam
                SinglePGameModel.getInstance().remove((EntityModel)body.getUserData());
                world.destroyBody(body);
                //cria uma nova nuvem
                CloudModel newCloud = SinglePGameModel.getInstance().addCloud(
                        playerBody.getX()+WORLD_WIDTH/2,
                        body.getPosition().y,
                        body.getAngle());

                if(newCloud.getSize() == CloudModel.CloudSize.BIG)
                    new BigCloudBody(world,newCloud);
                else if(newCloud.getSize() == CloudModel.CloudSize.MEDIUM)
                    new MediumCloudBody(world, newCloud);
                else if(newCloud.getSize() == CloudModel.CloudSize.SMALL)
                    new SmallCloudBody(world, newCloud);

            }

        }
    }

    /**
     * Returns the world controlled by this controller. Needed for debugging purposes only.
     *
     * @return The world controlled by this controller.
     */
    public World getWorld() {
        return world;
    }

    /**
     * The bird jumps
     *
     * @param delta Duration of the jump in seconds.
     */
    public void jump(float delta) {

        if(SinglePGameModel.getInstance().canJump()){
            playerBody.applyForceToCenter(JUMP_X,JUMP_Y, true);
            SinglePGameModel.getInstance().birdJump();
            SinglePGameModel.getInstance().getPlayer().setWalking(false);

        }
    }

    /**
     * Clears the game.
     */
    public void clear(){
        instance = null;
    }

    /**
     * Steps on the world.
     * @param delta time interval between steps
     * @param velocityIters velocity iterators
     * @param positionIters position iterators
     */
    public static void step_on_world(float delta, int velocityIters, int positionIters){
        if(instance == null)
            return ;
        else instance.getWorld().step(delta,velocityIters,positionIters);
    }

    /**
     * Gets the game's score.
     * @return the game score
     */
    public int getScore(){
        return SinglePGameModel.getInstance().getHighscore();
    }

}
