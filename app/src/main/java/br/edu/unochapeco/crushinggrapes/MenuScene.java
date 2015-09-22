package br.edu.unochapeco.crushinggrapes;

import AndGraph.AGGameManager;
import AndGraph.AGInputManager;
import AndGraph.AGScene;
import AndGraph.AGScreenManager;
import AndGraph.AGSprite;


public class MenuScene extends AGScene {

    private AGSprite fundo, botaojogar, botaocredits, botaosair = null;

    public MenuScene(AGGameManager pManager) {
        super(pManager);
    }

    @Override
    public void init() {
        fundo = createSprite(R.drawable.fundomenu, 1,1);
        fundo.setScreenPercent(100, 100);
        fundo.vrPosition.fX = AGScreenManager.iScreenWidth / 2;
        fundo.vrPosition.fY = AGScreenManager.iScreenHeight / 2;

        botaocredits = createSprite(R.drawable.btnsobre, 1, 1);
        botaocredits.setScreenPercent(90, 20);
        botaocredits.vrPosition.fX = AGScreenManager.iScreenWidth / 2;
        botaocredits.vrPosition.fY = AGScreenManager.iScreenHeight / 2;

        botaojogar = createSprite(R.drawable.btnjogar, 1, 1);
        botaojogar.setScreenPercent(90, 20);
        botaojogar.vrPosition.fX = AGScreenManager.iScreenWidth / 2;
        botaojogar.vrPosition.fY = botaocredits.vrPosition.fY + botaojogar.getSpriteHeight();

        botaosair = createSprite(R.drawable.btnsair, 1, 1);
        botaosair.setScreenPercent(90, 20);
        botaosair.vrPosition.fX = AGScreenManager.iScreenWidth / 2;
        botaosair.vrPosition.fY = botaocredits.vrPosition.fY - botaosair.getSpriteHeight();

    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        if (AGInputManager.vrTouchEvents.backButtonClicked()){
            vrGameManager.vrActivity.finish();
        }
        if (AGInputManager.vrTouchEvents.screenClicked() &&
                botaojogar.collide(AGInputManager.
                        vrTouchEvents.getLastPosition())){

            vrGameManager.setCurrentScene(3);
            return;
        }
        if (AGInputManager.vrTouchEvents.screenClicked() &&
                botaocredits.collide(AGInputManager.
                        vrTouchEvents.getLastPosition())){
            vrGameManager.setCurrentScene(2);
            return;
        }
        if (AGInputManager.vrTouchEvents.screenClicked() &&
                botaosair.collide(AGInputManager.vrTouchEvents.getLastPosition())){
            vrGameManager.vrActivity.finish();
        }

    }
}
