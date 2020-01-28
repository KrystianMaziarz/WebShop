package pl.com.store.webstore.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.store.webstore.entities.Category;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto implements Serializable {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Category category;
    private String photoUrl;
    private List<OrderDto> orders;
}
