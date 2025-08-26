package audioPlayerComp.PlayerComponents;


import audioPlayerComp.PlayerComponent;

import audioPlayerComp.SimplePlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

public class PlayComp implements PlayerComponent {
    private AudioPlayerComponent component;

    public PlayComp(AudioPlayerComponent audioPlayerComponent){
        this.component = audioPlayerComponent;
    }

    public void playSong(String path){
        System.out.println("Path to song = " + path);
        component.mediaPlayer().events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void finished(MediaPlayer mediaPlayer){
             releaseResources();
            }
        });

        Thread thread = new Thread(() -> {
            component.mediaPlayer().media().play(path);
            System.out.println("Ready to play " + path);
        });
        thread.start();
        /*component.mediaPlayer().media().play(path);
        System.out.println("Ready to play " + path);*/
    }

    private void releaseResources(){
        component.mediaPlayer().submit(() -> {
            component.mediaPlayer().release();
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

    @Override
    public void execute(SimplePlayer.PlayerContext playerContext) {
        playSong(playerContext.getPathToSong());
    }
}
