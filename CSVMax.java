
/**
 * Write a description of CSVMax here.
 * To find out the hottest day
 * @author (your name) 
 * @version (a version number or a date)
 */

import org.apache.commons.csv.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser){
        // start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        // For each row (currentRow) in the CSV file
        for (CSVRecord currentRow : parser){
            // If largestSoFar is nothing
            if (largestSoFar == null) largestSoFar = currentRow;
            // Otherwise
            else{
                double currentTemp = Double.parseDouble(currentRow.get("Tempreture"));
                double largestTemp = Double.parseDouble(largestSoFar.get("Tempreture"));                // Check if currentRow's temperature > largestSoFar
                // If so, update largestSoFar to currentRow
                if (currentTemp > largestTemp) largestSoFar = currentRow;
                }
        }
        // the largestSoFar is the answer
        return largestSoFar;
    }
}
