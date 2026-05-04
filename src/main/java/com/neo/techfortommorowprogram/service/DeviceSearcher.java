/* Neo Wang
 * March 24, 2026
 * Searches through the devices by ID code and displays the attributes of the device if found
 */
package com.neo.techfortommorowprogram.service;

import com.neo.techfortommorowprogram.domain.TechDevice;
import java.util.List;
import javax.swing.JOptionPane;


public class DeviceSearcher {
    /**
     * Searches through the devices by ID code and displays the attributes of the device if found
     * @param inventory the full array list of all the TechDevice objects
     */
    public TechDevice searchDevice(List<TechDevice> inventory){
        String inputId= JOptionPane.showInputDialog("Enter the ID of the device you to inspect"); //gets the requested ID from the user
        for(int i = 0; i < inventory.size(); i++){ //loops through all the TechDevices (the full array list), stops when boolean checker foundDevice is set to true
            TechDevice currentDevice = inventory.get(i); //stores the current index of the array list in a seperate variable for readability
            if(currentDevice.getIdCode().equals(inputId)){ //if the current device ID code matches the user inputed ID code
                return currentDevice;
            }
        }
        return null;
    }
}
