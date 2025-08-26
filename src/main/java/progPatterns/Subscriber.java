package progPatterns;

/*Interface description
* Цей інтерфейс реалізує сторону підписника у патерні Observer.
* Інтерфейс параметразовний через те що, підписник може підписуватись
* на різні Subject, Subject також параметриований.
* Також це потрібно для того, щоб передавати інформацію через метод.*/

public interface Subscriber<T> {
    void update(T eventContent);
}
