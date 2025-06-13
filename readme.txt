Per compilare: javac -cp .:junit/junit-4.13.jar:junit/hamcrest-core-1.3.jar myAdapter/*.java myTest/*.java

Per eseguire: java -cp .:junit/junit-4.13.jar:junit/hamcrest-core-1.3.jar org.junit.runner.JUnitCore myTest.TestListAdapter

javadoc: javadoc -tag d.esign:cm:"Progetto:" -tag s.ummary:cm:"Sommario:" -tag d.escription:cm:"Descrizione:" -tag p.recond:cm:"Condizione iniziale:" -tag p.ostcond:cm:"Condizione finale:" -tag r.esult:cm:"Risultato:" -private -d doc/ -cp ./JUnit/junit-4.13.jar: ./myTest/*.java ./myAdapter/*.java