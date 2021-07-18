package generic.doc;

import generic.QueryResult;

public class Store implements QueryResult<Long> {

    private Long storeId;
    private String name;
    private String address;

    public Store(Long storeId, String name, String address) {
        this.storeId = storeId;
        this.name = name;
        this.address = address;
    }

    @Override
    public Long id() {
        return storeId;
    }

    @Override
    public String toString() {
        return "Store{" +
            "storeId=" + storeId +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            '}';
    }
}
