package com.dxenterprise.cumulus.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.dxenterprise.cumulus.model.entities.CloudModel;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class BigCloudBody extends EntityBody {

    /**
     * Constructs a bIG cloud according to a cloud model
     *
     * @param world the physical world
     * @param model the model representing the cloud
     */
    public BigCloudBody(World world, CloudModel model) {
        super(world, model);

        float density = 1f, friction = 0.2f, restitution = 0.1f;
        int width = 2560, height = 512;

        createFixture(body, new float[]{
                83, 87,    531, 5,     2083, 5,     2509, 121,      2453, 467,      90, 469
        }, width, height, density, friction, restitution, CLOUD_BODY, (short) (CLOUD_BODY | PLAYER_BODY | POW_BODY));
    }
}
