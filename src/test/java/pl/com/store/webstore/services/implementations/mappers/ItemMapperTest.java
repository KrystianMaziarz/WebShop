package pl.com.store.webstore.services.implementations.mappers;

import org.assertj.core.util.Lists;
import org.junit.Test;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.entities.Category;
import pl.com.store.webstore.entities.Item;
import pl.com.store.webstore.entities.Order;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ItemMapperTest {

    @Test
    public void shouldMapToDto() {
        //given
        Item item = new Item();
        Category category = new Category();
        Order order = new Order();
        item.setName("Lodówka");
        item.setDescription("używana");
        item.setPhotoUrl("http://localhost:8080/photos/lodowka.jpg");
        item.setPrice(BigDecimal.valueOf(123000));
        item.setCategory(category);
        item.setOrders(Lists.newArrayList(order));

        ItemDto expected = new ItemDto();
        expected.setCategory(category);
        expected.setDescription("używana");
        expected.setName("Lodówka");
        expected.setPhotoUrl("http://localhost:8080/photos/lodowka.jpg");
        expected.setPrice(BigDecimal.valueOf(123000));
        //when
        ItemDto result = ItemMapper.mapToDto(item);
        //then
        assertEquals(expected, result);
    }

    @Test
    public void shouldMapToDtoButExpectedIsWrong() {
        //given
        Item item = new Item();
        Category category = new Category();
        Order order = new Order();
        item.setName("Lodówka");
        item.setDescription("używana");
        item.setPhotoUrl("http://localhost:8080/photos/lodowka.jpg");
        item.setPrice(BigDecimal.valueOf(123000));
        item.setCategory(category);
        item.setOrders(Lists.newArrayList(order));

        ItemDto expected = new ItemDto();
        expected.setCategory(category);
        expected.setDescription("używana");
        expected.setName("Lodówkaaaaaaaaaaaaaaaaa");
        expected.setPhotoUrl("");
        expected.setPrice(BigDecimal.valueOf(123000));
        //when
        ItemDto result = ItemMapper.mapToDto(item);
        //then
        assertNotEquals(expected, result);
    }
}
