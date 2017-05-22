package com.dxenterprise.cumulus.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dxenterprise.cumulus.MyCumulusGame;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class BigCloudView extends EntityView {

    /**
     * Constructs a Big CLOUD view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public BigCloudView(MyCumulusGame game) {
        super(game);
    }

    /**
     * Creates a sprite representing this CLOUD.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this asteroid
     */
    public Sprite createSprite(MyCumulusGame game) {
        Texture texture = game.getAssetManager().get("cloudBig.png");
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
