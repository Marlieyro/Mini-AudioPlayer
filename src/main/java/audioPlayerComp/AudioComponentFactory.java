package audioPlayerComp;

import audioPlayerComp.PlayerComponents.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Клас який існує через мій дизайн програми.
 * Надає компоненти для роботи плеєра.
 * Компоненти це класи в яких є методи, які реалізують код VLCJ,
 * а також деякий логічний код.
 *
 * Цей клас не досконалий!!!
 * Можливо було краще інше архітектурне рішення, але я придумав це.
 */

class AudioComponentFactory implements ComponentSupplier {

    private final Map<TypeOfComponent,PlayerComponent> workers = new HashMap<>();

    /* Якщо виникає потреба в додатковому компоненті(пр: перехід в повний екран)
    це можна зробити через цей метод. */
    public void addComponent(TypeOfComponent type, PlayerComponent component){
        workers.put(type, component);
    }

    // Видаляє непотрібні компоненти.
    public void removeComponent(TypeOfComponent type){
        workers.remove(type);
    }

    private Map<TypeOfComponent,PlayerComponent> createDefaultComponents(){
       workers.put(TypeOfComponent.PLAY, new PlayComp(AudioPlayerInstance.getAudioPlayerComponent()));
       workers.put(TypeOfComponent.PAUSE, new PauseComp(AudioPlayerInstance.getAudioPlayerComponent()));
       workers.put(TypeOfComponent.STOP, new StopComp(AudioPlayerInstance.getAudioPlayerComponent()));
       workers.put(TypeOfComponent.CHANGE_VOLUME, new ChangeVolume(AudioPlayerInstance.getAudioPlayerComponent()));
       workers.put(TypeOfComponent.SHUFFLE, new Shuffler());
       workers.put(TypeOfComponent.CHANGE_POSITION, new ChangePosition(AudioPlayerInstance.getAudioPlayerComponent()));
       return workers;
    }

    @Override
    public Map<TypeOfComponent, PlayerComponent> createDefault() {
        return createDefaultComponents();
    }
}
