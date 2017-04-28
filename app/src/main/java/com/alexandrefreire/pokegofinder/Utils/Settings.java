package com.alexandrefreire.pokegofinder.Utils;

/**
 * Created by Alexandre on 21/7/16.
 */
public class Settings {
    public static final String PREFS_NAME = "Settings.PREFS_NAME";

    public enum Environment{
        PRODUCTION, LOCALHOST, DEVELOPMENT
    }
    public static final Environment ENVIROMENT = Environment.PRODUCTION;
}
