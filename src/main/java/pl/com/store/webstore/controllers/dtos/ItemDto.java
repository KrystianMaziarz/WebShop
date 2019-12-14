package pl.com.store.webstore.controllers.dtos;

import java.math.BigDecimal;

public class ItemDto {

    private String name;

    private BigDecimal price;

    /*private Description description;*/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /*public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }*/
}
