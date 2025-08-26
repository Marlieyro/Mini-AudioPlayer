package progPatterns;

/*Interface description
Виконує роль Subject у патерні Observer.
Я не впевнений що на 100% правильно реалізую патерн, але
ось така моя реалізація

Реалізація:
- Інтерфейс є параметризованим, щоб можна було керувати тим що будуть отримувтаи підписники
інші рішення є або не надійні, або важкі(великі) для цього проекту
* */

public interface Subject<T> {

    /* Я не додаю сюди ініціалізацію List`a, бо тоді він буде спільний для усіх хто реалізує
    цей патерн. Нехай кожен хто реалізує інтерфейс сам створює свій List.*/

    void subscribeOnMe(Subscriber<T> subscriber);
    void unSubscribeFromMe(Subscriber<T> subscriber);
    void notifySubscribers(T eventContent);

}
