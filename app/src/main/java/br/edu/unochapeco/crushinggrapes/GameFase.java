package br.edu.unochapeco.crushinggrapes;


public class GameFase {
    private int velocidaUva;
    private int scoreTotal;
    private int scoreTotalMaior;
    private int scoreFase;
    private int levelNow;
    private int incScore;
    private int incVelocidade;

    public void init(int incScore, int incVelocidade) {
        this.incScore = incScore;
        this.incVelocidade = incVelocidade;
        this.levelNow = 0;
        this.scoreTotal = 0;
        this.scoreFase = 0;
        this.scoreTotalMaior = 0;
    }

    public void setVelocidadeUva(int v) {
        this.velocidaUva = v;
    }

    public int getVelocidaUva() {
        return this.velocidaUva;
    }

    public void nextFase() {
        ++this.levelNow;
        this.velocidaUva += this.incVelocidade;
        this.scoreFase += this.incScore;
    }

    public boolean isNextFase(int score) {
        return this.scoreFase <= score;
    }

    public int getLevelNow() { return this.levelNow; }

    public int getScoreFase() { return this.scoreFase; }

    public int getScoreFinal() {
        return this.scoreTotalMaior;
    }

    public void incScore(int n) {
        this.scoreTotal += n;
        if (this.scoreTotal > this.scoreTotalMaior)
            this.scoreTotalMaior = scoreTotal;
    }

    public void decScore(int n) {
        this.scoreTotal -= n;
        if (this.scoreTotal < 0) this.scoreTotal = 0;
    }

}
