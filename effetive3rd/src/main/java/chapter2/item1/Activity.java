package chapter2.item1;

public class Activity {

    // 만원에 activity 3개 이용!!
    private static final Activity DISCOUNT_THREE_ACTIVITY = new Activity(10000, 3);
    private static final Activity DISCOUNT_FIVE_ACTIVITY = new Activity(15000, 5);

    private int price;
    private int activityCount;

    private Activity() {
    }

    public Activity(int price, int activityCount) {
        this.price = price;
        this.activityCount = activityCount;
    }

    public static Activity getThreeActivity() {
        return DISCOUNT_THREE_ACTIVITY;
    }

    public static Activity getFiveActivity() {
        return DISCOUNT_FIVE_ACTIVITY;
    }
}
