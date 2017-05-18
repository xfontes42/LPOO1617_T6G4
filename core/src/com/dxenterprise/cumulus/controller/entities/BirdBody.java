package com.dxenterprise.cumulus.controller.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.dxenterprise.cumulus.model.entities.BirdModel;
import com.dxenterprise.cumulus.model.entities.EntityModel;

import static com.dxenterprise.cumulus.view.SinglePGameView.PIXEL_TO_METER;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class BirdBody extends EntityBody {

    final void createFixtureBird(Body body, int width, int height, float density, float friction, float restitution, short category, short mask) {
        CircleShape circle = new CircleShape();
        circle.setRadius(0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;

        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixtureDef.filter.categoryBits = category;
        fixtureDef.filter.maskBits = mask;
        body.createFixture(fixtureDef);

        circle.dispose();
    }

    public BirdBody(World world, BirdModel player) {
        super(world, player);

        float density = 1f, friction = 0.2f, restitution = 0.1f;
        int width = 512, height = 512;

        createFixtureBird(body, width, height, density, friction, restitution, CLOUD_BODY, (short) (CLOUD_BODY | PLAYER_BODY | POW_BODY));

    }
}
