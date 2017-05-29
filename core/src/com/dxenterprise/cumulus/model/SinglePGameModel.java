package com.dxenterprise.cumulus.model;

;

import com.dxenterprise.cumulus.controller.SinglePGameController;
import com.dxenterprise.cumulus.model.entities.BirdModel;
import com.dxenterprise.cumulus.model.entities.CloudModel;
import com.dxenterprise.cumulus.model.entities.EntityModel;
import com.dxenterprise.cumulus.view.SinglePGameView;


import java.util.List;
import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class SinglePGameModel {

    private int jumps = 0;

    private int highscore = 0;

    public int getHighscore() {
        return highscore;
    }

    public void setGame_lost(boolean game_lost) {
        this.game_lost = game_lost;
    }

    private boolean game_lost = false;

    public boolean isGame_lost() {
        return game_lost;
    }

    /**
     * The singleton instance of this controller
     */
    private static SinglePGameModel instance;


    /**
     * Number of cloud to generate per visual screen
     */
    public static final int NUMBER_CLOUDS = 5;


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
        clouds = new ArrayList<CloudModel>();
        playerModel = new BirdModel(SinglePGameController.WORLD_WIDTH / 2f, SinglePGameController.WORLD_HEIGHT / 2, 0);
        //generate clouds in own function...

        //these are the clouds that are showing
        float deltaClouds = (float)SinglePGameController.WORLD_WIDTH / NUMBER_CLOUDS;
        for (int i = 0; i < NUMBER_CLOUDS; i++){
            CloudModel.CloudSize c;
            int rand = random.nextInt();
            if(rand % 3 == 0)
                c = CloudModel.CloudSize.BIG;
            else if (rand % 3 == 1)
                c = CloudModel.CloudSize.MEDIUM;
            else c = CloudModel.CloudSize.SMALL;

            CloudModel atual = new CloudModel(
                    deltaClouds*i,
                    //random.nextFloat() * SinglePGameController.WORLD_HEIGHT/5,
                    (float)-1.8f-2.5f*random.nextFloat(),
                    (float) 0,
                    c);
            atual.setFlaggedForRemoval(false);
            clouds.add(atual);

        }

    }

    public BirdModel getPlayer() {
        return playerModel;
    }

    public List<CloudModel> getClouds() { return clouds;}


    /**
     * Removes a model from this game.
     *
     * @param model the model to be removed
     */
    public void remove(EntityModel model) {
        if (model instanceof CloudModel) {
            clouds.remove(model);
        }
    }


    public  void update(float delta){
        //this 2 instructions probably can be done in some other better way
        SinglePGameController.step_on_world(delta,6,2);   //getInstance().getWorld().step(delta,6,2); took this away because of unit tests
        playerModel.setPosition(playerModel.getX()+delta*playerModel.getVx(),playerModel.getY());
        if(playerModel.getY() <= -SinglePGameController.WORLD_HEIGHT/1.5f)
            game_lost = true;

        if(!game_lost){
            highscore += playerModel.getX()*delta + playerModel.getVx();
        }
    }

    public CloudModel addCloud(float x, float y, float ang){
        CloudModel.CloudSize c;
        int rand = random.nextInt();
        if(rand % 3 == 0)
            c = CloudModel.CloudSize.BIG;
        else if (rand % 3 == 1)
            c = CloudModel.CloudSize.MEDIUM;
        else c = CloudModel.CloudSize.SMALL;
        CloudModel newCloud = new CloudModel(x,y,ang,c);
        clouds.add(newCloud);
        return newCloud;
    }


    public void clear(){
        instance = null;
    }


    public void updateLostCondition(float position_player, float position_cam){
        if(position_player < position_cam)
            game_lost = true;
    }

    public boolean canJump() {
        return (jumps <2);
    }

    public void birdJump(){
        jumps++;
    }

    public void resetJumps(){
        jumps = 0;
    }

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


}