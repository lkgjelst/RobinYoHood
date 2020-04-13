package view;

import com.badlogic.gdx.Screen;
import com.progark.game.Controller;

public class LoadingView implements Screen {

    private Controller parent;

    public LoadingView(Controller controller) {
        parent = controller;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        parent.changeScreen(Controller.MENU);
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
