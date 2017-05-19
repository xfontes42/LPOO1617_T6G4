package com.dxenterprise.cumulus.model;

;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.dxenterprise.cumulus.controller.SinglePGameController;
import com.dxenterprise.cumulus.model.entities.BirdModel;
import com.dxenterprise.cumulus.model.entities.CloudModel;


import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class SinglePGameModel {
    /**
     * The singleton instance of this controller
     */
    private static SinglePGameModel instance;


    /**
     * Number of cloud to generate per visual screen
     */
    public static final int NUMBER_CLOUDS = 4;


    /**
     * Model of main player
     */
    private BirdModel playerModel;


    /**
     * Model of all clouds
     */
    private List<CloudModel> clouds;

    /**
     * Returns a singleton instance of a game controller
     *
     * @return the singleton instance
     */
    public static SinglePGameModel getInstance() {
        if (instance == null)
            instance = new SinglePGameModel();
        return instance;
    }

    private SinglePGameModel(){
        playerModel = new BirdModel(SinglePGameController.WORLD_WIDTH / 2, SinglePGameController.WORLD_HEIGHT / 2, 0);
        //generate clouds in own function...
    }

    public BirdModel getPlayer() {
        return playerModel;
    }

    public List<CloudModel> getClouds() { return clouds;}

//
//
//    /**
//     * Constructs a game with a.space ship in the middle of the
//     * arena and a certain number of asteroids in different sizes.
//     */
//    private GameModel() {
//        asteroids = new ArrayList<AsteroidModel>();
//        bullets = new ArrayList<BulletModel>();
//        ship = new ShipModel(GameController.ARENA_WIDTH / 2, GameController.ARENA_HEIGHT / 2, 0);
//
//        for (int i = 0; i < ASTEROID_COUNT; i++)
//            asteroids.add(new AsteroidModel(
//                    random.nextFloat() * GameController.ARENA_WIDTH,
//                    random.nextFloat() * GameController.ARENA_HEIGHT,
//                    (float) Math.toRadians(random.nextFloat() * 360),
//                    random.nextBoolean()?AsteroidModel.AsteroidSize.BIG:AsteroidModel.AsteroidSize.MEDIUM));
//    }
//
//    /**
//     * Returns the player space ship.
//     *
//     * @return the space ship.
//     */
//    public ShipModel getShip() {
//        return ship;
//    }
//
//    /**
//     * Returns the asteroids.
//     *
//     * @return the asteroid list
//     */
//    public List<AsteroidModel> getAsteroids() {
//        return asteroids;
//    }
//
//    /**
//     * Returns the bullets.
//     *
//     * @return the bullet list
//     */
//    public List<BulletModel> getBullets() {
//        return bullets;
//    }
//
//    public BulletModel createBullet(ShipModel ship) {
//        BulletModel bullet = bulletPool.obtain();
//
//        bullet.setFlaggedForRemoval(false);
//        bullet.setPosition(ship.getX() - (float)(Math.sin(ship.getRotation()) * 1.4), ship.getY() + (float)(Math.cos(ship.getRotation()) * 1.4));
//        bullet.setRotation(ship.getRotation());
//        bullet.setTimeToLive(.5f);
//
//        bullets.add(bullet);
//
//        return bullet;
//    }
//
//    /**
//     * Removes a model from this game.
//     *
//     * @param model the model to be removed
//     */
//    public void remove(EntityModel model) {
//        if (model instanceof BulletModel) {
//            bullets.remove(model);
//            bulletPool.free((BulletModel) model);
//        }
//        if (model instanceof AsteroidModel) {
//            asteroids.remove(model);
//        }
//    }
//
//    /**
//     * Adds a new asteroid to the model
//     *
//     * @param asteroidModel the asteroid model to be added
//     */
//    public void addAsteroid(AsteroidModel asteroidModel) {
//        asteroids.add(asteroidModel);
//    }
//
//    public void update(float delta) {
//        for (BulletModel bullet : bullets)
//            if (bullet.decreaseTimeToLive(delta))
//                bullet.setFlaggedForRemoval(true);
//    }

    public  void update(float delta){
        SinglePGameController.getInstance().getWorld().step(delta,6,2);
        playerModel.setPosition(playerModel.getX()+delta*playerModel.getVx(),playerModel.getY());


    }
}