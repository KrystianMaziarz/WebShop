package pl.com.store.webstore.exceptions;

public class NoBasketException extends Exception {

    public NoBasketException(){
        super("Brak koszyka dla zalogowanego klienta !");
    }
}
