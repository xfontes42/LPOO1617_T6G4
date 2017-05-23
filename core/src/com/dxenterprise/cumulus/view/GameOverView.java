package com.dxenterprise.cumulus.view;

import com.badlogic.gdx.ScreenAdapter;
import com.dxenterprise.cumulus.MyCumulusGame;

/**
 * Created by Xavier Fontes on 23/05/2017.
 */

public class GameOverView extends ScreenAdapter {

    /**
     * The game this screen belongs to.
     */
    private final MyCumulusGame game;

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public GameOverView(MyCumulusGame game) {
        this.game = game;
    }

}
