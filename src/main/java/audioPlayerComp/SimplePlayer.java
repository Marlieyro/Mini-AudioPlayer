package audioPlayerComp;

import dataStorage.AudioTrack;
import playLists.PlayList;
import progPatterns.Subscriber;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimplePlayer {
    private PlayerContext playerContext = new PlayerContext();
    Map<TypeOfComponent, PlayerComponent> workers = new AudioComponentFactory().createDefault();

    public SimplePlayer(PlayList playList){
        playerContext.setPlayList(playList);
        playerContext.setModifiedList(new ArrayList<>(playList.getPlayList()));
        playerContext.setOriginalList(playList.getPlayList());
    }

    public void setSongPositionInList(int position){
        playerContext.setCurrentTrackPos(position);
        playerContext.setPathToSong();
    }

   public void play(){
       PlayerComponent component = workers.get(TypeOfComponent.PLAY);
       component.execute(playerContext);
    }
   public void pause(){
        PlayerComponent component = workers.get(TypeOfComponent.PAUSE);
        component.execute(playerContext);
    }
   public void stop(){
        PlayerComponent component = workers.get(TypeOfComponent.STOP);
        component.execute(playerContext);
    }
   public void nextSong(){
        if (playerContext.currentTrackPos == playerContext.getOriginalList().size() -1 ){
            playerContext.currentTrackPos = 0;
        } else {
            playerContext.currentTrackPos++;
        }
        playerContext.setPathToSong();
        play();
    }
   public void prevSong(){
        if (playerContext.currentTrackPos == 0){
            playerContext.currentTrackPos = playerContext.getOriginalList().size() -1;
        } else {
            playerContext.currentTrackPos--;
        }
        playerContext.setPathToSong();
        play();
    }
   public void changeVolume(int value){
        PlayerComponent component = workers.get(TypeOfComponent.CHANGE_VOLUME);
        playerContext.setVolumeLevel(value);
        component.execute(playerContext);
    }

    public void changeTrackPos(int value){
        PlayerComponent component = workers.get(TypeOfComponent.CHANGE_POSITION);
        playerContext.setCurrentTimeLinePos(value);
        component.execute(playerContext);
    }

    void shuffle(){
        PlayerComponent component = workers.get(TypeOfComponent.SHUFFLE);
        component.execute(playerContext);
    }


    public static class PlayerContext implements Subscriber<AudioTrack> {
        private PlayList playList;

        private final AudioPlayerComponent audioPlayerComponent = AudioPlayerInstance.getAudioPlayerComponent();

        private List<AudioTrack> originalList;
        private List<AudioTrack> modifiedList;
        private int currentTrackPos = 0;
        private String pathToSong;
        private int volumeLevel = 0;
        private int currentTimeLinePos = 0;

        private PlayerContext(){}

        public AudioPlayerComponent getAudioPlayerComponent() {
            return audioPlayerComponent;
        }

        public int getCurrentTimeLinePos() {
            return currentTimeLinePos;
        }

        public void setCurrentTimeLinePos(int currentTimeLinePos) {
            this.currentTimeLinePos = currentTimeLinePos;
        }

        public int getVolumeLevel() {
            return volumeLevel;
        }

        public void setVolumeLevel(int volumeLevel) {
            this.volumeLevel = volumeLevel;
        }


        public String getPathToSong() {
            return pathToSong;
        }

        public void setPathToSong(String pathToSong) {

            this.pathToSong = pathToSong;
        }

        public void setPathToSong() {
            pathToSong = originalList.get(currentTrackPos).getAudioPath();
        }

        public PlayList getPlayList() {
            return playList;
        }

        public void setPlayList(PlayList playList) {
            this.playList = playList;
        }

        public List<AudioTrack> getOriginalList() {
            return originalList;
        }

        public void setOriginalList(List<AudioTrack> originalList) {
            this.originalList = originalList;
        }

        public int getCurrentTrackPos() {
            return currentTrackPos;
        }

        public void setCurrentTrackPos(int currentTrackPos) {
            this.currentTrackPos = currentTrackPos;
        }

        public List<AudioTrack> getModifiedList() {
            return modifiedList;
        }

        public void setModifiedList(List<AudioTrack> modifiedList) {
            this.modifiedList = modifiedList;
        }

        @Override
        public void update(AudioTrack eventContent) {
            modifiedList.add(eventContent);
        }
    }

}
