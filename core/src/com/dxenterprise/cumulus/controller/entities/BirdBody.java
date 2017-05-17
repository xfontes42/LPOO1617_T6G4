package com.dxenterprise.cumulus.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.dxenterprise.cumulus.model.entities.BirdModel;
import com.dxenterprise.cumulus.model.entities.EntityModel;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class BirdBody extends EntityBody {
    public BirdBody(World world, BirdModel player) {
        super(world, player);
    }
}
