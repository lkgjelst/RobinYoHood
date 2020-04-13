package model;

import com.badlogic.gdx.Gdx;

public class Preferences  {

    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_SOUND_ENABLED = "sound.enabled";
    private static final String PREFS_NAME = "b2dtut";

    protected com.badlogic.gdx.Preferences getPrefs() {
        return Gdx.app.getPreferences(PREFS_NAME);
    }

    public boolean getMusicEnabled() {
        return getPrefs().getBoolean(PREF_MUSIC_ENABLED, true);
    }

    public void setMusicEnabled(boolean enabled) {
        getPrefs().putBoolean(PREF_MUSIC_ENABLED, enabled);
        getPrefs().flush();
    }
    public boolean getSoundEnabled() {
        return getPrefs().getBoolean(PREF_SOUND_ENABLED, true);
    }

    public void setSoundEnabled(boolean enabled) {
        getPrefs().putBoolean(PREF_SOUND_ENABLED, true);
        getPrefs().flush();
    }

}
