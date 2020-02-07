package pl.com.store.webstore.exceptions;

public class BasketNotExistException extends Exception {

    public BasketNotExistException(){
        super("Brak koszyka dla zalogowanego klienta !");
    }
}
