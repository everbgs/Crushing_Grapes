package br.edu.unochapeco.crushinggrapes;

import android.app.Activity;
import android.os.Bundle;

import AndGraph.AGGameManager;
import AndGraph.AGInputManager;


public class MainActivity extends Activity {

    private AGGameManager manager;
    private GameFase gameFase;

      
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameFase = new GameFase();

        manager = new AGGameManager(this, false);
        manager.addScene(new IntroScene(manager));
        manager.addScene(new MenuScene(manager));
        manager.addScene(new Credits(manager));
        manager.addScene(new Game(manager, gameFase));
        manager.addScene(new GameOver(manager, gameFase));


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AGInputManager.vrTouchEvents.bBackButtonClicked = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        manager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.release();
        manager = null;
    }
}
