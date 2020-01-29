package pl.com.store.webstore.services.implementations;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.services.basket.Basket;
import pl.com.store.webstore.services.basket.BasketRepository;
import pl.com.store.webstore.services.implementations.BasketServiceImp;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BasketServiceImpTest {

    @Mock
    private BasketRepository repository;

    private BasketServiceImp service;

    @Before
    public void setUp() throws Exception {
        service = new BasketServiceImp(repository);
    }

    @Test
    public void testShouldCreateBasket() {
        //given
        Basket expected = new Basket();
        expected.setCustomerId(1L);
        repository.getBaskets().add(expected);
        //when
        Basket result = service.createBasket(1L);
        //then
        assertEquals(expected, result);
    }

    @Test
    public void testShouldCreateBasketButExpectedCustomerIdIsWrong() {
        //given
        Basket expected = new Basket();
        expected.setCustomerId(2L);
        repository.getBaskets().add(expected);
        //when
        Basket result = service.createBasket(1L);
        //then
        assertNotEquals(expected, result);
    }

    @Test
    public void shouldAddToBasket() {
        //given
        ItemDto itemDto = new ItemDto(2L, "bluzka", BigDecimal.valueOf(1111), "nowa", null, "urlOfPhotoFile", null);
        Basket expected = new Basket();
        expected.setCustomerId(3L);
        expected.setItems(Lists.newArrayList(itemDto));
        //when
        Basket result = service.addToBasket(3L, itemDto);
        //then
        assertEquals(expected, result);
    }

    @Test
    public void shouldAddToBasketButExpectedCustomerIdIsWrong() {
        //given
        ItemDto itemDto = new ItemDto(2L, "bluzka", BigDecimal.valueOf(1111), "nowa", null, "urlOfPhotoFile", null);
        Basket expected = new Basket();
        expected.setCustomerId(4L);
        expected.setItems(Lists.newArrayList(itemDto));
        //when
        Basket result = service.addToBasket(3L, itemDto);
        //then
        assertNotEquals(expected, result);
    }

    @Test
    public void testShouldReturnBasketObjectWithTheSameValue() {
        //given
        Basket expected = new Basket();
        expected.setCustomerId(11L);
        expected.setItems(Lists.newArrayList(new ItemDto(1l, "bluza", BigDecimal.valueOf(1111), "nowa", null, "photoFileUrl", null)));
        repository.getBaskets().add(expected);
        Mockito.when(repository.getBaskets()).thenReturn(Lists.newArrayList(expected));
        //when
        Basket result = service.getBasket(11L);
        //then
        assertEquals(expected, result);
    }

    @Test
    public void testShouldReturnBasketObjectWithDifferentValue() {
        //given
        Basket expected = new Basket();
        expected.setCustomerId(11L);
        expected.setItems(Lists.newArrayList(new ItemDto(1l, "bluzeczka", BigDecimal.valueOf(1111), "nowa", null, "photoFileUrl2", null)));
        repository.getBaskets().add(expected);
        Basket found = new Basket();
        found.setCustomerId(11L);
        Mockito.when(repository.getBaskets()).thenReturn(Lists.newArrayList(found));
        //when
        Basket result = service.getBasket(11L);
        //then
        assertNotEquals(expected, result);
    }

    @Test
    public void removeBasket() {
        //given
        Basket basket = new Basket();
        basket.setCustomerId(5L);
        basket.setItems(Lists.newArrayList(new ItemDto()));
        repository.getBaskets().add(basket);
        Mockito.when(repository.getBaskets()).thenReturn(Lists.newArrayList(basket));
        //when
        Basket result = service.getBasket(5L);
        service.removeBasket(5L);
        Basket result2 = service.getBasket(5L);
        //then
        assertEquals(basket, result);
        assertNull(result2);
    }
}
