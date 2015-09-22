package br.edu.unochapeco.crushinggrapes;

import AndGraph.AGGameManager;
import AndGraph.AGInputManager;
import AndGraph.AGScene;
import AndGraph.AGScreenManager;
import AndGraph.AGSprite;


public class GameOver extends AGScene {

    private AGSprite fim, voltar, nono, uva;
    private AGSprite[] vetFase;
    private AGSprite[] vetScore;
    private GameFase gameFase;

    public GameOver(AGGameManager pManager, GameFase gameFase) {
        super(pManager);
        this.gameFase = gameFase;
    }


    @Override
    public void init() {
        fim = createSprite(R.drawable.gameover, 1,1);
        fim.setScreenPercent(100, 100);
        fim.vrPosition.fX = AGScreenManager.iScreenWidth / 2;
        fim.vrPosition.fY = AGScreenManager.iScreenHeight / 2;

        voltar = createSprite(R.drawable.voltar, 1,1);
        voltar.setScreenPercent(80, 15);
        voltar.vrPosition.fX = AGScreenManager.iScreenWidth / 2;
        voltar.vrPosition.fY = voltar.getSpriteHeight() / 2;


        nono = createSprite(R.drawable.nono, 1,1);
        nono.setScreenPercent(30, 10);
        nono.vrPosition.fX = (AGScreenManager.iScreenWidth / 2) - 20;
        nono.vrPosition.fY = (AGScreenManager.iScreenHeight / 2) + 20;


        vetFase = new AGSprite[2];
        for (int i = 0; i < vetFase.length; i++) {
            vetFase[i] = createSprite(R.drawable.fonte, 4, 4);
            vetFase[i].setScreenPercent(13, 10);

            for (int j = 0; j <= 9; j++ )
                vetFase[i].addAnimation(1, false, j);

            if (i == 0)
                vetFase[i].vrPosition.fX = (nono.vrPosition.fX + nono.getSpriteWidth() / 2) + 30;
            else
                vetFase[i].vrPosition.fX = vetFase[i - 1].vrPosition.fX + vetFase[i].getSpriteWidth() / 2;

            vetFase[i].vrPosition.fY = nono.vrPosition.fY - 5;
        }

        uva = createSprite(R.drawable.uva_fim, 1,1);
        uva.setScreenPercent(30, 10);
        uva.vrPosition.fX = (AGScreenManager.iScreenWidth / 2) - 20;
        uva.vrPosition.fY = (nono.vrPosition.fY - nono.getSpriteHeight() / 2) - (uva.getSpriteHeight() / 2) - 10;

        vetScore = new AGSprite[3];
        for (int i = 0; i < vetScore.length; i++) {
            vetScore[i] = createSprite(R.drawable.fonte, 4, 4);
            vetScore[i].setScreenPercent(13, 10);

            for (int j = 0; j <= 9; j++ )
                vetScore[i].addAnimation(1, false, j);

            if (i == 0)
                vetScore[i].vrPosition.fX = (uva.vrPosition.fX + uva.getSpriteWidth() / 2) + 30;
            else
                vetScore[i].vrPosition.fX = vetScore[i - 1].vrPosition.fX + vetScore[i].getSpriteWidth() / 2;

            vetScore[i].vrPosition.fY = uva.vrPosition.fY - 5;
        }

        atualizarContador(vetFase, gameFase.getLevelNow());
        atualizarContador(vetScore, gameFase.getScoreFinal());
    }

    private void atualizarContador(AGSprite[] ref, int n) {
        for (int i = ref.length - 1; i >= 0 && n > 0; i--, n /= 10)
            ref[i].setCurrentAnimation(n % 10);
    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {

        if (AGInputManager.vrTouchEvents.screenClicked() &&
                voltar.collide(AGInputManager.
                        vrTouchEvents.getLastPosition())){

            Som.somIntro();
            vrGameManager.setCurrentScene(1);
            return;

        }

    }

}
