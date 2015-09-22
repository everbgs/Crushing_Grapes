package br.edu.unochapeco.crushinggrapes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import AndGraph.AGGameManager;
import AndGraph.AGInputManager;
import AndGraph.AGScene;
import AndGraph.AGScreenManager;
import AndGraph.AGSprite;
import AndGraph.AGTimer;

import br.edu.unochapeco.crushinggrapes.comp.Grape;


public class Game extends AGScene {
    private AGTimer timerCreateGrape;
    private AGTimer timerSomAleatorio;
    private List<Grape> listUvas;
    private Random sorteioPosUva;
    private Random sorteioTipoUva;
    private int placar;
    private boolean escolher;
    private GameFase fase;

    private AGSprite[] vetFase;
    private AGSprite[] vetScore;
    private AGSprite[] vetScoreAtual;


    public Game(AGGameManager pManager, GameFase gameFase) {
        super(pManager);
        fase = gameFase;
    }

    @Override
    public void init() {
        listUvas = new ArrayList<Grape>();

        AGSprite fundo = createSprite(R.drawable.pipa, 1,1);
        Som.somJogo();

        fundo.setScreenPercent(100, 100);
        fundo.vrPosition.fX = AGScreenManager.iScreenWidth / 2;
        fundo.vrPosition.fY = AGScreenManager.iScreenHeight / 2;

        timerSomAleatorio = new AGTimer();
        timerSomAleatorio.restart(20 * 1000); //20 segundos

        timerCreateGrape = new AGTimer();
        timerCreateGrape.restart(500);

        this.sorteioPosUva = new Random();
        this.sorteioTipoUva = new Random();

        placar = 0;
        escolher = false;

        this.fase.init(20, 3);
        this.fase.setVelocidadeUva(7);
        this.fase.nextFase();


        vetFase = new AGSprite[2];
        for (int i = 0; i < vetFase.length; i++) {
            vetFase[i] = createSprite(R.drawable.fonte, 4, 4);
            vetFase[i].bAutoRender = false;
            vetFase[i].setScreenPercent(13, 10);

            for (int j = 0; j <= 9; j++ )
                vetFase[i].addAnimation(1, false, j);

            if (i == 0)
                vetFase[i].vrPosition.fX = vetFase[i].getSpriteWidth() / 2;
            else
                vetFase[i].vrPosition.fX = vetFase[i - 1].vrPosition.fX + vetFase[i].getSpriteWidth() / 2;

            vetFase[i].vrPosition.fY = AGScreenManager.iScreenHeight - vetFase[i].getSpriteHeight() / 2;
        }

        vetScore = new AGSprite[3];
        for (int i = 0; i < vetScore.length; i++) {
            vetScore[i] = createSprite(R.drawable.fonte, 4, 4);
            vetScore[i].bAutoRender = false;
            vetScore[i].setScreenPercent(13, 10);

            for (int j = 0; j <= 9; j++ )
                vetScore[i].addAnimation(1, false, j);

            if (i == 0)
                vetScore[i].vrPosition.fX =  AGScreenManager.iScreenWidth - vetScore[i].getSpriteWidth() / 2;
            else
                vetScore[i].vrPosition.fX = vetScore[i - 1].vrPosition.fX - vetScore[i].getSpriteWidth() / 2;

            vetScore[i].vrPosition.fY = AGScreenManager.iScreenHeight - vetScore[i].getSpriteHeight() / 2;
        }

        vetScoreAtual = new AGSprite[3];
        for (int i = 0; i < vetScore.length; i++) {
            vetScoreAtual[i] = createSprite(R.drawable.fonte2, 4, 4);
            vetScoreAtual[i].bAutoRender = false;
            vetScoreAtual[i].setScreenPercent(13, 10);

            for (int j = 0; j <= 9; j++ )
                vetScoreAtual[i].addAnimation(1, false, j);

            if (i == 0)
                vetScoreAtual[i].vrPosition.fX =  AGScreenManager.iScreenWidth - vetScoreAtual[i].getSpriteWidth() / 2;
            else
                vetScoreAtual[i].vrPosition.fX = vetScoreAtual[i - 1].vrPosition.fX - vetScoreAtual[i].getSpriteWidth() / 2;

            vetScoreAtual[i].vrPosition.fY = vetScore[0].vrPosition.fY - (vetScoreAtual[i].getSpriteHeight() / 2) - 2;
        }

        atualizarContador(vetFase, this.fase.getLevelNow(), false);
        atualizarContador(vetScore, this.fase.getScoreFase(), true);
        atualizarContador(vetScoreAtual, 0, true);
    }

    @Override
    public void render() {
        super.render();

        for (int i = 0; i < vetFase.length; i++) vetFase[i].render();

        for (int i = 0; i < vetScore.length; i++) vetScore[i].render();

        for (int i = 0; i < vetScoreAtual.length; i++) vetScoreAtual[i].render();
    }

    private void atualizarContador(AGSprite[] ref, int n, boolean inicio) {
        if (n == 0) {
            for (int i = 0; i < ref.length; i++)
                ref[i].setCurrentAnimation(0);
            return;
        }

        if (inicio) {
            for (int i = 0; i < ref.length && n > 0; i++) {
                ref[i].setCurrentAnimation(n % 10);
                n /= 10;
            }

        } else {

            for (int i = ref.length - 1; i >= 0 && n > 0; i--) {
                ref[i].setCurrentAnimation(n % 10);
                n /= 10;
            }

        }
    }

    private void executaSomAleatorio(){
         timerSomAleatorio.update();
         if(timerSomAleatorio.isTimeEnded()){
             Som.somAleatorio();
            timerSomAleatorio.restart();
         }
    }

    @Override
    public void restart() {
        if (!Som.isSomJogoPlaying())
            Som.somJogo();
    }

    @Override
    public void stop() {
        Som.stopSomJogo();
    }

    private float getRandomUvaFX(float widthGrape) {
        float min = widthGrape/2;
        float max = AGScreenManager.iScreenWidth - min;

        return min + this.sorteioTipoUva.nextFloat() * (max - min);
    }

    private boolean isGoodGrape() {
        return this.sorteioTipoUva.nextBoolean();
    }

    private void criarUvasRandom() {
        timerCreateGrape.update();

        if (timerCreateGrape.isTimeEnded()) {
            AGSprite aux;

            if (escolher) {

                escolher = false;

                for (Grape g : listUvas) {
                    aux = g.getCompGame();
                    if (aux.bRecycled) {

                        aux.bRecycled = false;
                        aux.bVisible = true;
                        aux.vrPosition.fX = getRandomUvaFX(aux.getSpriteWidth());
                        aux.vrPosition.fY = AGScreenManager.iScreenHeight + aux.getSpriteHeight() / 2;

                        timerCreateGrape.restart();
                        return;
                    }
                }
            }

            escolher = true;
            boolean grapeGood = isGoodGrape();
            if (grapeGood)
                aux = createSprite(R.drawable.uva, 1, 1);
            else
                aux = createSprite(R.drawable.estragada, 1, 1);

            aux.setScreenPercent(30, 10);
            aux.vrPosition.fX = getRandomUvaFX(aux.getSpriteWidth());
            aux.vrPosition.fY = AGScreenManager.iScreenHeight  + aux.getSpriteHeight() / 2;

            listUvas.add(new Grape(aux, grapeGood));

           timerCreateGrape.restart();
        }
    }

    private void moverUva() {
        AGSprite aux;
        for (Grape g: listUvas) {
            aux = g.getCompGame();

            if (aux.bRecycled) continue;

            aux.vrPosition.fY -= fase.getVelocidaUva();

            if (aux.vrPosition.fY <= -(aux.getSpriteHeight() / 2)) {
                aux.bRecycled = true;
                if (g.isGrapeGood()) {
                    this.placar -= 3;
                    this.fase.decScore(3);
                    Som.somFalha();
                    atualizarContador(vetScoreAtual, this.placar, true);
                }
            }
        }
    }

    private void tratarUvasEsmagadas() {
        if (AGInputManager.vrTouchEvents.screenClicked()) {

            AGSprite aux;

            for (Grape g : listUvas) {
                aux = g.getCompGame();

                if (aux.bRecycled) continue;

                if (aux.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                    aux.bRecycled = true;
                    aux.bVisible = false;

                    if (g.isGrapeGood()) {
                        this.placar++;
                        this.fase.incScore(1);
                    } else {
                        this.placar--;
                        this.fase.decScore(3);
                    }
                    atualizarContador(vetScoreAtual, this.placar, true);
                    break;
                }
            }
        }
    }



    @Override
    public void loop() {
        if (fase.isNextFase(this.placar)) {
            this.placar = 0;
            fase.nextFase();
            atualizarContador(vetFase, this.fase.getLevelNow(), false);
            atualizarContador(vetScore, this.fase.getScoreFase(), true);
            atualizarContador(vetScoreAtual, 0, true);
        }

        if (this.placar < 0) {
            Som.stopSomJogo();

            vrGameManager.setCurrentScene(4);
            return;
        }

        criarUvasRandom();
        moverUva();
        tratarUvasEsmagadas();
        executaSomAleatorio();
    }



}
