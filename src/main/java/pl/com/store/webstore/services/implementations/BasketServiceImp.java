package pl.com.store.webstore.services.implementations;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.services.basket.Basket;
import pl.com.store.webstore.services.BasketService;
import pl.com.store.webstore.services.basket.BasketRepository;

@Service
public class BasketServiceImp implements BasketService {

   private BasketRepository repository;

    public BasketServiceImp(BasketRepository repository) {
        this.repository = repository;
    }

    @Override
    public Basket createBasket(Long customerId) {
        Basket basket = new Basket();
        basket.setCustomerId(customerId);
        repository.getBaskets().add(basket);
        return basket;
    }

    @Override
    public Basket addToBasket(Long customerId, ItemDto itemDto) {

        Basket foundBasket = repository.getBaskets().stream()
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
        if (!repository.getBaskets().isEmpty()) {
            return repository.getBaskets().stream()
                    .filter(basket -> basket.getCustomerId().equals(customerId))
                    .findFirst().orElse(null);
        }
        return null;
    }

    @Override
    public void removeBasket(Long customerId) {
        Basket basket = getBasket(customerId);
        repository.getBaskets().remove(basket);
    }
}
