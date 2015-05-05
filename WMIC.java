
package energyreport;

/**
 *
 * @author Kobevader
 */
public interface WMIC {                        // An interface of WMIC commands.
    
    
    public void soundDev();                 // Information on installed audio hardware
    public void getUUID();                 // Gets Universal Unique Identifier of computer, & other info (hex)
    public void memoryChip();             // Memory chip information
    public void CPU();                   // Central Processing Unit inforamtion
    public void getSysVariables();      //Active Environmental Variables
    public void baseBoard();           //Baseboard info
    public void installedPrograms();  //Currently installed programs & paths
    public void userAccount();       //Information pertaining to User Accounts on the System
}
