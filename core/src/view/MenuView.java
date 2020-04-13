package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.progark.game.Controller;

public class MenuView implements Screen {

    private Controller parent;
    private Stage stage = new Stage(new ScreenViewport());

    // Creating buttons for home screen
    Skin skin = new Skin(Gdx.files.internal("skin/dark-hdpi/Holo-dark-hdpi.json"));
    TextButton newGame = new TextButton("New Game", skin);
    TextButton preferences = new TextButton("Preferences", skin);
    TextButton exit = new TextButton("Exit", skin);

    public MenuView(Controller controller) {
        parent = controller;

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();

        // Create new table that fills the screen -> Table added to stage
        Table table = new Table();
        table.setFillParent(true);
        // Cheap way to add background, fix later using asset manager
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background.png"))));
        // table.setPosition(400, 0);      // Want to move the buttons to the right
        stage.addActor(table);

        table.row().pad(200, 0, 10, 0);
        table.add(newGame).fillX().uniform();
        table.row().pad(10, 0, 10, 0);
        table.add(preferences).fillX().uniform();
        table.row().pad(10, 0, 10, 0);
        table.add(exit).fillX().uniform();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(Controller.GAME);
            }
        });

        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(Controller.SETTINGS);
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

    @Override
    public void dispose() {
        stage.dispose();
    }
}
