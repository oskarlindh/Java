import java.util.*;
import java.io.*;

public class ScannerTest {
    public static void main(String[] args) throws Exception {
        testScanner();        
    }


    public static void testScanner() throws Exception
    {

        Scanner fileScanner = new Scanner(new File("stuff.txt"));
        fileScanner.useDelimiter("\n");
        while (fileScanner.hasNext()) {
            // Read one line
            String line = fileScanner.next();
            System.out.println("Reading line: + '" + line + "'");

            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(";");

            while (lineScanner.hasNext()) {
                // Read one token
                String token = lineScanner.next();
                System.out.println("\tReading token: + '" + token + "'");
            }
        }
    }
}
