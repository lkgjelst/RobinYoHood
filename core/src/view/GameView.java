package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.progark.game.Controller;

import model.BodyModel;
import model.InputController;

public class GameView implements Screen {

    private final Box2DDebugRenderer debugRenderer;
    private final OrthographicCamera cam;
    private final BodyModel model;

    private Stage stage = new Stage(new ScreenViewport());

    private Controller parent;
    private InputProcessor inputController;

    public GameView(Controller controller) {
        parent = controller;

        // Model
        cam = new OrthographicCamera(32, 24);
        inputController = new InputController();
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
        model = new BodyModel((InputController) inputController);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputController);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();

        // Create new table that fills the screen -> Table added to stage
        Table table = new Table();
        table.setFillParent(true);
        // TODO: Add a cool background (iterate through 3 and pick random in asset manager)
        stage.addActor(table);

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
