package model;

import com.badlogic.gdx.graphics.Texture;

public class AssetManager {

    // Asset manager api: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/assets/AssetManager.html
    // Loads and stores assets like textures, bitmapfonts, tile maps, sounds, music and so on.

    // TODO: Add music and sound effects
    // TODO: Fix preferences

    public final AssetManager manager = new AssetManager();

    // Textures
    public final String firstPlayerImage = "";
    public final String secondPlayerImage = "";

    // Sounds TODO: Add files @eivind
    public final String shootSound = "";
    public final String hitSound = "";
    public final String music = "";

    public void queueAddImages() {
        // manager.load(firstPlayerImage, Texture.class);
    }
}
