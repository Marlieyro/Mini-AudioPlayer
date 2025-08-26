package audioPlayerComp;

import projectCustomAnnotation.Unstable;
import java.util.Map;

/**
 * Інтерфейс який забезпечує створення компонентів.
 * Насправді можна обійтись без нього.
 * Оскільки мій дизайн передбачає існування такого класу, то
 * нехай буде.
 */
@Unstable
interface ComponentSupplier {

    // Метод гарантує 100% створення компонентів програвача.
    Map<TypeOfComponent, PlayerComponent> createDefault();

}
