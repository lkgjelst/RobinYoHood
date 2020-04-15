package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.progark.game.Controller;

import model.BodyModel;
import model.InputController;

public class GameView implements Screen {

    private final Box2DDebugRenderer debugRenderer;
    private final OrthographicCamera cam;
    private final BodyModel model;

    private Label infoLabel;

    private Stage stage = new Stage(new ScreenViewport());

    private Controller parent;
    private InputProcessor inputController;

    // Midlertidig til MenuView for testing
    // TODO: Back button - maybe should look different and also endGame
    private Skin skin = new Skin(Gdx.files.internal("skin/dark-hdpi/Holo-dark-hdpi.json"));
    private final TextButton backButton = new TextButton("Back", skin); // Sjekk for en annen / mindre knapp i dette skinet

    public GameView(Controller controller) {
        parent = controller;
        stage.draw();

        // Create new table that fills the screen -> Table added to stage
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        // Info
        infoLabel = new Label("Bruk piltastene for å bevege deg - knapp kjem", skin);

        // Model
        cam = new OrthographicCamera(32, 24);
        inputController = new InputController();
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
        model = new BodyModel((InputController) inputController);

        // Stage and buttons
        // TODO: Add a cool background (iterate through 3 and pick random in asset manager)

        // Button
        // TODO: Where to be located?
        table.row().pad(10,0,0,10);
        table.add(infoLabel);
        table.row().pad(-50,0,0,10);
        table.add(backButton);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputController);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();

        backButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                parent.changeScreen(Controller.MENU);
                return false;
            }
        });

    }

    // TODO: INCLUDE IN VIEW
    @Override
    public void render(float delta) {
        model.logicStep(delta);
        Gdx.gl.glClearColor(0.34f,0.51f,0.72f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(model.world, cam.combined);
    }

    @Override
    public void resize(int width, int height) {

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

    }
}
