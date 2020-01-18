package pl.com.store.webstore.services.implementations;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.entities.Category;
import pl.com.store.webstore.entities.Item;
import pl.com.store.webstore.repositories.ItemRespository;
import pl.com.store.webstore.repositories.OrderRepository;
import pl.com.store.webstore.services.ItemService;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImpTest {

    @Mock
    private ItemRespository respository;
    @Mock
    private OrderRepository orderRepository;

    private ItemService service;

    @Before
    public void setUp()throws Exception{
        service=new ItemServiceImp(respository,orderRepository);
    }

    @Test
    public void shouldAddItem() {
        //given
        Mockito.when(respository.save(Mockito.any())).thenReturn(new Item());
        //when
        ItemDto itemDto=new ItemDto();
        itemDto.setCategory(new Category());
        itemDto.setDescription("nowy");
        itemDto.setName("hak");
        itemDto.setPhotoUrl("url");
        itemDto.setPrice(BigDecimal.valueOf(12400000));

        service.addItem(itemDto);
        //then
        Mockito.verify(respository,Mockito.atLeastOnce()).save(Mockito.any());
    }

    @Test
    public void shouldFindAllItems() {
        //given
        Mockito.when(respository.findAll()).thenReturn(Lists.newArrayList(new Item()));
        //when
        service.findAllItems();
        //then
        Mockito.verify(respository,Mockito.atLeastOnce()).findAll();
    }

    @Test
    public void findItemById() throws Exception {
        //given
        Item item=new Item();
        item.setId(2L);
        Mockito.when(respository.getOne(2L)).thenReturn(item);
        //when
        service.findItemById(2L);
        //then
        Mockito.verify(respository,Mockito.atLeastOnce()).getOne(2L);
    }

    @Test
    public void updateItemById() throws Exception {
        //given
        Item item=new Item();
        item.setId(3L);
        Mockito.when(respository.getOne(3L)).thenReturn(item);

        ItemDto itemDto=new ItemDto();
        Category category=new Category();
        itemDto.setCategory(category);
        itemDto.setDescription("nowy");
        itemDto.setName("hak");
        itemDto.setPhotoUrl("url");
        itemDto.setPrice(BigDecimal.valueOf(12400000));

        Item expected=new Item();
        expected.setId(3L);
        expected.setCategory(category);
        expected.setDescription("nowy");
        expected.setName("hak");
        expected.setPhotoUrl("url");
        expected.setPrice(BigDecimal.valueOf(12400000));
        //when
        Item result = service.updateItemById(3L, itemDto);
        //then
        assertEquals(expected,result);

    }

    @Test
    public void deleteItemById() throws Exception {
        //when
        service.deleteItemById(1L);
        //then
        Mockito.verify(respository,Mockito.times(1)).deleteById(1L);
    }
}
