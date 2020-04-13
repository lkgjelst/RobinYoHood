package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.progark.game.Controller;

import model.BodyModel;

public class GameView implements Screen {

    private final Box2DDebugRenderer debugRenderer;
    private final OrthographicCamera cam;
    private final BodyModel model;
    private Controller parent;

    public GameView(Controller controller) {
        parent = controller;

        // Model
        model = new BodyModel();
        cam = new OrthographicCamera(32, 24);
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
    }

    @Override
    public void show() {

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
