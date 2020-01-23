package pl.com.store.webstore.services.implementations;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.exceptions.NoBasketException;
import pl.com.store.webstore.services.Basket;
import pl.com.store.webstore.services.BasketService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketServiceImp implements BasketService {

    private List<Basket> baskets = new ArrayList<>();

    @Override
    public Basket createBasket(Long customerId) {
        Basket basket = new Basket();
        basket.setCustomerId(customerId);
        baskets.add(basket);
        return basket;
    }

    @Override
    public Basket addToBasket(Long customerId, ItemDto itemDto) {

        Basket foundBasket = baskets.stream()
                .filter(basket -> basket.getCustomerId().equals(customerId))
                .findFirst().orElse(createBasket(customerId));

        if (foundBasket.getItems() != null) {
            foundBasket.getItems().add(itemDto);
        } else {
            foundBasket.setItems(Lists.newArrayList(itemDto));
        }
        return foundBasket;
    }

    @Override
    public Basket getBasket(Long customerId) {
       return baskets.stream()
                .filter(basket -> basket.getCustomerId().equals(customerId))
                .findFirst().get();
    }

    @Override
    public void removeBasket(Long customerId) {
        Basket basket = getBasket(customerId);
        baskets.remove(basket);
    }
}
