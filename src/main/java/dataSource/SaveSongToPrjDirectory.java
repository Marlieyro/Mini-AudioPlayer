package dataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Клас який надає метод, який копіює пісню
 * з оригінальної теки у теку проєкту.
 * Це робиться для централізації всіх пісень, на випадок,
 * якщо користувач видалить пісню з оригінального шляху.
 */

// Парсер викликатиме метод який сейвить пісню у папку
class SaveSongToPrjDirectory {

    private SaveSongToPrjDirectory() {}

    protected static void copyToPath(String source){
        Path sourcePath = Path.of(source);

        Path targetDirectory = Path.of("C:\\Users\\crtwi\\IdeaProjects\\AudioPlayerProject\\src\\main\\resources\\libraryOfTracks");
        Path targetSongFileName = targetDirectory.resolve(sourcePath.getFileName());

        try {
            // Перевірка чи існує вже такий файл у нашій теці.
            if (Files.exists(targetSongFileName)){
                System.out.println("Такий файл уже існує в папці.");
                return;
            }

            Files.copy(sourcePath, targetSongFileName, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
