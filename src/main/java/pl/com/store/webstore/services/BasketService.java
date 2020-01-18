package pl.com.store.webstore.services;

import pl.com.store.webstore.controllers.dtos.CustomerDto;
import pl.com.store.webstore.controllers.dtos.ItemDto;

public interface BasketService {

   Basket createBasket(Long customerId);

   Basket addToBasket(Long customerId,ItemDto itemDto);

   Basket getBasket(Long customerId);

   void removeBasket(Long customerId);
}
