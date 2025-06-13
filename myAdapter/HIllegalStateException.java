package myAdapter;

/**
* Eccezione lanciata quando un'operazione viene chiamata in un momento non valido.
* Questa eccezione viene tipicamente lanciata quando si tenta di modificare un elemento
* attraverso un iteratore senza aver prima chiamato next() o previous().
*/
public class HIllegalStateException extends RuntimeException {

    /**
     * Costruisce una HIllegalStateException senza messaggio di dettaglio.
     */
    public HIllegalStateException() {
        super();
    }
 
    /**
     * Costruisce una HIllegalStateException con il messaggio di dettaglio specificato.
     *
     * @param message il messaggio di dettaglio
     */
    public HIllegalStateException(String message) {
        super(message);
    }
 }