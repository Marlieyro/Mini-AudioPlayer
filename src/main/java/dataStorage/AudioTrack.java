package dataStorage;

import java.util.List;
import java.util.Objects;

/**
 * Клас який являє собою певну інформацію про трек,
 * а також: шлях, мета дані.
 *
 * Доробити:
 * - Додати ще можливі поля з інформацією про трек, але
 * ці поля є не всюди.
 */

public class AudioTrack {
    private String title;
    private String artist;
    private Long duration;
    private final String audioPath;
    private List<String> listOfMetaData;

    public AudioTrack(String title, String artist, long duration, String audioPath){
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.audioPath = audioPath;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public Long getDuration() { return duration; }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<String> getListOfMetaData() {
        return listOfMetaData;
    }

    @Override
    public String toString() {
        return "AudioTrack{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", duration=" + duration +
                ", audioPath='" + audioPath + '\'' +
                ", listOfMetaData=" + listOfMetaData +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AudioTrack that = (AudioTrack) object;
        return Objects.equals(title, that.title) && Objects.equals(artist, that.artist) && Objects.equals(duration, that.duration) && Objects.equals(audioPath, that.audioPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist, duration, audioPath);
    }


}
