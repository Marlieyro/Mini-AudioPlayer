package audioPlayerComp;

/**
 * Інтерфейс по суті надає гарантію, що у компонента 100% буде метод який
 * запускає роботу компонента.
 */

// Public тут це херновий дизайн.
public interface PlayerComponent{

    void execute(SimplePlayer.PlayerContext playerContext);



}
