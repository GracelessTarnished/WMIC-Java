package energyreport;

/**
 *
 * @author Kobevader
 */
public interface Commands {         // An interface of non WMIC commands
    
    
   
    public void hostname();     // Hostname info
    public void userInfo();    // Various user account info
    public void netstata();   // Active network connections
    public void psInfo();    // Pc info
    public void taskList(); // Currently running tasks
    public void ipConfig();// Ip, dns, subnet mask info
}
