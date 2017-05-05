package com.dxenterprise.cumulus.model;

/**
 * Created by Xavier Fontes on 05/05/2017.
 */

public class SinglePGameModel {

    /**
     * The singleton instance of this controller
     */
    private static SinglePGameModel instance;


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

}
