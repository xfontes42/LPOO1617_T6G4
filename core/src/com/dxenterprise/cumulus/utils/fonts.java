package com.dxenterprise.cumulus.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Xavier Fontes on 26/05/2017.
 */

public class fonts {
    private static fonts instance;
    private BitmapFont Rancho90;
    private BitmapFont Clouds90;

    public static fonts getInstance(){
        if(instance == null)
            instance = new fonts();
        return instance;
    }

    private fonts(){
        loadFonts();
    }

    /**
     * Loads the Rancho font used in this screen.
     */
    private void loadFonts() {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Rancho-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        parameter.borderWidth = 0.5F;
        parameter.borderColor = Color.WHITE;
        Rancho90 = generator.generateFont(parameter); // font size 12 pixels

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/AlphaClouds.ttf"));
        Clouds90 = generator.generateFont(parameter); // font size 12 pixels

        generator.dispose();


    }

    public BitmapFont getRancho(){
        return Rancho90;
    }

    public BitmapFont getClouds(){
        return Clouds90;
    }

}
