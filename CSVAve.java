
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
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double averageTempWithHighHumidity = 0;
        double sum = 0;
        int count = 0;
        for (CSVRecord record : parser){
            if (Double.parseDouble(record.get("Humidity")) >= value){
                double temp = Double.parseDouble(record.get("TemperatureF"));
                sum +=temp;
                count += 1;
            }
        }
        if (count != 0) averageTempWithHighHumidity = sum/count;
        else System.out.println("No temperatures with that humidity");
        return averageTempWithHighHumidity;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
       FileResource fr = new FileResource();
       double averageTempWithHighHumidity = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
       if (averageTempWithHighHumidity != 0) System.out.println("Average Temp when high Humidity is " + averageTempWithHighHumidity);
    }
} 
