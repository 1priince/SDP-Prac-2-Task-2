/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package prac2task2;
import prac2task2.Reader;

public class App {
    public static void main(String[] args) {
        Reader reader = new Reader();

        boolean printName = true;
        boolean printPostalZip = false; // Change to true if you want to print
        boolean printRegion = true;
        boolean printCountry = false; // Change to true if you want to print
        boolean printAddress = true;
        
        String path = "C:/Users/Sello Prince Lebudi/Desktop/Prac2Task2/Prac2.xml";
        reader.readAndPrint(path, printName, printPostalZip, printRegion, printCountry, printAddress);
    }
}
