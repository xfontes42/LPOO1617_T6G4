package com.dxenterprise.cumulus.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.dxenterprise.cumulus.model.entities.CloudModel;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class MediumCloudBody extends EntityBody {

    /**
     * Constructs a MEDIUM cloud according to a cloud model
     *
     * @param world the physical world
     * @param model the model representing the cloud
     */
    public MediumCloudBody(World world, CloudModel model) {
        super(world, model);

        float density = 1000f, friction = 0.2f, restitution = 0f;
        int width = 2048, height = 512;

        createFixture(body, new float[]{
                71, 50, 487, 1, 1591, 1, 1981, 101, 1929, 469, 107, 469
        }, width, height, density, friction, restitution, CLOUD_BODY, (short) (CLOUD_BODY | PLAYER_BODY | POW_BODY));

        body.setType(BodyDef.BodyType.StaticBody);
    }

}
