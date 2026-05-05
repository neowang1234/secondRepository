/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neo.techfortommorowprogram.ui;

import com.neo.techfortommorowprogram.service.AvailableDevicesFinder;
import com.neo.techfortommorowprogram.service.DeviceBorrowedTotalCalculator;
import com.neo.techfortommorowprogram.service.DeviceDeleter;
import com.neo.techfortommorowprogram.service.DeviceSearcher;
import com.neo.techfortommorowprogram.domain.TechDevice;
import com.neo.techfortommorowprogram.exception.TechDeviceException;
import com.neo.techfortommorowprogram.service.DeviceStatusUpdater;
import com.neo.techfortommorowprogram.repo.DeviceFileReader;
import com.neo.techfortommorowprogram.repo.DeviceFileUpdater;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author L0W-R1SE
 */
import com.neo.techfortommorowprogram.repo.DeviceFileUpdater;
import com.neo.techfortommorowprogram.repo.DeviceFileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DevicesManager {

    private DecimalFormat money = new DecimalFormat("$#,##0.00");
    private boolean dataSaved = true;
    private List<TechDevice> inventory = new ArrayList();

    private final String ADD_DEVICE_OPTION = "1";
    private final String REMOVE_DEVICE_OPTION = "2";
    private final String UPDATE_DEVICE_OPTION = "3";
    private final String SEARCH_DEVICE_OPTION = "4";
    private final String DISPLAY_DEVICES_OPTION = "5";
    private final String CALCULATE_TOTAL_OPTION = "6";
    private final String SAVE_CHANGES_OPTION = "7";
    private final String HELP_OPTION = "8";
    private final String IMPORT_FILE_HELP_OPTION = "9";

    public void initDevices(String filepath) throws IOException, TechDeviceException {
        DeviceFileReader importFile = new DeviceFileReader(); //reads the data file in the ImportFile class
        this.inventory = importFile.importFile(filepath); //creates a new array list of TechDevice objects using the read data from the file
    }

    public void manageDevices() throws IOException {
        String option = displayMenu(); //invokes the displayMenu method, displaying the main meny to the user
        if (option != null) { //If the did not press cancel
            while (!option.equals("10") && !option.equals("")) { //while the user has decided to quit or enter nothing
                decisionBranch(option); //invokes dicisionBranch method
                option = displayMenu(); //after the decisionBranch method is done, reopens the unit
                if (option == null) { //checks if the user pressed cancel every loop
                    option = ""; //if the user pressed cancel, sets their input to "" indicating the end of the program without a crash
                }
            }
        }
        if (!dataSaved) { //if the user has not saved their most recent changes 
            JOptionPane.showMessageDialog(null, "Please save your changes before leaving"); //Displays warning message
            DeviceFileUpdater dataSaver = new DeviceFileUpdater(); //instantiates the SaveChanges object
            dataSaved = dataSaver.save(inventory); //invokes the save behaviour of dataSaver, allowing the user to save
        }

        if (dataSaved) { //of the user succesfully saved every change
            JOptionPane.showMessageDialog(null, "Changes Succesfully Saved, Goodbye!"); //good bye message indicating save success
        } else { //if the user still chose to cancel the save prompt, or fail to save the file due to an error
            JOptionPane.showMessageDialog(null, "User chose to exit without saving. Goodbye!");//good bye message indicatng failue to save
        }

    }

    /**
     * Displays an interface with all the functions of the program for the user
     * to see and select from
     *
     * @return The user's choice
     */
    private String displayMenu() {
        String option = JOptionPane.showInputDialog("""
                                    Welcome to TechForTomorrow!
                                    Main Menu
                                    Would you like too:
                                    1. Add a Device
                                    2. Remove a Device
                                    3. Update a device status
                                    4. Search and Inspect a device
                                    5. Display Available Devices
                                    6. Display current total borrowed value
                                    7. Save Changes
                                    8. Help
                                    9. Importing new data file instructions.
                                    10. Quit""");
        return option;
    }

    /**
     * Manages all possible user choices and instantiates all the different
     * classes for each function of the program Updates the inventory of devices
     * after every option
     *
     * @param option The choice the user chose in the main menu
     */
    private void decisionBranch(String option) throws IOException {

        if (option.equals(ADD_DEVICE_OPTION)) { //if the user entered 1 (Add a Device choice)
            addDevice();
        } else if (option.equals(REMOVE_DEVICE_OPTION)) { //if the user entered 2 (Remove a Device choice)
            removeDevice();
        } else if (option.equals(UPDATE_DEVICE_OPTION)) { //if the user entered 3 (Update a device status)
            updateStatus();
        } else if (option.equals(SEARCH_DEVICE_OPTION)) { //if the user entered 4 (Search and Inspect a device)
            searchDevice();
        } else if (option.equals(DISPLAY_DEVICES_OPTION)) { //if the user entered 5 (Display Avaiable Devices)
            displayAvailable();
        } else if (option.equals(CALCULATE_TOTAL_OPTION)) {//if the user entered 6 (Display current total borrowed value)
            calculateBorrowedTotal();
        } else if (option.equals(SAVE_CHANGES_OPTION)) {//if the user entered 7 (Save changes)
            saveChanges();
        } else if (option.equals(HELP_OPTION)) { //if the user entered 8 (Help)
            helpMenu();
        } else if (option.equals(IMPORT_FILE_HELP_OPTION)) { //if the user entered 9 (Importing new data file instructions)
            importDevicesFileHelp();
        } else { //if the user entered something else other than the avaiblable functions of the program
            JOptionPane.showMessageDialog(null, "Invalid input, Please enter a valid choice", "Error", JOptionPane.ERROR_MESSAGE); //dispalys error message to user
        }
    }

    private void addDevice() {
        DeviceCreater deviceAdder = new DeviceCreater();
        deviceAdder.setVisible(true); //displays the JFrame GUI menu for adding a new device
        deviceAdder.setLocationRelativeTo(null); //centers the JFrame menu relative to screen size

        while (deviceAdder.isDisplayable()) { //while the menu is still up (user hasn't finished)
            try {
                Thread.sleep(100); //pauses the program to avoid the main menu apearing at the same time as the JFrame menu
            } catch (InterruptedException e) { //catches if any otehr process interups the pause
                JOptionPane.showMessageDialog(null, "Error: " + e); //displays error message
            }
        }

        TechDevice newDevice = deviceAdder.getDevice(); //creates a new TechDevice Object using the getDevice behaviour of the AddDevice class
        if (newDevice != null) { //if the new device is not null (User did not close out of the menu)
            this.inventory.add(newDevice); //adds the new device to the array list of the other TechDevice objects
        }
        dataSaved = false; //boolean dataSaved set to false after any action is done
    }

    private void removeDevice() {
        String inputId = JOptionPane.showInputDialog("Enter the ID of the device you wish to remove"); //gets the requested ID from the user
        DeviceDeleter deviceRemover = new DeviceDeleter(); //instantiates RemovedDevice class
        boolean deviceFound = deviceRemover.removeDevice(this.inventory, inputId); //invokes the removeDevice behaviour of the deviceRemover object
        if (deviceFound) {
            JOptionPane.showMessageDialog(null, "Device: " + inputId + "\nhas been successfully removed.");
        } else { //if the device was never found (No ID code match)
            JOptionPane.showMessageDialog(null, "Device ID not found.", "Error", JOptionPane.ERROR_MESSAGE); //displays error message
        }

        dataSaved = false; //boolean dataSaved set to false after any action is done
    }

    private void updateStatus() {
        DeviceStatusUpdater statusUpdater = new DeviceStatusUpdater(); //instantiates UpdateStatus class
        statusUpdater.updateStatus(this.inventory); //invoke the updateStatus behvaious of the statusUpdater object
        dataSaved = false; //boolean dataSaved set to false after any action is done
    }

    private void searchDevice() {
        DeviceSearcher deviceSearcher = new DeviceSearcher(); //instantiates SearchDevice class
        TechDevice foundDevice = deviceSearcher.searchDevice(this.inventory); //invoke the searchDevice behvaious of the statusUpdater object
        if (foundDevice != null) {
            JOptionPane.showMessageDialog(null, "Device found!\n" + foundDevice.toString()); //dislays confirmation of success message
        } else {
            JOptionPane.showMessageDialog(null, "Device ID not found.", "Error", JOptionPane.ERROR_MESSAGE); //Displays error message

        }

        dataSaved = false; //boolean dataSaved set to false after any action is done
    }

    private void displayAvailable() {
        AvailableDevicesFinder displayAvailable = new AvailableDevicesFinder();  //instantiates DisplayAvailble class
        displayAvailable.findAllAvailable(this.inventory); //invoke the findAllAvailable behvaious of the statusUpdater object
        String output = displayAvailable.organiseAvailableList();  //invoke the displayAvailableList behvaious of the statusUpdater object
        JOptionPane.showMessageDialog(null, output); //displays the final output message
        dataSaved = false; //boolean dataSaved set to false after any action is done
    }

    private void calculateBorrowedTotal() {
        DeviceBorrowedTotalCalculator borrowedTotal = new DeviceBorrowedTotalCalculator(); //instantiates CalculateBorrowedTotal class
        double totalValue = borrowedTotal.calculate(this.inventory); //invoke the calculate behvaious of the statusUpdater object
        dataSaved = false; //boolean dataSaved set to false after any action is done

        //displays the total replacement cost, and an confrim  option to see the full list of borrowed Devices
        int reply = JOptionPane.showConfirmDialog(null, "Total Replacement Cost of all Borrowed Devices\n"
                + money.format(totalValue) + "\nWould you like to see the full borrowed devices list?", "", JOptionPane.YES_NO_OPTION);

        //if the user chose to see the full borrowed devices list
        if (reply == JOptionPane.YES_OPTION) {
            String displayList = borrowedTotal.displayBorrowedDevices(inventory);
            JOptionPane.showMessageDialog(null, displayList); //displays the full list
        }
    }

    private void saveChanges() throws IOException {
        DeviceFileUpdater dataSaver = new DeviceFileUpdater(); //instantiates SaveChanges class
        //This is the only way to set dataSaved to true
        dataSaved = dataSaver.save(this.inventory); //invokes the save behaviour of the object, reutrning a true boolean if the file is succesfully saved. 
        if (dataSaved) {
            JOptionPane.showMessageDialog(null, "Save Data succesfully saved!"); //displays succesful save message
        }
    }

    private void helpMenu() {
        //displays help menu
        JOptionPane.showMessageDialog(null, """
                                              Usage Instructions:
                                              Follow the prompts as you use the program.
                                              Enter the number corresponding to the option you wish to select to select it
                                              Pressing cancel or not entering anything on the main menu will exit the program.
                                              Please remember to save all your changes before you close the program.
                                              IMPORTANT: Your saves will only be saved when you select Option 7 to save on the main menu.
                                              Failure to do so will result in the loss of your progress/changes.""");
    }

    private void importDevicesFileHelp() {
        //displays Instructions to importing a new data file menu
        JOptionPane.showMessageDialog(null, """
                                                Instructions to import a new data file.
                                                Note: Must have java installed.
                                                1. Download the source code zip folder for this program.
                                                2. Find the resources folder in your file explorer, or using a terminal. (TechForTommorowProgram\\src\\main\\resources)
                                                3. Insert the data file into the resources folder.
                                                4. Open a terminal such as Windows' build in Command Prompt, and use cd to enter the TechForTommorowProgram directory
                                                5. Enter the command: mvn clean install
                                                6. Lastly, enter the command: java -jar target/TechForTommorowProgram-1.0.jar""");
    }
}

//global boolean variable to track if the user has saved thier changes. 
//Default is true since the user has not done anything at the time of opening the program

