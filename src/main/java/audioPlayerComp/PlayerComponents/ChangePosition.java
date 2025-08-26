package audioPlayerComp.PlayerComponents;

import audioPlayerComp.AudioPlayerInstance;
import audioPlayerComp.PlayerComponent;
import audioPlayerComp.SimplePlayer;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

public class ChangePosition implements PlayerComponent<ChangePosition> {
    private final AudioPlayerComponent audioPlayerComponent;

    public ChangePosition(AudioPlayerComponent component){
        this.audioPlayerComponent = component;
    }

    @Override
    public void execute(SimplePlayer.PlayerContext playerContext) {
        long newTime = (long) ((playerContext.getCurrentTimeLinePos() / 1000.0) * audioPlayerComponent.mediaPlayer().media().info().duration());
        audioPlayerComponent.mediaPlayer().controls().setTime(newTime);
    }

    @Override
    public ChangePosition getComponent() {
        return this;
    }
}
