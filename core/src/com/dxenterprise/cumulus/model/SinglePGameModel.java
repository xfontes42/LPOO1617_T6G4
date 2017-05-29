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

    /**
     * The amount of times the bird jumped since it last touched a cloud. This value goes from 0 to 2.
     */
    private int jumps = 0;

    /**
     * The current highscore.
     */
    private int highscore = 0;

    /**
     * The current highscore.
     */
    public int getHighscore() {
        return highscore;
    }

    /**
     * Sets the lose/not-lose state of the game.
     * @param game_lost the new lose/not-lose state
     */
    public void setGame_lost(boolean game_lost) {
        this.game_lost = game_lost;
    }

    /**
     * The lose/not-lose state of the game.
     */
    private boolean game_lost = false;

    /**
     * Returns the lose/not-lose state of the game.
     * @return true if the game has been lost, false if otherwise
     */
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
     * Returns a singleton instance of the game model.
     *
     * @return the singleton instance
     */
    public static SinglePGameModel getInstance() {
        if (instance == null)
            instance = new SinglePGameModel();
        return instance;
    }

    /**
     * Creates the game model.
     */
    private SinglePGameModel(){
        clouds = new ArrayList<CloudModel>();
        playerModel = new BirdModel(SinglePGameController.WORLD_WIDTH / 2f, SinglePGameController.WORLD_HEIGHT / 2f, 0);

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
                    (float)-1.8f-2.5f*random.nextFloat(),
                    (float) 0,
                    c);
            atual.setFlaggedForRemoval(false);
            clouds.add(atual);

        }

    }

    /**
     * Gets the current player model.
     * @return the player model
     */
    public BirdModel getPlayer() {
        return playerModel;
    }

    /**
     * Gets a list of generated active clouds.
     * @return the active clouds.
     */
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

    /**
     * Updates the game logic.
     * @param delta the time interval between updates.
     */
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

    /**
     * Adds a cloud to the game.
     * @param x the cloud's x value
     * @param y the cloud's y value
     * @param ang the cloud's angle
     * @return the cloud model created
     */
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

    /**
     * Clears the game.
     */
    public void clear(){
        instance = null;
    }

    /**
     * Checks if the player has lost.
     *
     * The player loses when they fall below a certain threshold or when the camera leaves them behind.
     * @param position_player the player's position
     * @param position_cam the camera's position
     */
    public void updateLostCondition(float position_player, float position_cam){
        if(position_player < position_cam)
            game_lost = true;
    }

    /**
     * Checks if the bird can still jump
     * @return true if the bird has jumped less than 2 times, false if otherwise
     */
    public boolean canJump() {
        return (jumps <2);
    }

    /**
     * The bird jumps.
     */
    public void birdJump(){
        jumps++;
    }

    /**
     * Resets the bird's jump counter.
     */
    public void resetJumps(){
        jumps = 0;
    }

}