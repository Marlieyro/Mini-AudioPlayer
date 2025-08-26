package audioPlayerComp;

import progPatterns.Subject;
import progPatterns.Subscriber;
import projectCustomAnnotation.Unstable;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас який грає роль менеджера підписок у патерні Observer.
 * Зберігає List з усіма Subject, щоб можна було дістати будь-який
 * клас який реалізує Subject і підписати на нього потенційного підписника.
 *
 * Можливо методи класу і List варто зробити статичними.
 */

@Unstable
public class SubscriberManager {

    public SubscriberManager(){}

    /**
     * Цей інтерфейс параментризований дженериком, але
     * тут зберігаються різні реалізатори Subject.
     */
    List<Subject> subjects = new ArrayList<>();

    public void regSubject(Subject subject){
        subjects.add(subject);
    }

    public void subToSubject(Subscriber subscriber, int index){
        subjects.get(index).subscribeOnMe(subscriber);
    }
}
