package com.dxenterprise.cumulus.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dxenterprise.cumulus.MyCumulusGame;
import com.dxenterprise.cumulus.model.SinglePGameModel;
import com.dxenterprise.cumulus.model.entities.BirdModel;
import com.dxenterprise.cumulus.model.entities.EntityModel;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class BirdView extends EntityView {
    /**
     * The time between the animation frames
     */
    private static final float FRAME_TIME = 0.1f;

    /**
     *  The animation used when the bird is walking
     */
    private Animation<TextureRegion> walkingAnimation;

    /**
     * The texture used when the bird jumps
     */
    private TextureRegion jumpingAnimation;


    /**
     * Time since the bird started the game. Used
     * to calculate the frame to show in animations.
     */
    private float stateTime = 0;




    /**
     * Constructs a bird view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public BirdView(MyCumulusGame game) {
        super(game);
    }



    /**
     * Creates a sprite representing this bird.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing the bird
     */
    @Override
    public Sprite createSprite(MyCumulusGame game) {
        walkingAnimation = createWalkingAnimation(game);
        jumpingAnimation = createJumpingAnimation(game);

        return new Sprite(jumpingAnimation);
    }


    /**
     * Creates the texture used when the bird is jumping
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the texture used when the bird is jumping
     */
    private TextureRegion createJumpingAnimation(MyCumulusGame game) {
        Texture jumping = game.getAssetManager().get("bird.png");
        //todo check if this is correct
        return new TextureRegion(jumping, 2/3f,0f,1f,1/3f);
    }

    /**
     * Creates the animation used when bird is walking
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the animation used when the bird is walking
     */
    private Animation<TextureRegion> createWalkingAnimation(MyCumulusGame game) {
        Texture walkingTexture = game.getAssetManager().get("bird.png");
        TextureRegion[][] walkingRegion = TextureRegion.split(walkingTexture, walkingTexture.getWidth() / 3, walkingTexture.getHeight()/3);

        TextureRegion[] frames = new TextureRegion[2]; //todo change to accomodate any color of bird
        System.arraycopy(walkingRegion[0], 0, frames, 0, 2);

        return new Animation<TextureRegion>(FRAME_TIME, frames);
    }

    /**
     * Updates this bird model. Also save and resets
     * the jumping flag from the model.
     *
     * @param model the model used to update this view
     */
    @Override
    public void update(EntityModel model) {
        super.update(model);

    }

    /**
     * Draws the sprite from this view using a sprite batch.
     * Chooses the correct texture or animation to be used
     * depending on the walking flag.
     *
     * @param batch The sprite batch to be used for drawing.
     */
    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        if (SinglePGameModel.getInstance().getPlayer().isWalking())
            sprite.setRegion(walkingAnimation.getKeyFrame(stateTime, true));
        else
            sprite.setRegion(jumpingAnimation);

        sprite.draw(batch);
    }
}
