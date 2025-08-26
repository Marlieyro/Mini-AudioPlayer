package dataSource;

import dataStorage.AudioCatalog;
import dataStorage.AudioTrack;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.media.Media;
import uk.co.caprica.vlcj.media.MediaParsedStatus;
import uk.co.caprica.vlcj.media.Meta;

/*Class description
* Клас для парсингу пісні, шлях до пісні передається у конструкторі.
* Клас по суті є immutable, він приймає шлях, працює з шляхом і видаляється JVM.
*
* Недоліки:
* - Напряму залежний від шляху, який передають в конструкторі
* - Залежний від класу AudioCatalog
* - Передає інформацію про трек зразу після парсингу,
* можливо варто розглянути якийсь модифікований Observer
* - Обробляє тільки один трек за раз
* - Не досконале використання бібліотеки VLCJ
* - Потрібен метод для добавлення в каталог, для прозорої логіки*/

public class AudioParser {
    private final String pathToAudio;
    private final Media media;


    public AudioParser(String pathToAudio){
        this.pathToAudio = pathToAudio;
        MediaPlayerFactory factory = new MediaPlayerFactory();
        this.media = factory.media().newMedia(pathToAudio);

    }

    public void parseAudio(){
        // Повертаю bool для подальшого дебагу.
        boolean status =  media.parsing().parse();
        System.out.println(status);

        // Цей цикл варто змінити
        while (media.parsing().status() != MediaParsedStatus.DONE){
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Показує всі дані які доступні як MetaData у файлі, використовую для дебагу.
        for (Meta meta : Meta.values()){
            System.out.println(media.meta().get(meta));
        }

        // Зберігаємо цю пісню у нашу папку
        SaveSongToPrjDirectory.copyToPath(pathToAudio);

        /* Для того, щоб не робити Observer прямо в цьому методі я реагую
         на додавання пісні, щоб зразу її в каталог.

         Варто зробити метод, щоб було легше бачити логіку. */
        AudioCatalog.getInstance().addToCatalog(getAudioTrackInformation());
    }

    // Можливо тут варто було б використати патерн Builder.
    public AudioTrack getAudioTrackInformation(){
        String title = media.meta().get(Meta.TITLE);
        String artist = media.meta().get(Meta.ARTIST);
        long duration = media.info().duration();
        return new AudioTrack(title, artist, duration, pathToAudio);
    }

}