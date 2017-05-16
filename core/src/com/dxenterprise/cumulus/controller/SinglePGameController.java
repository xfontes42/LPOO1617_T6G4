package com.dxenterprise.cumulus.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class SinglePGameController implements ContactListener {
    /**
     * The singleton instance of this controller
     */
    private static SinglePGameController instance;

    /**
     * The physics world controlled by this controller.
     */
    private final World world;

    /**
     * Returns a singleton instance of a game controller
     *
     * @return the singleton instance
     */
    public static SinglePGameController getInstance() {
        if (instance == null)
            instance = new SinglePGameController();
        return instance;
    }

    private SinglePGameController(){
        world = new World(new Vector2(0, 0), true);
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public void removeFlagged() {
    }

    public void createNewClouds() {
    }

    public void update(float delta) {
    }

    /**
     * Returns the world controlled by this controller. Needed for debugging purposes only.
     *
     * @return The world controlled by this controller.
     */
    public World getWorld() {
        return world;
    }
}
