package pl.com.store.webstore.controllers.dtos;

import java.io.Serializable;

public class CategoryDto implements Serializable {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
