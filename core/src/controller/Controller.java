package controller;

import com.badlogic.gdx.Game;

import view.GameOverView;
import view.GameView;
import view.LoadingView;
import view.MenuView;
import view.SettingsView;

public class Controller extends Game {

    private GameOverView gameOverView;
    private GameView gameView;
    private LoadingView loadingView;
    private MenuView menuView;
    private SettingsView settingsView;

    final static int MENU = 0;
    final static int SETTINGS = 1;
    final static int GAME = 2;
    final static int ENDGAME = 3;

    @Override
    public void create() {
        loadingView = new LoadingView(this);
        setScreen(loadingView);
    }

    public void changeScreen(int screen){
        switch(screen){
            case MENU:
                if(menuView == null) menuView = new MenuView(this);
                this.setScreen(menuView);
                break;
            case SETTINGS:
                if(settingsView == null) settingsView = new SettingsView(this);
                this.setScreen(settingsView);
                break;
            case GAME:
                if(gameView == null) gameView = new GameView(this);
                this.setScreen(gameView);
                break;
            case ENDGAME:
                if(gameOverView == null) gameOverView = new GameOverView(this);
                this.setScreen(gameOverView);
                break;
        }
    }

}
