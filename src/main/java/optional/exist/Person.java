package optional.exist;

import java.util.Optional;

public class Person {

    private Car car;

    public Optional<Car> getCar() {
        return Optional.ofNullable(car);
    }
}
