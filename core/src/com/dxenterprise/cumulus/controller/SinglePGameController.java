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
import com.dxenterprise.cumulus.model.entities.CloudModel;
import com.dxenterprise.cumulus.model.entities.EntityModel;

import java.util.ArrayList;
import java.util.List;

import static com.dxenterprise.cumulus.model.SinglePGameModel.NUMBER_CLOUDS;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class SinglePGameController implements ContactListener {
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
     *
     * The force in y applied by jumping
     *
     */
    public static final float JUMP_Y = 1f;

    /**
     *
     * The force in x applied by jumping
     *
     */
    public static final float JUMP_X = 0.5f;

    /**
     * The physics world controlled by this controller.
     */
    private final World world;

    /**
     * The spaceship body.
     */
    private final BirdBody playerBody;


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
     *
     */
    private SinglePGameController(){
        world = new World(new Vector2(0,0), true);
        playerBody = new BirdBody(world, SinglePGameModel.getInstance().getPlayer());
        //instanciate
        List<CloudModel> all_clouds = SinglePGameModel.getInstance().getClouds();
        for(CloudModel cloud : all_clouds){
            if(cloud.getSize() == CloudModel.CloudSize.BIG)
                new BigCloudBody(world,cloud);
            else if(cloud.getSize() == CloudModel.CloudSize.MEDIUM)
                new MediumCloudBody(world, cloud);
            else if(cloud.getSize() == CloudModel.CloudSize.SMALL)
                new SmallCloudBody(world, cloud);
        }

        //

//        cloudsToShow
//                cloudsShowing
        world.setContactListener(this);
    }



    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public void removeFlagged() {
        int i = 0;
       // float deltaClouds = WORLD_WIDTH / SinglePGameModel.NUMBER_CLOUDS;

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            if(((EntityModel) body.getUserData()).isFlaggedToBeRemoved())
                ((EntityModel) body.getUserData()).setFlaggedForRemoval(false);
                body.setTransform(
                        body.getPosition().x + SinglePGameController.WORLD_WIDTH ,
                        body.getPosition().y,
                        0
                );



        }
    }

    public void createNewClouds() {
    }

    public void update(float delta) {
        SinglePGameModel.getInstance().update(delta);

        /*
    similar
          timeToNextShoot -= delta;

        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1/60f) {
            world.step(1/60f, 6, 2);
            accumulator -= 1/60f;
        }

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            verifyBounds(body);
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
            ((EntityModel) body.getUserData()).setRotation(body.getAngle());
        }

         */

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body body : bodies) {
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
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



//
//
//
//
//    /**
//     * Verifies if the body is inside the arena bounds and if not
//     * wraps it around to the other side.
//     *
//     * @param body The body to be verified.
//     */
//    private void verifyBounds(Body body) {
//        if (body.getPosition().x < 0)
//            body.setTransform(ARENA_WIDTH, body.getPosition().y, body.getAngle());
//
//        if (body.getPosition().y < 0)
//            body.setTransform(body.getPosition().x, ARENA_HEIGHT, body.getAngle());
//
//        if (body.getPosition().x > ARENA_WIDTH)
//            body.setTransform(0, body.getPosition().y, body.getAngle());
//
//        if (body.getPosition().y > ARENA_HEIGHT)
//            body.setTransform(body.getPosition().x, 0, body.getAngle());
//    }
//
//    /**
//     * Returns the world controlled by this controller. Needed for debugging purposes only.
//     *
//     * @return The world controlled by this controller.
//     */
//    public World getWorld() {
//        return world;
//    }
//
//    /**
//     * Rotates the spaceship left. The rotation takes into consideration the
//     * constant rotation speed and the delta for this simulation step.
//     *
//     * @param delta Duration of the rotation in seconds.
//     */
//    public void rotateLeft(float delta) {
//        shipBody.setTransform(shipBody.getX(), shipBody.getY(), shipBody.getAngle() + ROTATION_SPEED * delta);
//        shipBody.setAngularVelocity(0);
//    }
//
//    /**
//     * Rotates the spaceship right. The rotation takes into consideration the
//     * constant rotation speed and the delta for this simulation step.
//     *
//     * @param delta Duration of the rotation in seconds.
//     */
//    public void rotateRight(float delta) {
//        shipBody.getX();
//
//        shipBody.setTransform(shipBody.getX(), shipBody.getY(), shipBody.getAngle() - ROTATION_SPEED * delta);
//        shipBody.setAngularVelocity(0);
//    }
//
//    /**
//     * Acceleratesins the spaceship. The acceleration takes into consideration the
//     * constant acceleration force and the delta for this simulation step.
//     *
//     * @param delta Duration of the rotation in seconds.
//     */
//    public void accelerate(float delta) {
//        shipBody.applyForceToCenter(-(float) sin(shipBody.getAngle()) * ACCELERATION_FORCE * delta, (float) cos(shipBody.getAngle()) * ACCELERATION_FORCE * delta, true);
//        ((ShipModel)shipBody.getUserData()).setAccelerating(true);
//    }
//
//    /**
//     * Shoots a bullet from the spaceship at 10m/s
//     */
//    public void shoot() {
//        if (timeToNextShoot < 0) {
//            BulletModel bullet = GameModel.getInstance().createBullet(GameModel.getInstance().getShip());
//            BulletBody body = new BulletBody(world, bullet);
//            body.setLinearVelocity(BULLET_SPEED);
//            timeToNextShoot = TIME_BETWEEN_SHOTS;
//        }
//    }
//
//    /**
//     * A contact between two objects was detected
//     *
//     * @param contact the detected contact
//     */
//    @Override
//    public void beginContact(Contact contact) {
//        Body bodyA = contact.getFixtureA().getBody();
//        Body bodyB = contact.getFixtureB().getBody();
//
//        if (bodyA.getUserData() instanceof BulletModel)
//            bulletCollision(bodyA);
//        if (bodyB.getUserData() instanceof BulletModel)
//            bulletCollision(bodyB);
//
//        if (bodyA.getUserData() instanceof BulletModel && bodyB.getUserData() instanceof AsteroidModel)
//            bulletAsteroidCollision(bodyA, bodyB);
//        if (bodyA.getUserData() instanceof AsteroidModel && bodyB.getUserData() instanceof BulletModel)
//            bulletAsteroidCollision(bodyB, bodyA);
//
//    }
//
//    @Override
//    public void endContact(Contact contact) {
//
//    }
//
//    @Override
//    public void preSolve(Contact contact, Manifold oldManifold) {
//
//    }
//
//    @Override
//    public void postSolve(Contact contact, ContactImpulse impulse) {
//
//    }
//
//    /**
//     * A bullet colided with something. Lets remove it.
//     *
//     * @param bulletBody the bullet that colided
//     */
//    private void bulletCollision(Body bulletBody) {
//        ((BulletModel)bulletBody.getUserData()).setFlaggedForRemoval(true);
//    }
//
//    /**
//     * A bullet collided with an asteroid. Lets remove the asteroids and break into
//     * pieces if needed.
//     * @param bulletBody the bullet that collided
//     * @param asteroidBody the asteroid that collided
//     */
//    private void bulletAsteroidCollision(Body bulletBody, Body asteroidBody) {
//        AsteroidModel asteroidModel = (AsteroidModel) asteroidBody.getUserData();
//        asteroidModel.setFlaggedForRemoval(true);
//
//        if (asteroidModel.getSize() == AsteroidModel.AsteroidSize.BIG) {
//            for (int i = 0; i < FRAGMENT_COUNT; i++)
//                asteroidsToAdd.add(new AsteroidModel(asteroidModel.getX(), asteroidModel.getY(), (float) (asteroidModel.getRotation() * i * 2 * PI / 5), AsteroidModel.AsteroidSize.MEDIUM));
//        }
//    }
//
//    /**
//     * Creates the asteroids that have been added in the previous
//     * simulation step.
//     */
//    public void createNewAsteroids() {
//        for (AsteroidModel asteroidModel : asteroidsToAdd) {
//            GameModel.getInstance().addAsteroid(asteroidModel);
//            if (asteroidModel.getSize() == AsteroidModel.AsteroidSize.MEDIUM) {
//                MediumAsteroidBody body = new MediumAsteroidBody(world, asteroidModel);
//                body.setLinearVelocity((float) (Math.random() * 5));
//            }
//        }
//        asteroidsToAdd.clear();
//    }
//
//    /**
//     * Removes objects that have been flagged for removal on the
//     * previous step.
//     */
//    public void removeFlagged() {
//        Array<Body> bodies = new Array<Body>();
//        world.getBodies(bodies);
//        for (Body body : bodies) {
//            if (((EntityModel)body.getUserData()).isFlaggedToBeRemoved()) {
//                GameModel.getInstance().remove((EntityModel) body.getUserData());
//                world.destroyBody(body);
//            }
//        }
//    }
//
//     */
}
