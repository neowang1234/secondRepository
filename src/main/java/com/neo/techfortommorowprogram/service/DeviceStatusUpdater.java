/* Neo Wang
 * March 24, 2026
 * Allows the user to modify the status of a selected device using the ID code
 */
package com.neo.techfortommorowprogram.service;

import com.neo.techfortommorowprogram.domain.TechDevice;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author L0W-R1SE
 */
public class DeviceStatusUpdater {
    public void updateStatus(List<TechDevice> inventory){
        boolean foundDevice = false; //boolean checker for if the ID num the user entered as found
        String inputId= JOptionPane.showInputDialog("Enter the ID of the device you wish to update the status of"); //gets the requested ID from the user
        for(int i = 0; i < inventory.size() && !foundDevice; i++){ //loops through all the TechDevices (the full array list), stops when boolean checker foundDevice is set to true
            TechDevice currentDevice = inventory.get(i); //stores the current index of the array list in a seperate variable for readability
            if(currentDevice.getIdCode().equals(inputId)){ //if the current device ID code matches the user inputed ID code
                //displays prompt to user asking which status they want
                String newStatus = JOptionPane.showInputDialog(null, """
                                                                     What would you like the new status to be?
                                                                     1. Available
                                                                     2. Borrowed
                                                                     3. Under Repair
                                                                     Enter the number corresponding to your choice.""");
                //matches user response with the coresonding status
                if(newStatus.equals("1")){
                    inventory.get(i).setStatus("Available"); //invokes mutator method to change the status
                    JOptionPane.showMessageDialog(null, "Status successfully changed to Available");
                }else if(newStatus.equals("2")){
                    inventory.get(i).setStatus("Borrowed");
                    JOptionPane.showMessageDialog(null, "Status successfully changed to Borrowed");
                }else if(newStatus.equals("3")){
                    inventory.get(i).setStatus("Under Repair");
                    JOptionPane.showMessageDialog(null, "Status successfully changed to Under Repair");
                }else{ //error checks, if the user does not enter a possible option
                    JOptionPane.showMessageDialog(null, "Please enter a valid option.", "Error", JOptionPane.ERROR_MESSAGE); //displays error message
                }
                //sets boolean checker to true, ending the for loop (Even if user selected an impossible option, still end it since the device has already been found, and each ID code is unique'
                foundDevice = true; 
            }
        }
        
        
        if(!foundDevice){//if the device was never found (No ID code match)
            JOptionPane.showMessageDialog(null, "Device ID not found.", "Error", JOptionPane.ERROR_MESSAGE); //Displays error message
        }
        
        
    }
}
