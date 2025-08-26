import audioPlayerComp.*;
import dataSource.AudioParser;
import dataStorage.AudioCatalog;
import dataSource.SaveLoadJS;
import gui.swingGUI;
import playLists.PlayListFactory;

import javax.swing.*;
import java.io.IOException;


public class Main {
    @SuppressWarnings("uncheked")
    public static void main(String[] args) throws InterruptedException {


        SwingUtilities.invokeLater(() -> {
            swingGUI gui = new swingGUI();
            gui.setVisible(true);

            // Приклад використання методів для зміни стану
            gui.setSongTitle("Нова пісня - Виконавець");
            gui.setPlaying(true);
        });



       /* AudioCatalog audioCatalog = AudioCatalog.getInstance();
        System.out.println(audioCatalog.getCatalog());
        try {
           audioCatalog.setCatalog(SaveLoadJS.loadSongsList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(audioCatalog.getCatalog());
        System.out.println("------------");
        AudioParser parser = new AudioParser("C:\\Users\\crtwi\\Downloads\\Metallica - The Unforgiven II.mp3");
        parser.parseAudio();

        PlayListFactory playListFactory = new PlayListFactory();

        try {
            SaveLoadJS.saveSongsList(audioCatalog.getCatalog());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(audioCatalog.getCatalog());*/

       /* SimplePlayer simplePlayer = new SimplePlayer(playListFactory.createRawList());
        simplePlayer.setSongPositionInList(0);
        simplePlayer.play();
        Thread.currentThread().join();*/
    }
}
