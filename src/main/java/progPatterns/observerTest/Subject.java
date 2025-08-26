package progPatterns.observerTest;

public interface Subject<T> {
    void subscribe(Subscriber<T> subsccribeObject);
    void unSubscribe();
    void giveInformationToAllSubscribers();
}
