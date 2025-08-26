package playLists;

import dataStorage.AudioTrack;

import java.util.List;

/* Interface description
    Цей інтерфейс призначений для маркування плей-листів.
    Це потрібно для того, щоб було легше працювати з плей-листами.
    Тут є метод getPlayList() який надає 100% гарантію що при використанні
    певного пелей-листу ми точно матимемо можливість отримати пісні з нього.
    Метод getController() повертає контроллер для плей-листа, бо плей-лист
    керується з контролера, а не з самого класу плей листа.
* */
public interface PlayList {

    List<AudioTrack> getPlayList();

    PlayListController getController();
}
