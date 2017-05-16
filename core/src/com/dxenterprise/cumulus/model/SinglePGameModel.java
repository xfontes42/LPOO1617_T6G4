package com.dxenterprise.cumulus.model;

import com.badlogic.gdx.Input;
import com.dxenterprise.cumulus.controller.entities.BirdBody;
import com.dxenterprise.cumulus.model.entities.BirdModel;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class SinglePGameModel {

    /**
     * The singleton instance of this controller
     */
    private static SinglePGameModel instance;

    private BirdModel player;

    /**
     * Returns a singleton instance of a game controller
     *
     * @return the singleton instance
     */
    public static SinglePGameModel getInstance() {
        if (instance == null)
            instance = new SinglePGameModel();
        return instance;
    }

    private SinglePGameModel(){

    }

    public BirdModel getPlayer() {
        return player;
    }
}
