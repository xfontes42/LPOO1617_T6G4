package com.dxenterprise.cumulus.model.entities;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class CloudModel extends EntityModel {
    /**
     * Possible cloud sizes.
     */
    public enum CloudSize {BIG, MEDIUM,SMALL};

    /**
     * This cloud size.
     */
    private CloudSize size;

    /**
     * Constructs a asteroid model belonging to a game model.
     *
     * @param x The x-coordinate of this asteroid.
     * @param y The y-coordinate of this asteroid.
     * @param rotation The rotation of this asteroid.
     * @param size The size of this asteroid.
     */
    public CloudModel(float x, float y, float rotation, CloudSize size) {
        super(x, y, rotation);
        this.size = size;
    }

    /**
     * Returns the size of this asteroid.
     *
     * @return The size of this asteroid.
     */
    public CloudSize getSize() {
        return size;
    }


    @Override
    public ModelType getType() {
        if(size == CloudSize.BIG)
            return ModelType.BIGCLOUD;
        if(size == CloudSize.MEDIUM)
            return ModelType.MEDIUMCLOUD;
        if(size == CloudSize.SMALL)
            return ModelType.SMALLCLOUD;
        return null;
    }
}
