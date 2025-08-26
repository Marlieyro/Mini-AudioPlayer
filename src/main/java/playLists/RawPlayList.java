package playLists;

import dataStorage.AudioTrack;
import java.util.List;

/**
 * Клас який представляє сирий плейлсит, тобто по суті це Catalog,
 * але SimplePLayer працює з PlayList тому цей клас є базовим плейлистом.
 *
 * Взаємодія ж самим листом відбувається(редагування ітд) через PlayListController.
 *
 * Метод getPlayList() використовується як базовий для PlayList,
 * це спрощує роботу з PlayList`s.
 */

class RawPlayList implements PlayList {

    private final PlayListController plController = PlayListController.create(this);
    private final List<AudioTrack> listOfSong;

    protected RawPlayList(List<AudioTrack> playLists) {
        this.listOfSong = playLists;
    }

    @Override
    public List<AudioTrack> getPlayList() {
        return listOfSong;
    }

    @Override
    public PlayListController getController() {
        return plController;
    }
}
