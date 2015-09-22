package br.edu.unochapeco.crushinggrapes.comp;

import AndGraph.AGSprite;


public class Grape implements CompGame {
    private AGSprite grape;
    private boolean grapeType;

    public Grape(){}

    public Grape(AGSprite grape, boolean grapeType) {
        this.grape = grape;
        this.grapeType = grapeType;
    }

    public void setGrapeType(boolean grapeType) {
        this.grapeType = grapeType;
    }

    public boolean isGrapeGood() {
        return this.grapeType;
    }

    @Override
    public AGSprite getCompGame() {
        return this.grape;
    }

       @Override
    public void setSprite(AGSprite sprite) {
        this.grape = sprite;
    }
}
