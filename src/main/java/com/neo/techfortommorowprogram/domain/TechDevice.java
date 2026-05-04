/* Neo Wang
 * March 24, 2026
 * Creates a object for a device with an Id code, Device type, Brand Model, Status, and Replacement Value
 */
package com.neo.techfortommorowprogram.domain;
import java.text.DecimalFormat;

public class TechDevice {
    private DecimalFormat money = new DecimalFormat("$#,##0.00");
    private String idCode;
    private String deviceType;
    private String brandModel;
    private String status;
    private double replacementValue;
    
    /**
     * Primary Constructor - Creates an device with null attributes and 0 replacement value
     */
    public TechDevice(){
        idCode = null;
        deviceType = null;
        brandModel = null;
        status = null;
        replacementValue = 0;
    }
    
    /**
     * Secondary Constructor - Creates a device with a set Id Code, Device Type, Brand Model, Status, Replacement Value
     * @param idCode
     * @param deviceType
     * @param brandModel
     * @param status
     * @param replacementValue 
     */
    public TechDevice(String idCode,String deviceType,String brandModel,String status,double replacementValue){
        this.idCode = idCode;
        this.deviceType = deviceType;
        this.brandModel = brandModel;
        this.status = status;
        this.replacementValue = replacementValue;
    }
    
    /**
     * Accessor for the Device Id Code
     * @return the device Id code
     */
    public String getIdCode(){
        return idCode;
    }
    
    /**
     * Mutator for the Device Id Code
     * @param idCode the new device Id Code
     */
    public void setIdCode(String idCode){
        this.idCode = idCode;
    }
    
    /**
     * Accessor for the Device Type
     * @return the device type
     */
    public String getDeviceType(){
        return deviceType;
    }
    
    /**
     * Mutator for the Device type
     * @param deviceType the new device type
     */
    public void setDeviceType(String deviceType){
        this.deviceType = deviceType;
    }
    
    /**
     * Accessor for the Device brand model
     * @return the device brand model
     */
    public String getBrandModel(){
        return brandModel;
    }
    
    /**
     * Mutator for the Device brand model
     * @param brandModel the new device brand model
     */
    public void setBrandModel(String brandModel){
        this.brandModel = brandModel;
    }
    
    /**
     * Accessor for the Device status
     * @return the device status
     */
    public String getStatus(){
        return status;
    }
    
    /**
     * Mutator for the Device Status
     * @param status the new device status
     */
    public void setStatus(String status){
        this.status = status;
    }
    
    /**
     * Accessor for the Device replacement value
     * @return the device replacement value
     */
    public double getReplacementValue(){
        return replacementValue;
    }
    
    /**
     * Mutator for the Device replacement value
     * @param replacementValue the new device replacement value
     */
    public void setReplacementValue(double replacementValue){
        this.replacementValue = replacementValue;
    }
    
    /**
     * Organises the device attribute into a neat String chart
     * @return the Device Attributes
     */
    public String toString(){
        return("ID Number: " + idCode
                + "\nDevice Type: " + deviceType
                + "\nBrand and Model: " + brandModel
                + "\nStatus: " + status
                + "\nReplacement Value: " + money.format(replacementValue));
    }
}

