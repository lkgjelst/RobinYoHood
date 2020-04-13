package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.progark.game.Controller;
import model.*;

public class SettingsView implements Screen {

    private Controller parent;
    private Stage stage = new Stage(new ScreenViewport());

    // Labels
    private Label musicOnOffLabel;
    private Label soundOnOffLabel;

    // Skin
    private Skin skin = new Skin(Gdx.files.internal("skin/dark-hdpi/Holo-dark-hdpi.json"));

    private final CheckBox musicCheckbox = new CheckBox(null, skin);
    private final CheckBox soundCheckbox = new CheckBox(null, skin);
    private final TextButton backButton = new TextButton("Back", skin); // Sjekk for en annen / mindre knapp i dette skinet

    public SettingsView(Controller controller) {
        parent = controller;
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();

        // Create new table that fills the screen -> Table added to stage
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        musicCheckbox.setChecked( parent.getPreferences().getMusicEnabled());
        soundCheckbox.setChecked( parent.getPreferences().getSoundEnabled());

        musicOnOffLabel = new Label("Music", skin);
        soundOnOffLabel = new Label("Effects", skin);

        // Cheap way to add background, TODO: fix later using asset manager
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background.png"))));

        // Table generated :D
        table.row().pad(350,0,0,10);
        table.add(musicOnOffLabel).left();
        table.add(musicCheckbox);
        table.row().pad(10,0,0,10);
        table.add(soundOnOffLabel).left();
        table.add(soundCheckbox);
        // Back button
        table.row().pad(50, 0, 0, 10);
        table.add(backButton).left();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        musicCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = musicCheckbox.isChecked();
                parent.getPreferences().setMusicEnabled( enabled );
                return false;
            }
        });

        backButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                parent.changeScreen(Controller.MENU);
                return false;
            }
        });
    }

    // TODO: IMPLEMENT IN ABSTRACT CLASS
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.34f,0.51f,0.72f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    // TODO: IMPLEMENT IN ABSTRACT CLASS
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    // TODO: POSSIBLY IMPLEMENT IN ABSTRACT CLASS
    @Override
    public void dispose() {
        stage.clear();
    }
}
