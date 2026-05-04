/* Neo Wang
 * March 24, 2026
 * Calculates the sum of all the TechDevice Objects that have a "Borrowed" status, 
 * also displays a full list of all the "Borrowed" devices if the user wishes too
 */
package com.neo.techfortommorowprogram.service;

import com.neo.techfortommorowprogram.domain.TechDevice;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class DeviceBorrowedTotalCalculator {
    
    /**
     * Calculates the sum of all the TechDevice Objects that have a "Borrowed" status
     * @param inventory the full array list with all the TechDevices
     */
    public double calculate(List<TechDevice> inventory){
        int count = 0;
        DecimalFormat money = new DecimalFormat("$#,##0.00"); //Instantiates deicmal format for money to use later in the output message
        double totalValue = 0; //The double variable stroing the total sum
        for (int i = 0; i < inventory.size(); i++) { //loops through the full array list of TechDevices
            TechDevice currentDevice = inventory.get(i); //stores the current index of the array list in a seperate variable for readability
            if (currentDevice.getStatus().equals("Borrowed")) { //if the current TechDevice is borrowed
                count++;
                totalValue += currentDevice.getReplacementValue(); //adds the replacement value ot the total value
            }
        }
        
        
        return totalValue;
    }
    
    public String displayBorrowedDevices(List<TechDevice> inventory){
        int count = 0;
        DecimalFormat money = new DecimalFormat("$#,##0.00"); //Instantiates deicmal format for money to use later in the output message
        String displayList = "All currently borrowed devices:\n"; //the start of the full list of Borrowed Devices
        for (int i = 0; i < inventory.size(); i++) { //loops through the full array list of TechDevices
            TechDevice currentDevice = inventory.get(i); //stores the current index of the array list in a seperate variable for readability
            if (currentDevice.getStatus().equals("Borrowed")) { //if the current TechDevice is borrowed
                count++;
                displayList += count + ". " + currentDevice.getIdCode() + " ("  //updates the display list of all the borrowed TechDevices
                    + currentDevice.getBrandModel() + "). Replacement Value: " 
                    + money.format(currentDevice.getReplacementValue()) +"\n";
            }
        }
        return displayList;
    }
   
}
