package com.dxenterprise.cumulus.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.dxenterprise.cumulus.model.entities.CloudModel;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class SmallCloudBody extends EntityBody {

    /**
     *
     * Constructs a small cloud according to a cloud model
     * @param world the physical world
     * @param model the model representing the cloud
     */
    public SmallCloudBody(World world, CloudModel model) {
        super(world, model);

        float density = 1000f, friction = 0.2f, restitution = 0f;
        int width = 1536, height = 512;

        createFixture(body, new float[]{
                95,93,  479,9,  1079,9, 1493,121,   1437,463,  95,475
        }, width, height, density, friction, restitution, CLOUD_BODY , (short) (CLOUD_BODY | PLAYER_BODY | POW_BODY));

        body.setType(BodyDef.BodyType.StaticBody);
    }
}
