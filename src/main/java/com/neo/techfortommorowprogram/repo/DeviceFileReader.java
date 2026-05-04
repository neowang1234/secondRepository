/* N Wang
 * March 24, 2026
 * Imports the data file and stores the data into an array list full of TechDevice Objects
 */
package com.neo.techfortommorowprogram.repo;

import com.neo.techfortommorowprogram.domain.TechDevice;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeviceFileReader {
    /**
     * Imports the data file and stores the data into an array list full of TechDevice Objects
     * @param filePath the String filePath of the imported file
     * @return the array list full of the TechDevice Objects
     */
    public List<TechDevice> importFile(String filePath){
        InputStream in = DeviceFileReader.class.getResourceAsStream(filePath); //gets the file assuming it is in the resources folder
        Scanner s = new Scanner(in); //instantiates the scanner object
        List<TechDevice> inventory = new ArrayList<TechDevice>(); //creates an empty new Array List of TechDevice Objects
        
        //while the data file has a next line (loops through the whole data file)
        while(s.hasNextLine()){
            String tokens[] = s.nextLine().split(","); //creates a tokens array with all the data parts by spliting the lines by commas
            //stores each token into the parameter variables
            String idCode = tokens[0]; 
            String deviceType = tokens[1];
            String brand = tokens[2];
            String status = tokens[3];
            double replacementValue = Double.parseDouble(tokens[4]); 
            TechDevice techDevice = new TechDevice(idCode, deviceType, brand, status, replacementValue); //instantiates a new TechDevice object with all the tokens
            inventory.add(techDevice);//stores the TechDevice object into the array list
        } 
        return inventory; //returns the full array list of TechDevice Objects
    }
    
}
