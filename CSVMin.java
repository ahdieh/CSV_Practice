
/**
 * Write a description of CSVMin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import org.apache.commons.csv.*;
import edu.duke.*;
import java.io.*;

public class CSVMin {
    public CSVRecord coldestHourInFile(CSVParser parser){
        // start with largestSoFar as nothing
        CSVRecord coldestSoFar = null;
        // For each row (currentRow) in the CSV file
        for (CSVRecord currentRow : parser){
           coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
        }
        // the coldestSoFar is the answer
        return coldestSoFar;
    }
    
    public CSVRecord getColdestOfTwo(CSVRecord currentRow, CSVRecord coldestSoFar){
         // If coldestSoFar is nothing
            if (coldestSoFar == null) coldestSoFar = currentRow;
            // Otherwise
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));                
                // Check if currentRow's temperature > largestSoFar
                // If so, update coldestSoFar to currentRow
                if (currentTemp != -9999.00 && currentTemp < coldestTemp) coldestSoFar = currentRow;
            }
            return coldestSoFar;
    }
    
    public String fileWithColdestTemperature(){
        String fileWithColdest = "";
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        //iterate over files
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            // use meyhod to get the minimum in the file
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);            
            if (currentRow == coldestSoFar) fileWithColdest = f.getPath();
        }
        // the fileWithColdest is the answer
        return fileWithColdest;
    }
    
    // Minimum Temperature in a single day
    public void testColdestHourInFile(){
        FileResource fr = new FileResource("data/2014/weather-2014-01-08.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temprature was " + coldest.get("TemperatureF") + " at " + coldest.get("TimeEST"));
    }
    
    // Minimum Temperature from Multiple Datasets
    public void testFileWithColdestTemperature(){
        String coldestFile = fileWithColdestTemperature();
        File f = new File(coldestFile);
        System.out.println();
        System.out.println();
        FileResource fr = new FileResource(coldestFile);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest day was in file: " + f.getName());
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were: "); 
        for (CSVRecord record : fr.getCSVParser()){
            System.out.println( record.get("DateUTC") + record.get("TemperatureF"));
        }
    }
}