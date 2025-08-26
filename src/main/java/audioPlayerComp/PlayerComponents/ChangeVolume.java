package audioPlayerComp.PlayerComponents;

import audioPlayerComp.PlayerComponent;
import audioPlayerComp.SimplePlayer;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

public class ChangeVolume implements PlayerComponent {
    private final AudioPlayerComponent audioPlayerComponent;
    public ChangeVolume(AudioPlayerComponent audioPlayerComponent){
        this.audioPlayerComponent = audioPlayerComponent;
    }
    @Override
    public void execute(SimplePlayer.PlayerContext playerContext) {
         audioPlayerComponent.mediaPlayer().audio().setVolume(playerContext.getVolumeLevel());
    }
}
