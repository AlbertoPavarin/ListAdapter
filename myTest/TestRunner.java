package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
/**
 * Classe che esegue i test per le classi Iterator, ListAdapter e ListIterator.
 * Esegue prima i test di tutte le classi insieme e poi esegue i test delle classi una per volta <br />
 * In ogni caso scrive il numero di test effetuati, il numero di test falliti e il tempo impiegato per eseguire i test. <br />
 * Inoltre se ci sono test falliti, mostra i dettagli dei test falliti. <br />
 * Si utilizzano JUnit 4.13, hamcrest-core-1.3 <br />
 */
public class TestRunner {
    /**
     * Metodo principale che esegue i test per le classi Iterator, ListAdapter e ListIterator.
     * Esegue prima i test di tutte le classi insieme e poi esegue i test delle classi una per volta e ne stampa i dettagli <br />
     * @param args parametri passati da riga di comando
     */
    public static void main(String[] args) {
        Class[] classes = new Class[]{  
            TestIterator.class,
            TestListAdapter.class,
            TestListIterator.class
        };
        System.out.println("Eseguo i test delle classi:");
        System.out.println(" - TestIterator");
        System.out.println(" - TestListIterator");
        System.out.println(" - TestListAdapter");

        // Specifica le classi di test da eseguire
        Result result = JUnitCore.runClasses(
            classes
        );

        // Risultato complessivo
        System.out.println();
        System.out.println("Numero di test eseguiti: " + result.getRunCount());
        System.out.println("Numero di test falliti: " + result.getFailureCount());
        System.out.println("Tempo impiegato per eseguire i test: " + result.getRunTime() + "ms");
        System.out.println();

        if (result.wasSuccessful()) {
            System.out.println("Tutti i test sono stati eseguiti con successo!");
        } else {
            System.out.println("Dettagli sui test falliti: ");
            // Mostra i fallimenti
            for (Failure failure : result.getFailures()) {
                System.out.println("Test fallito: " + failure.toString());
            }
        }

        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println("Esecuzione test per ogni classe");
        for (Class elem : classes) {
            System.out.println();
            System.out.println("Esecuzione test classe: " + elem.getName());
            result = JUnitCore.runClasses(
                elem
            );

            System.out.println("Numero di test eseguiti: " + result.getRunCount());
            System.out.println("Numero di test falliti: " + result.getFailureCount());
            System.out.println("Tempo impiegato per eseguire i test: " + result.getRunTime() + "ms");

            if (result.wasSuccessful()) {
                System.out.println("Tutti i test di " + elem.getName() +" sono stati eseguiti con successo!");
            } else {
                System.out.println("Dettagli sui test falliti: ");
                // Mostra i fallimenti
                for (Failure failure : result.getFailures()) {
                    System.out.println("Test fallito: " + failure.toString());
                }
            }
        }
    }
}

