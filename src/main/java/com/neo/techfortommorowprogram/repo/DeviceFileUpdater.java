/* Neo Wang
 * March 24, 2026
 * Saves the array list of TechDevice Objects into a new file using JFileChooser
 */
package com.neo.techfortommorowprogram.repo;

import com.neo.techfortommorowprogram.domain.TechDevice;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author L0W-R1SE
 */
public class DeviceFileUpdater {
    
    /**
     * Saves the array list of TechDevice Objects into a new save file.
     * @param inventory The array list of TechDevices
     * @return whether of not the save was a success
     */
    public boolean save(List<TechDevice> inventory) throws IOException {
        boolean succesfulSave = false; //boolean tracker is file was succesfully saved, assumes false
        
        JFileChooser fileChooser = new JFileChooser(); //instantiates the JFileChooser object
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir"))); //sets the intital directory for saving in the project directory
        int result = fileChooser.showSaveDialog(new JOptionPane()); //uses JOptionPane to display a file explorer menu to create a new . Stores result in an int

        if (result == JFileChooser.APPROVE_OPTION) { //if the save was sucessfull
            File file = fileChooser.getSelectedFile(); //gets the file the user just created
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < inventory.size(); i++) { //loops through all the objects in the array list
                    //uses buffered writer to write all the object attributes into a file, folowing the same structure as the default file
                    writer.write(inventory.get(i).getIdCode() + ",");
                    writer.write(inventory.get(i).getDeviceType() + ",");
                    writer.write(inventory.get(i).getBrandModel() + ",");
                    writer.write(inventory.get(i).getStatus() + ",");
                    writer.write(inventory.get(i).getReplacementValue() + ",");
                    writer.write(System.lineSeparator()); //after every full file insert, writes line break
                }
                succesfulSave = true; //sets boolean tracker to true
            }

        }
        return succesfulSave; //returns boolean tracker of if the save was succesfull.
    }

}
