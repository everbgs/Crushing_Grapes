package br.edu.unochapeco.crushinggrapes;

import AndGraph.AGGameManager;
import AndGraph.AGScene;
import AndGraph.AGScreenManager;
import AndGraph.AGSoundManager;
import AndGraph.AGSprite;


public class IntroScene extends AGScene {
    private AGSprite nonoBepi;
    private int estadoSprite = 0;
    private long inicio;

    public IntroScene(AGGameManager pManager) {
        super(pManager);
    }

    @Override
    public void init() {
        setSceneBackgroundColor(1f, 1f, 1f);
        nonoBepi = createSprite(R.drawable.nono, 1, 1);
        nonoBepi.fadeIn(1000);
        inicio = System.currentTimeMillis();

        nonoBepi.vrPosition.fX = AGScreenManager.iScreenWidth / 2;
        nonoBepi.vrPosition.fY = AGScreenManager.iScreenHeight / 2;

        nonoBepi.setScreenPercent(60, 40);

        Som.somIntro();
    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        if (estadoSprite == 0) {
            long intervalo = System.currentTimeMillis()-inicio;
            if (nonoBepi.fadeEnded() && intervalo > 4000) {
                estadoSprite = 1;
                nonoBepi.fadeOut(1000);
            }
        } else {
            if (nonoBepi.fadeEnded()) {
                vrGameManager.setCurrentScene(1);
            }
        }
    }
}
