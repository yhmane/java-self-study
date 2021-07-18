package generic;

import generic.doc.Store;
import generic.doc.User;

public class GenericMain {

    public static void main(String[] args) {

        User user = new User(1L, "윤호", "test1234@gmail.com");
        Store store = new Store(1L, "윤호가게", "강남구 봉은사로");
        System.out.println(user);
        System.out.println(store);

        Box<Integer> box1 = BoxUtil.boxing(100);
        Box<String> box2 = BoxUtil.boxing("홍길동");
        System.out.println(box1.get());
        System.out.println(box2.get());
    }
}
