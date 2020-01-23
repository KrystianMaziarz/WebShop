package pl.com.store.webstore.controllers.dtos;

import lombok.Data;
import pl.com.store.webstore.entities.Category;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemDto implements Serializable {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Category category;
    private String photoUrl;
    private List<OrderDto> orders;
}
