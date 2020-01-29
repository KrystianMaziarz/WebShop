package pl.com.store.webstore.services.basket;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class BasketRepository {

    private List<Basket> baskets = new ArrayList<>();

    public List<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(List<Basket> baskets) {
        this.baskets = baskets;
    }
}
