/* Neo Wang
 * March 24, 2026
 * Removes a TechDevice from the array list based on ID code
 */
package com.neo.techfortommorowprogram.service;

import com.neo.techfortommorowprogram.domain.TechDevice;
import java.util.List;
import javax.swing.JOptionPane;


public class DeviceDeleter {
    
    /**
     * Removes a TechDevice from the array list based on ID code
     * @param inventory the array list of TechDevice Objects
     * @param inputId the inputId of the device the user is searching for
     * @return whether the device was found
     */
    public boolean removeDevice(List<TechDevice> inventory, String inputId){
        boolean foundDevice = false; //boolean checker for if the ID num the user entered as found
        for(int i = 0; i < inventory.size() && !foundDevice; i++){ //loops through all the TechDevices (the full array list), stops when boolean checker foundDevice is set to true
            TechDevice currentDevice = inventory.get(i); //stores the current index of the array list in a seperate variable for readability
            if(currentDevice.getIdCode().equals(inputId)){ //if the current device ID code matches the user inputed ID code
                inventory.remove(i); //removes the TechDevice object form the array list
                foundDevice = true; //sets boolean checker foundDevice to true, stoping the for loop
            }
        }
        return foundDevice;
        
    }
}
