package playLists;

import progPatterns.Subscriber;
import dataStorage.AudioCatalog;
import dataStorage.AudioTrack;
import java.util.Collections;
import java.util.List;

/**
 * Цей клас є контролером для класів PlayList.
 * З його допомогою відбуваються маніпуляції над плейлистом.
 * Має в собі стандарті методи взаємодії з Collection.
 *
 * Також він є підписником -> AudioCatalog, оскільки при добавленні
 * нової пісні у загал. список пісень він через метод update()
 * автоматично МОЖЕ! добавляти пісню у плейлист.
 *
 * Допрацювати:
 * - Метод update() сам додає пісню, треба зробити так, щоб це
 * можна було вимкнути
 *
 * Контролер живе стільки, стільки живе плей-лист.
 *     Треба брати контролер у плей листа.
 *     Для кожного плей листа новий контролер.
 */

class PlayListController implements Subscriber<AudioTrack> {

    private final List<AudioTrack> listOfSongs;

        private PlayListController(PlayList playList) {
            //this.playList = Objects.requireNonNull(playList, "PlayList не може бути null");
            // Отримуємо наш плейст у контролер.
            this.listOfSongs = playList.getPlayList();
        }

        static PlayListController create(PlayList playList){
            return new PlayListController(playList);
        }

        /** Додає трек у плейлист */
        public void addTrack(AudioTrack track) {
            if (track != null && !listOfSongs.contains(track)) {
                listOfSongs.add(track);
            }
        }

        /** Додає кілька треків одразу */
        public void addTracks(List<AudioTrack> tracks) {
            if (tracks != null) {
                for (AudioTrack track : tracks) {
                    addTrack(track);
                }
            }
        }

        /** Видаляє трек */
        public boolean removeTrack(AudioTrack track) {
            return listOfSongs.remove(track);
        }

        /** Очищує весь плейлист */
        public void clear() {
            listOfSongs.clear();
        }

        /** Перевіряє, чи є трек у плейлисті */
        public boolean contains(AudioTrack track) {
            return listOfSongs.contains(track);
        }

        /** Повертає список треків */
        public List<AudioTrack> getTracks() {
            return Collections.unmodifiableList(listOfSongs);
        }

        /** Заповнює дефолтними треками з каталогу */
        @Deprecated
        public void fillFromCatalog(AudioCatalog catalog) {
            /*
            if (catalog != null) {
                 addTracks(catalog.getAllTracks());
             }
            */
        }

        // Метод автоматично додає пісню в цей сирий каталог, якщо користувач додає пісню.
        @Override
        public void update(AudioTrack eventContent) {
            addTrack(eventContent);
        }
    }
