package audioPlayerComp;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;
@Deprecated
public class VLCJPlayer {
    // треба перевести усе на статуси у Enum щоб можна було відслідковувати кроки користувача
    // також треба додати обсерверів і більш логічну структуру переключення треків і самого програшу.
    private AudioPlayerComponent audioPlayerComponent = AudioPlayerInstance.getAudioPlayerComponent();

    private int indexAudioTracker = 0;

    public VLCJPlayer() {
        //this.audioPlayerComponent = AudioPlayer Instance.getAudioPlayerComponent();
    }



    private void releaseResources() {
        audioPlayerComponent.mediaPlayer().submit(() -> {
            audioPlayerComponent.mediaPlayer().release();
            System.exit(0);
        });
    }




   protected void pause(){
        audioPlayerComponent.mediaPlayer().controls().pause();
   }

   protected void stop(){
        audioPlayerComponent.mediaPlayer().controls().stop();
   }

    public boolean play(String path) {
        boolean successStatus = audioPlayerComponent.mediaPlayer().media().play(path    );
        audioPlayerComponent.mediaPlayer().events().addMediaPlayerEventListener(new MediaPlayerEventAdapter(){
            @Override
            public void finished(MediaPlayer mediaPlayer) {
                releaseResources();
            }
        });
        return successStatus;
    }

   /*protected void next(){
        if (shuffleStatus == ShuffleStatus.SHUFFLED){
           // fileAbsolutePath = listOfAudio.getListOfAudioTracks().get(shuffleIndex).getPathToAudio();
            if (repeatStatus == RepeatStatus.NOT_REPEAT || repeatStatus == RepeatStatus.REPEAT_ALL){
            shuffleIndex++;}
        }

        if (shuffleStatus == ShuffleStatus.NOT_SHUFFLED) {
          //  fileAbsolutePath = listOfAudio.getListOfAudioTracks().get(indexAudioTracker).getPathToAudio();
            if (repeatStatus == RepeatStatus.NOT_REPEAT || repeatStatus == RepeatStatus.REPEAT_ALL){
            indexAudioTracker++;}
        }
        play();
   }*/

   /*protected void prev(){
       if (indexAudioTracker < 0) throw new IndexOutOfBoundsException();

       if (shuffleStatus == ShuffleStatus.SHUFFLED){
           //fileAbsolutePath = listOfAudio.getListOfAudioTracks().get(shuffleIndex).getPathToAudio();
           if (repeatStatus == RepeatStatus.NOT_REPEAT || repeatStatus == RepeatStatus.REPEAT_ALL){
           shuffleIndex--;}
       }
       if (shuffleStatus == ShuffleStatus.NOT_SHUFFLED) {
           //   fileAbsolutePath = listOfAudio.getListOfAudioTracks().get(indexAudioTracker).getPathToAudio();
            if (repeatStatus == RepeatStatus.NOT_REPEAT || repeatStatus == RepeatStatus.REPEAT_ALL){
            indexAudioTracker--;}
        }
        play();
   }*/

   protected void volumePlus(){}
   protected void volumeMinus(){}

   protected void adjustAudioPosition(long time){
        audioPlayerComponent.mediaPlayer().controls().setTime(time);
   }

   protected void skipPositionUp(long time){
        audioPlayerComponent.mediaPlayer().controls().skipTime(time);
   }

    protected void skipPositionBack(long time){
        audioPlayerComponent.mediaPlayer().controls().skipTime(time);
    }

    /*protected void shuffleTracks(){
        if (shuffleStatus == ShuffleStatus.NOT_SHUFFLED) {
            shuffledListOfAudio = new ArrayList<>();
            if (!shuffledListOfAudio.isEmpty()){
                shuffledListOfAudio.clear();
                Collections.copy(shuffledListOfAudio, listOfAudio.getListOfAudioTracks());
            }
            Collections.shuffle(shuffledListOfAudio);
        }
    }*/


    private class AudioValidator{

        private boolean isNull(String path){
            return path == null;
        }
    }

    private enum ShuffleStatus {
        SHUFFLED, NOT_SHUFFLED;
        private ShuffleStatus(){}
        private static void setShuffled(ShuffleStatus statusShuffled){
            statusShuffled = SHUFFLED;
        }
        private static void  setNotShuffled(ShuffleStatus statusNotShuffled){
            statusNotShuffled = NOT_SHUFFLED;
        }
    }
    private enum RepeatStatus {
        REPEAT_ALL, REPEAT_SINGLE, NOT_REPEAT;
        private RepeatStatus(){}
    }
}


