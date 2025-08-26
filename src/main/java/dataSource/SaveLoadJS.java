package dataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dataStorage.AudioTrack;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Цей клас дає змогу зберігати List з піснями після закриття
 * програми. А також завантажувати List зі збережними піснями
 * при завантаженні програми.
 *
 * Використовується бібліотека Gson від Google. Це дуже проста
 * бібліотека, яка буквально робить збереження/завантаження
 * інформації з .json файлу, за одну дві, строки коду.
 *
 * Я не розуміюсь у .json форматі!
 */

public class SaveLoadJS {

    // Статичні екземпляри через те, що клас не потребує створення для роботи
    private static final Path path = Path.of("C:\\Users\\crtwi\\IdeaProjects\\AudioPlayerProject\\src\\main\\resources\\songsGeneralCatalog.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<AudioTrack> loadSongsList() throws IOException {
        return gson.fromJson(Files.readString(path), new TypeToken<List<AudioTrack>>() {}.getType());
    }

    public static void saveSongsList(List<AudioTrack> save_to_json) throws IOException {
        String jsonStr = gson.toJson(save_to_json);
        Files.writeString(path, jsonStr);
    }
}
