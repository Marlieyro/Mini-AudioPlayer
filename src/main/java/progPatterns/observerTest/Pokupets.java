package progPatterns.observerTest;

public class Pokupets implements Subscriber<Integer>{

    @Override
    public void whatImDoWhenEventIsHappen(Integer itsDataFromEventWhichProducedWhenHappenEvent) {
        System.out.println(itsDataFromEventWhichProducedWhenHappenEvent);
    }
}
