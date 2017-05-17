package com.dxenterprise.cumulus.model.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.dxenterprise.cumulus.controller.entities.EntityBody;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class BirdModel extends EntityModel {

    /**
     * Creates a new bird model in a certain position and having a certain rotation.
     *
     * @param x the x-coordinate in meters
     * @param y the y-coordinate in meters
     * @param rotation the rotation in radians
     */
    public BirdModel(float x, float y, int rotation) {
       super(x, y,rotation);
    }


    @Override
    public ModelType getType() {
        return ModelType.PLAYER;
    }
}
