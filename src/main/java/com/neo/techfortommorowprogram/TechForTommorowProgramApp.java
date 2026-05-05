/* Neo Wang
 * March 24, 2026
 * Device inventory orgaiser for the company TechForTomorrow. 
 * Allows the creation, deletion, searching, displaying, and saving of devices in a save file 
 */
package com.neo.techfortommorowprogram;

import com.neo.techfortommorowprogram.exception.TechDeviceException;
import com.neo.techfortommorowprogram.ui.DevicesManager;
import java.io.IOException;
import javax.swing.JOptionPane;

public class TechForTommorowProgramApp {

    public static void main(String[] args) {
        try {
            DevicesManager deviceManager = new DevicesManager();
            deviceManager.initDevices("/techData.txt");
            deviceManager.manageDevices();
        } catch (IOException | TechDeviceException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }
}
