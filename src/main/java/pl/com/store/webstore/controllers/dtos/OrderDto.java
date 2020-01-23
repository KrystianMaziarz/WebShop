package pl.com.store.webstore.controllers.dtos;

import lombok.Data;
import pl.com.store.webstore.entities.enums.Status;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDto implements Serializable {

    private Long id;
    private Long customerId;
    private LocalDate orderDate;
    private BigDecimal orderPrice;
    private List<ItemDto> items;
    private Status status;
}
