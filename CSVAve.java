
/**
 * Write a description of CSVAve here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import org.apache.commons.csv.*;
import java.io.*;
import edu.duke.*;
public class CSVAve {
    public double averageTemperatureInFile (CSVParser parser){
        double averageTemp = 0;
        double totalTemp = 0;
        int count = 0;
        for (CSVRecord record : parser){
            totalTemp = totalTemp + Double.parseDouble(record.get("TemperatureF"));
            count += 1;
        }
        averageTemp = totalTemp / count;
        return averageTemp;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        double average = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + average);
    }
    
}
