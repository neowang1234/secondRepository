/* Neo Wang
 * March 24, 2026
 * Device inventory orgaiser for the company TechForTomorrow. 
 * Allows the creation, deletion, searching, displaying, and saving of devices in a save file 
*/


package com.neo.techfortommorowprogram;

import com.neo.techfortommorowprogram.ui.DevicesManager;

public class TechForTommorowProgramApp {
    
    public static void main(String[] args) {
        DevicesManager deviceManager = new DevicesManager();
        deviceManager.initDevices("/techData.txt");
        deviceManager.manageDevices();
    }
}
