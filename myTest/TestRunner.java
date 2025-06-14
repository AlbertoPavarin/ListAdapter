package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
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

