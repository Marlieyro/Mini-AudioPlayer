package audioPlayerComp.PlayerComponents;

import audioPlayerComp.PlayerComponent;
import audioPlayerComp.SimplePlayer;
import dataStorage.AudioTrack;

import java.util.Collections;
import java.util.List;

public class Shuffler implements PlayerComponent {
    // Щоб можна блуо видалити клас, бо GC його не забере
   // private List<AudioTrackInformation> modifiedList;

    public Shuffler(){}

    public void shuffle(List<AudioTrack> currentList){
        Collections.shuffle(currentList);
    }


    @Override
    public void execute(SimplePlayer.PlayerContext playerContext) {
        shuffle(playerContext.getModifiedList());
    }
}
