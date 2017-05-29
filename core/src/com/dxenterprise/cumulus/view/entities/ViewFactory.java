package com.dxenterprise.cumulus.view.entities;

import com.dxenterprise.cumulus.MyCumulusGame;
import com.dxenterprise.cumulus.model.entities.EntityModel;

import java.util.HashMap;
import java.util.Map;

import static com.dxenterprise.cumulus.model.entities.EntityModel.ModelType.BIGCLOUD;
import static com.dxenterprise.cumulus.model.entities.EntityModel.ModelType.MEDIUMCLOUD;
import static com.dxenterprise.cumulus.model.entities.EntityModel.ModelType.PLAYER;
import static com.dxenterprise.cumulus.model.entities.EntityModel.ModelType.SMALLCLOUD;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class ViewFactory {
    /**
     * The cache the entities are stored in.
     */
    private static Map<EntityModel.ModelType, EntityView> cache =
            new HashMap<EntityModel.ModelType, EntityView>();

    /**
     * Creates an entity based on the model provided.
     * @param game the game instance running.
     * @param model the model whose entity is being created.
     * @return
     */
    public static EntityView makeView(MyCumulusGame game, EntityModel model) {
        if (!cache.containsKey(model.getType())) {
            if (model.getType() == BIGCLOUD) cache.put(model.getType(), new BigCloudView(game));
            if (model.getType() == MEDIUMCLOUD)cache.put(model.getType(), new MediumCloudView(game));
            if (model.getType() == SMALLCLOUD) cache.put(model.getType(), new SmallCloudView(game));
            if (model.getType() == PLAYER) cache.put(model.getType(), new BirdView(game));
            //todo adicionar power up and down quando/se chegar à altura
        }
        return cache.get(model.getType());
    }

}
