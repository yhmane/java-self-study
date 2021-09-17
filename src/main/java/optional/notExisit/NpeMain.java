package optional.notExisit;

public class NpeMain {

    /**
     * 1. NPE는 에러의 근원
     * 2. 코드를 어지럽힌다
     * 3. null은 아무 의미도 표현하지 않는다
     * 4. 자바 철학에 위배
     * 5. 형식 시스템에 구멍이 생김
     */
    public String getCarInsuranceName1(Person person) {
        if (person != null) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();

                if (insurance != null) {
                    return  insurance.getName();
                }
            }
        }
        return "Unknown";
    }

    public String getCarInsuranceName2(Person person) {
        if (person == null) {
            return "Unknown";
        }

        Car car = person.getCar();
        if (car == null) {
            return "Unknown";
        }

        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "Unknown";
        }
        return "Unknown";
    }
}
