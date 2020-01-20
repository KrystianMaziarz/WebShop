package pl.com.store.webstore.exceptions;

public class ItemNotExists extends Exception {

    public ItemNotExists(){
        super("Brak przedmiotu w zasobach");
    }
}
