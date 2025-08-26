package progPatterns.observerTest;

public interface Subscriber<T> {
    void whatImDoWhenEventIsHappen(T itsDataFromEventWhichProducedWhenHappenEvent);
}
