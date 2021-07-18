package generic.doc;

import generic.QueryResult;

public class User implements QueryResult<Long> {

    private Long userId;
    private String name;
    private String email;

    public User(Long userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    @Override
    public Long id() {
        return userId;
    }

    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
