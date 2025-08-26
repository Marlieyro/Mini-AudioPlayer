package audioPlayerComp;

import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

/**
 * Клас псевдо Singleton. Надає компонент з яким працює плеєр.
 * Це потрібно через те, що я використовую бібліотеку VLCJ.
 * Де цей клас є основою роботи інших класів.
 *
 * Створений, бо можна обійтись тільки один таким класом (напевно :)).
 */

public class AudioPlayerInstance {

    private static final AudioPlayerComponent audioPlayerComponent = new AudioPlayerComponent();

    private AudioPlayerInstance(){}

    public static AudioPlayerComponent getAudioPlayerComponent(){
        return audioPlayerComponent;
    }

}
