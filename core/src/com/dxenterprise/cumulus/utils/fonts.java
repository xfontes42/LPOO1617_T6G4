package com.dxenterprise.cumulus.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by Xavier Fontes on 26/05/2017.
 */

public class fonts {

    /**
     * The fonts' instance.
     */
    private static fonts instance;

    /**
     * The Rancho Regular font, at 80p.
     */
    private BitmapFont Rancho80;

    /**
     * The AlphaClouds font, at 80p.
     */
    private BitmapFont Clouds80;

    /**
     * The Rancho Regular font, at 40p.
     */
    private BitmapFont Rancho40;

    /**
     * Returns the fonts' instance, creating it if it doesn't exist.
     * @return The fonts' instance
     */
    public static fonts getInstance(){
        if(instance == null)
            instance = new fonts();
        return instance;
    }

    /**
     * Loads the fonts.
     */
    private fonts(){
        loadFonts();
    }

    /**
     * Loads the Rancho and AlphaClouds fonts used in the game.
     */
    private void loadFonts() {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/AlphaClouds.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        parameter.borderWidth = 0.5F;
        parameter.borderColor = Color.WHITE;
        Clouds80 = generator.generateFont(parameter);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Rancho-Regular.ttf"));
        Rancho80 = generator.generateFont(parameter);
        parameter.size = 40;
        Rancho40 = generator.generateFont(parameter);
        generator.dispose();


    }

    /**
     * Returns the Rancho font.
     * @return the font
     */
    public BitmapFont getRancho(){
        return Rancho80;
    }

    /**
     * Returns the Rancho40 font.
     * @return the font
     */
    public BitmapFont getRancho40(){
        return Rancho40;
    }

    /**
     * Returns the AlphaClouds font.
     * @return the font
     */
    public BitmapFont getClouds(){
        return Clouds80;
    }

}
