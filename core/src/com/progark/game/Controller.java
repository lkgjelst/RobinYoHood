package com.progark.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Menu;
import java.util.Set;

import model.Preferences;
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
	private Preferences preferences;

	public final static int MENU = 0;
	public final static int SETTINGS = 1;
	public final static int GAME = 2;
	public final static int ENDGAME = 3;

	@Override
	public void create() {
		// Sets the default screen when the application is loaded
		loadingView = new LoadingView(this);
		setScreen(loadingView);
		preferences = new Preferences();
	}

	public Preferences getPreferences(){
		return this.preferences;
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
