package pl.com.store.webstore.controllers.dtos;


import pl.com.store.webstore.entities.Item;

import java.util.List;

public class OrderDto {

    private Long customerId;

    private List<Item> itemList;

    private String createDate;

//    private DispatchAddress dispatchAddress;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

  /*  public DispatchAddress getDispatchAddress() {
        return dispatchAddress;
    }

    public void setDispatchAddress(DispatchAddress dispatchAddress) {
        this.dispatchAddress = dispatchAddress;
    }*/
}
