package audioPlayerCompV2;

import dataStorage.AudioTrack;
import playLists.PlayList;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

import java.util.List;

/**
 * Опис до реалізації класу, перед реалізацією класу.
 *
 * Клас тримає в собі усі методи які відповідають за логіку плеєра
 * Реалізація цих методів відбувається прямо у цьому класі.
 * Якщо метод потребує додаткового методу, він позначається як additional.
 *
 * Клас приймає в себе інструменти бібліотеки, щоб мати низькорівневу взаємодію
 */

public class PlayerStation {

    // Змінна для отримання пісень, а також для можливості керування їхнім списком
    private PlayList playList;

    // Доступ до бібліотеки VLCJ
    private final AudioPlayerComponent lowLevelComponent;

    // Список для зміни списку треків
    private List<AudioTrack> modifiedListOfTracks;

    /**
     * Логічні змінні для роботи цього класу
     */
    // Відображає поточний трек у Списку, за допомогою індексу.
    private int currentTrackPos = 0;
    // Відображення гучності
    private int volumeLevel;





    public PlayerStation(PlayList playlist, AudioPlayerComponent lowLevelComponent) {
        this.playList = playlist;
        this.lowLevelComponent = lowLevelComponent;
    }

    public AudioPlayerComponent getLowLevelComponent(){
        return this.lowLevelComponent;
    }

    public PlayList getListOfTracksInteraction(){
        return this.playList;
    }

    public void play(){
        // Для дебагу
       System.out.println("Path to song = " + playList.getPlayList().get(currentTrackPos).getAudioPath());
        lowLevelComponent.mediaPlayer().events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void finished(MediaPlayer mediaPlayer){
                additionalPlayReleaseRes();
            }
        });

        Thread thread = new Thread(() -> {
            lowLevelComponent.mediaPlayer().media().play(playList.getPlayList().get(currentTrackPos).getAudioPath());
            // Для дебагу
            System.out.println("Ready to play " + playList.getPlayList().get(currentTrackPos).getAudioPath());
        });
        thread.start();
    }
    private void additionalPlayReleaseRes(){
        lowLevelComponent.mediaPlayer().submit(() -> {
            lowLevelComponent.mediaPlayer().release();
            System.exit(0);
        });

        /*new Thread(() -> {
            try {
                component.mediaPlayer().controls().stop();
                component.mediaPlayer().release();
            } catch (Exception e){

            }
        }).start();*/
    }

    public void pause(){
        lowLevelComponent.mediaPlayer().controls().pause();
    }

    public void changeVolume(int currentVolumeLvl){
        lowLevelComponent.mediaPlayer().audio().setVolume(currentVolumeLvl);
    }

    public void nextSong(){
        if (currentTrackPos == playList.getPlayList().size() - 1){
            currentTrackPos = 0;
        } else {
            currentTrackPos++;
        }
        play();
    }

    public void prevSong(){
        if (currentTrackPos == 0){
            currentTrackPos = playList.getPlayList().size() -1;
        } else {
            currentTrackPos--;
        }
        play();
    }

    public void stop(){
        lowLevelComponent.mediaPlayer().controls().stop();
    }
}