package com.dxenterprise.cumulus.model.entities;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

/**
 * An abstract model representing an entity belonging to a game model.
 */
public abstract class EntityModel {
    /**
     * The types of entities (clouds of different sizes, and player)
     */
    public enum ModelType {BIGCLOUD, MEDIUMCLOUD, SMALLCLOUD, PLAYER};//, POWERUP, POWERDOWN};

    /**
     * Gets the entity's velocity in X.
     * @return the entity's velocity in X.
     */
    public float getVx() {
        return vx;
    }

    /**
     * Sets the entity's velocity in X.
     * @param vx the entity's new velocity in X.
     */
    public void setVx(float vx) {
        this.vx = vx;
    }

    /**
     * Gets the entity's velocity in Y.
     * @return the entity's velocity in Y.
     */
    public float getVy() {
        return vy;
    }

    /**
     * Sets the entity's velocity in Y.
     * @param vy the entity's new velocity in Y.
     */
    public void setVy(float vy) {
        this.vy = vy;
    }

    /**
     * The entity's velocity in X.
     */
    private float vx;

    /**
     * The entity's velocity in Y.
     */
    private float vy;

    /**
     * The x-coordinate of this model in meters.
     */
    private float x;

    /**
     * The y-coordinate of this model in meters.
     */
    private float y;

    /**
     * The current rotation of this model in radians.
     */
    private float rotation;

    /**
     * Has this model been flagged for removal?
     */
    private Boolean flaggedForRemoval = false;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x The x-coordinate of this entity in meters.
     * @param y The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     */
    EntityModel(float x, float y, float rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    /**
     * Returns the x-coordinate of this entity.
     *
     * @return The x-coordinate of this entity in meters.
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this entity.
     *
     * @return The y-coordinate of this entity in meters.
     */
    public float getY() {
        return y;
    }

    /**
     * Returns the rotation of this entity.
     *
     * @return The rotation of this entity in radians.
     */
    public float getRotation() {
        return rotation;
    }

    /**
     * Sets the position of this entity.
     *
     * @param x The x-coordinate of this entity in meters.
     * @param y The y-coordinate of this entity in meters.
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the rotation of this entity.
     *
     * @param rotation The current rotation of this entity in radians.
     */
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    /**
     * Checks if the entity is flagged to be removed.
     * @return true if the entity is to be removed, false if otherwise
     */
    public boolean isFlaggedToBeRemoved() {
        return flaggedForRemoval;
    }

    /**
     * Makes this model flagged for removal on next step.
     */
    public void setFlaggedForRemoval(boolean flaggedForRemoval) {
        this.flaggedForRemoval = flaggedForRemoval;
    }

    /**
     * Gets the type of the entity.
     * @return a value related to the class and type of the entity
     */
    public abstract ModelType getType();
}
