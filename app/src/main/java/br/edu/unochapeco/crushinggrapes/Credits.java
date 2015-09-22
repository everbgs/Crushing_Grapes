package br.edu.unochapeco.crushinggrapes;

import AndGraph.AGGameManager;
import AndGraph.AGInputManager;
import AndGraph.AGScene;
import AndGraph.AGScreenManager;
import AndGraph.AGSprite;


public class Credits extends AGScene {

    private AGSprite sobre, voltar = null;

    public Credits(AGGameManager pManager) {
        super(pManager);
    }


    @Override
    public void init() {
        sobre = createSprite(R.drawable.sobre, 1,1);
        sobre.setScreenPercent(100, 100);
        sobre.vrPosition.fX = AGScreenManager.iScreenWidth / 2;
        sobre.vrPosition.fY = AGScreenManager.iScreenHeight / 2;

        voltar = createSprite(R.drawable.voltar, 1,1);
        voltar.setScreenPercent(80, 15);
        voltar.vrPosition.fX = AGScreenManager.iScreenWidth / 2;
        voltar.vrPosition.fY = AGScreenManager.iScreenHeight / 8;

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
            vrGameManager.setCurrentScene(1);
        }
        if (AGInputManager.vrTouchEvents.screenClicked() &&
                voltar.collide(AGInputManager.
                        vrTouchEvents.getLastPosition())){

            vrGameManager.setCurrentScene(1);
            return;

        }

    }
}
