package dataStorage;

import progPatterns.Subject;
import progPatterns.Subscriber;
import java.util.ArrayList;
import java.util.List;

/** Class description
* Цей клас працює як абсолютний каталог пісень, тобто
* клас який зберігає УСІ пісні які були колись додані.
*
* Зберігання відбувається в чіткому порядку, залежить від
* того коли саме додана пісня, тобто найновіші в кінці списку.
*
* Реалізація: (тут про поля, методи імплементації, для чого вони є і що роблять)
* - Клас реалізує патерн Observer. Subject з типом того що буде передаватись підписникам.
* Відповідно підписники мають мати тип такий що і Subject, щоб мати змогу приянйти дані.
* - Клас реалізує патерн Singleton. Оскільки він не має мати більше одного екземпляра.
* Одного екземпляра цілком достатньо, бо зберігається все в одній колекції.
*
* Доробити:
* - Додати клас який би зберігав List, serialization, або запис у файл.
*
* **/

                            // Можливо парто передавати List, а не суто єдиний трек.
public class AudioCatalog implements Subject<AudioTrack>{

    // Сам список пісень
    private final List<AudioTrack> catalog = new ArrayList<>();
    // Список підписників на оновлення, коли добавляється нова пісня, усі підписники про це знають
    private final List<Subscriber<AudioTrack>> subscribers = new ArrayList<>();

    // Приватний конструктор, бо клас реалізує Singleton
    private AudioCatalog(){}

    /* Повертає список пісень які зараз є у каталозі, повертає новий список, щоб
    не можна було змінити оригінальний каталог.*/
    public List<AudioTrack> getCatalog(){
        return new ArrayList<>(catalog);
    }

    @Deprecated
    public AudioTrack getAudio(int position){
       return catalog.get(position);
    }
    @Deprecated
    public AudioTrack getAudio(String audioTitle){
        for (AudioTrack trackInformation : catalog){
            if (trackInformation.getTitle().equals(audioTitle)){
                return trackInformation;
            }
        }
        return null;
    }

    // Метод який викликається у Parser коли додається нова пісня.
    public void addToCatalog(AudioTrack audio){
        catalog.add(audio);

        /* Оповіщення усім PLayList що була додана нова пісня.
        Цю пісню отримують УСІ підписники */
        notifySubscribers(audio);
    }

    // Метод для того, щоб після читання з файлу встановлювалась колекція
    public void setCatalog(List<AudioTrack> savedList){
        catalog.addAll(savedList);
    }

    // Метод для видалення пісні з каталогу
    public void removeFromCatalog(AudioTrack audio){
        catalog.remove(audio);
    }

    /* Переставляє місцями треки у масиві, current це поточна позиція пісні, desire це бажана позиція,
    але чи потрібен цей метод в оригінальному каталозі, бо при передачі до плей листів зіб'ється отримання*/
    @Deprecated
    public void swapAudioPos(int current, int desire){}


    @Override
    public void subscribeOnMe(Subscriber<AudioTrack> subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unSubscribeFromMe(Subscriber<AudioTrack> subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(AudioTrack eventContent) {
        for (Subscriber<AudioTrack> subscriber : subscribers){
            subscriber.update(eventContent);
        }
    }

    // Initialization-on-demand holder - один із варіантів реалізації патерну Singleton.
    private static class ThisClassHolder{ private static final AudioCatalog INSTANCE = new AudioCatalog(); }
    public static AudioCatalog getInstance(){ return ThisClassHolder.INSTANCE; }

}

