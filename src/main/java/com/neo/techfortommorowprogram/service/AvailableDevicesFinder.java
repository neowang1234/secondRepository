/* N Wang
 * March 24, 2026
 * Finds all Devices with the "Available" status and stores them in a new array. 
 * Than orders and displays them from highest to lowest replacement value
 */
package com.neo.techfortommorowprogram.service;

import com.neo.techfortommorowprogram.domain.TechDevice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;


public class AvailableDevicesFinder {
    //globally declares new array list for only the "Available" status TechDevices to limit too much passing parameters, and also since it is a small class
    List<TechDevice> availableDevices = new ArrayList<TechDevice>(); 
    
    /**
     * Finds all Devices with the "Available" status and stores them in a new array. 
     * Than orders and displays them from highest to lowest replacement value
     * @param inventory the full array list of all the TechDevices
     */
    public void findAllAvailable(List<TechDevice> inventory) {
        for (int i = 0; i < inventory.size(); i++) { //loops through all the TechDevices (the full array list)
            TechDevice currentDevice = inventory.get(i); //stores the current index of the array list in a seperate variable for readability
            if (currentDevice.getStatus().equals("Available")) { //if the current device has a status of "Available"
                availableDevices.add(currentDevice); //adds the current device of the array list to the new "Available" status exclusive array list
            }
        }
        
    }
    
    public String organiseAvailableList(){
        DecimalFormat money = new DecimalFormat("$#,##0.00"); //instantiates a money Decimal Format pattern for the later output message
        //creates a custom comparator, using Collections.sort to sort the array list
        availableDevices.sort(new Comparator<TechDevice>() { 
            @Override
            public int compare(TechDevice t1, TechDevice t2) {
                return Double.compare(t2.getReplacementValue(), t1.getReplacementValue()); //t2 being compared to t1 makes the comparator sort the array list from highest to lowest
            }
        });
        
        
        String displayList = "All Available Devices. Sorted Highest to Lowest Cost\n"; //the title of the output message 
        int count = 0; //accumulator used for numbering all the Available devices
        for (int i = 0; i < availableDevices.size(); i++) { //loops through the sorted Available status array list
            //adds all the Available TechDevices to the output message
            count++; 
            displayList += count + ". " + availableDevices.get(i).getIdCode() + " ("  
                    + availableDevices.get(i).getBrandModel() + "). Replacement Value: " 
                    + money.format(availableDevices.get(i).getReplacementValue()) +"\n";
        }
        return displayList;
    }

}
