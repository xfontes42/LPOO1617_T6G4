package com.dxenterprise.cumulus.controller;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class SinglePGameController implements ContactListener {

    /**
     * The singleton instance of this controller
     */
    private static SinglePGameController instance;


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
}
