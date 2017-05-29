package com.dxenterprise.cumulus.model.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.dxenterprise.cumulus.controller.entities.EntityBody;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class BirdModel extends EntityModel {
    /**
     * This flag changes value whether the bird is walking or jumping.
     */
    private boolean walking = false;

    /**
     * Set the walking flag for this bird
     *
     * @param walking the accelerating tag
     */
    public void setWalking(boolean walking) {
        this.walking = walking;
    }

    /**
     * Is the bird walking in this update
     *
     * @return the walking flag
     */
    public boolean isWalking() {
        return walking;
    }


    /**
     * Creates a new bird model in a certain position and having a certain rotation.
     *
     * @param x the x-coordinate in meters
     * @param y the y-coordinate in meters
     * @param rotation the rotation in radians
     */
    public BirdModel(float x, float y, int rotation) {
       super(x, y,rotation);
        setVx(2f);
    }

    /**
     * Returns the type of the entity.
     * @return the bird's ModelType (player)
     */
    @Override
    public ModelType getType() {
        return ModelType.PLAYER;
    }

}
