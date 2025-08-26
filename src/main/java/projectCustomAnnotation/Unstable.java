package projectCustomAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Ця анотація використовується якщо клас є:
 * недоробленим, не стабільним, можливі помилки,
 * не все реалізовано.
 */

@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Unstable {
}
