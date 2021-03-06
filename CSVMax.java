
/**
 * Write a description of CSVMax here.
 * To find out the hottest day
 * @author (your name) 
 * @version (a version number or a date)
 */

import org.apache.commons.csv.*;
import edu.duke.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser){
        // start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        // For each row (currentRow) in the CSV file
        for (CSVRecord currentRow : parser){
           largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        // the largestSoFar is the answer
        return largestSoFar;
    }
    // Maximum Temperature in a single day
    public void testHottestDay(){
        FileResource fr = new FileResource("data/2015/weather-2015-01-02.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temprature was " + largest.get("TemperatureF") + 
                            " at " + largest.get("TimeEST"));
    }
    
    public CSVRecord hottestInManyDays(){
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        //iterate over files
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            // use meyhod to get the maximum in the file
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        // the largestSoFar is the answer
        return largestSoFar;
    }
    
    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar){
         // If largestSoFar is nothing
            if (largestSoFar == null) largestSoFar = currentRow;
            // Otherwise
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));                // Check if currentRow's temperature > largestSoFar
                // If so, update largestSoFar to currentRow
                if (currentTemp > largestTemp) largestSoFar = currentRow;
                }
                return largestSoFar;
    }
    // Maximum Temperature from Multiple Datasets
    public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temprature was " + largest.get("TemperatureF") + 
                            " at " + largest.get("DateUTC"));
    }
    
    
    
}
