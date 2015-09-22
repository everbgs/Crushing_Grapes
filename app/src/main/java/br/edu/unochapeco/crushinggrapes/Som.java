package br.edu.unochapeco.crushinggrapes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import AndGraph.AGSoundManager;


public class Som {

    private static final String somIntro = "funiculi.mid";
    private static final String somJogo = "tarantella.mid";
    private static final String[] sonsAleatorios = {"barucha.mp3", "esqueceujapona.mp3", "tchucodenovo.mp3", "vaicarpir.mp3"};
    private static final String[] sonsFalha = {"porcocane.mp3", "sacramento.mp3", "vinho.mp3", "velho.mp3", "porco.mp3"};

    /**
     * Executa o som da intro.
     */
    public static void somIntro() {
        AGSoundManager.vrMusic.loadMusic(somIntro, true);
        AGSoundManager.vrMusic.play();
    }

    /**
     * Executa o som de fundo do jogo.
     */
    public static void somJogo() {
        AGSoundManager.vrMusic.loadMusic(somJogo, true);
        AGSoundManager.vrMusic.play();
    }

    /**
     * Para  o som de fundo
     */
    public static void stopSomJogo() {
        AGSoundManager.vrMusic.stop();
    }
    /**
     * Verifica se o som de fundo está tocando.
     */
    public static boolean isSomJogoPlaying() {
        return AGSoundManager.vrMusic.isPlaying();
    }

    /**
     * Executa um som aleatório.
     */
    public static void somAleatorio() {
        int som = AGSoundManager.vrSoundEffects.loadSoundEffect(getAleatorio(sonsAleatorios));
        AGSoundManager.vrSoundEffects.play(som);

    }

    /**
     * Executa um som de falha, quando deixa passar muitos cachos ou esmaga cacho estragado.
     */
    public static void somFalha() {
        int som = AGSoundManager.vrSoundEffects.loadSoundEffect(getAleatorio(sonsFalha));
        AGSoundManager.vrSoundEffects.play(som);
    }


    private static String getAleatorio(String[] array) {
        List<String> aleatorios = new ArrayList<>(Arrays.asList(array));
        aleatorios.addAll(Arrays.asList(array));
        aleatorios.addAll(Arrays.asList(array));
        aleatorios.addAll(Arrays.asList(array));

        Collections.shuffle(aleatorios);
        Random random = new Random();

        return aleatorios.get(random.nextInt(aleatorios.size() - 1));
    }

}
