package audioPlayerComp.PlayerComponents;

import audioPlayerComp.PlayerComponent;
import audioPlayerComp.SimplePlayer;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

public class PauseComp implements PlayerComponent {
    private AudioPlayerComponent audioPlayerComponent;

    public PauseComp(AudioPlayerComponent audioPlayerComponent){
        this.audioPlayerComponent = audioPlayerComponent;
    }

    @Override
    public void execute(SimplePlayer.PlayerContext playerContext) {
        audioPlayerComponent.mediaPlayer().controls().pause();
    }
}
