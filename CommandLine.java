/*
 Program to execute command arguments...
 Implements three interfaces.
 UPDATE : Methods work, however the GUI definitely needs work. The information gleaned from WMIC's must be presentable, and possibly interactable too.
 */
package energyreport;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 *
 * @author Kobevader
 */
public class CommandLine implements Commands, WMIC, AdminCommands { //Implement the 3 interfaces containing commands

    public static String AdminCmd = "C:\\Users\\Kobevader\\Documents\\NetBeansProjects\\EnergyReport\\ElevatedCmd.lnk";
    final static JTextArea metainfo = new JTextArea();
    
    public static void main(String[] args) throws IOException { //GUI is created in the main method

        final CommandLine cl = new CommandLine();
        
        try {
            
            com.jtattoo.plaf.noire.NoireLookAndFeel.setTheme("Small-Font", "INSERT YOUR LICENSE KEY HERE", "EV Radio");
            
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        final JFrame frame = new JFrame();
        
        JMenuBar menu = new JMenuBar();
        
        metainfo.setFocusable(true);
         metainfo.setWrapStyleWord(true);
 
        
        Font f = new Font("Zapfino", Font.ROMAN_BASELINE, 12);
        UIManager.put("Menu.font", f);
        
        JMenu m1 = new JMenu("System");
        JMenu m2 = new JMenu("Network");
        JMenu m3 = new JMenu("Tools");
        JMenu m4 = new JMenu("About");
        
        metainfo.setSize(25, 25);
        metainfo.setOpaque(false);
        metainfo.setLineWrap(true);
        metainfo.setWrapStyleWord(true);
        metainfo.setEditable(false);
        Font font = new Font("Bauhaus 93", Font.BOLD, 12);
        metainfo.setFont(font);
        metainfo.setForeground(Color.white);
        
        JMenuItem item1 = new JMenuItem("Audio Hardware");
        JMenuItem item2 = new JMenuItem("Hostname");
        JMenuItem item3 = new JMenuItem("Task List");
        JMenuItem item4 = new JMenuItem("Memory Chip");
        JMenuItem item5 = new JMenuItem("CPU Info");
        
        menu.setBackground(Color.BLACK);
        menu.add(m1);
        
        m1.add(item1);
        m1.add(item2);
        m1.add(item3);
        m1.add(item4);
        m1.add(item5);
        
        frame.setSize(400, 350);
        frame.setResizable(true);
        frame.setTitle("Systems Management");
        
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);
        frame.setJMenuBar(menu);
        frame.add(metainfo);
        frame.setVisible(true);
        
       cl.installedPrograms();
        
        item1.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Thread hilo;
                hilo = new Thread(new Runnable() {
                    
                    @Override
                    public void run() {
                        
                        cl.soundDev();
                        
                    }
                });
                
                hilo.start();
                
            }
        });
        
        item2.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Thread hilo;
                hilo = new Thread(new Runnable() {
                    
                    @Override
                    public void run() {
                        
                        cl.userInfo();
                        
                    }
                });
                
                hilo.start();
                
            }
        });
        
        
         item3.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Thread hilo;
                hilo = new Thread(new Runnable() {
                    
                    @Override
                    public void run() {
                        
                        cl.taskList();
                        
                    }
                });
                
                hilo.start();
                
            }
        });
        
         
           item4.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Thread hilo;
                hilo = new Thread(new Runnable() {
                    
                    @Override
                    public void run() {
                        
                        cl.memoryChip();
                        
                    }
                });
                
                hilo.start();
                
            }
        });
           
              item5.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Thread hilo;
                hilo = new Thread(new Runnable() {
                    
                    @Override
                    public void run() {
                        
                        cl.CPU();
                        
                    }
                });
                
                hilo.start();
                
            }
        });
        
    }
    
    @Override
    public void energyReport() {
        try {
            
            File ElevatedCmd = new File(AdminCmd);
            
            Runtime rt = Runtime.getRuntime();
            
            Desktop Dtop = Desktop.getDesktop();
            Dtop.open(ElevatedCmd);
            /*
             Process pr = rt.exec(ElevatedCmd);
                
             Process pr2 = rt.exec("cmd powercfg/energy");
                
             BufferedReader input = new BufferedReader(new InputStreamReader(
             pr.getInputStream()));
                
             String line = null;
                
             while ((line = input.readLine()) != null) {
             System.out.println(line);

             }
                
             String report = "C:\\Windows\\System32\\energy-report.html";
             File energyreport = new File(report);
                
             Desktop dt = Desktop.getDesktop();
             dt.open(energyreport);
                
               
             }*/
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void hostname() {
        
        try {
            Runtime rt = Runtime.getRuntime();
            String cmd = "cmd /c hostname";
            
            Process pr = rt.exec(cmd);
            
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    pr.getInputStream()));
            
            String line = null;
            
            while ((line = input.readLine()) != null) {
                System.out.println(line);
               
                metainfo.append(line);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void userInfo() {
        
        try {
            Runtime rt = Runtime.getRuntime();
            String cmd = "cmd /c whoami";
            
            Process pr = rt.exec(cmd);
            
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    pr.getInputStream()));
            
            String line = null;
            
            metainfo.setText(null);
            
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                
                String message = line;
                
                metainfo.append(message);
                
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void netstata() {
        
        try {
            Runtime rt = Runtime.getRuntime();
            String cmd = "cmd /c netstat -a";
            
            Process pr = rt.exec(cmd);
            
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    pr.getInputStream()));
            
            String line = null;
            
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                metainfo.append(line);
                
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    
    @Override
    public void psInfo() {
        
        try {
            Runtime rt = Runtime.getRuntime();
            String cmd = "cmd /c psinfo";
            
            Process pr = rt.exec(cmd);
            
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    pr.getInputStream()));
            
            String line = null;
            
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                metainfo.append(line);
                
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    
    @Override
    public void taskList() {
        
        try {
            Runtime rt = Runtime.getRuntime();
            String cmd = "cmd /c TASKLIST";
            
            Process pr = rt.exec(cmd);
            
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    pr.getInputStream()));
            
            String line = null;
            
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                metainfo.append(line);
                
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    
    @Override
    public void soundDev() {
        
        try {
            Runtime rt = Runtime.getRuntime();
            String cmd = "cmd /c WMIC";
            
            Process pr = rt.exec(cmd);
            OutputStream os = pr.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write("SOUNDDEV");
            bw.newLine();
            bw.flush();
            
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    pr.getInputStream()));
            
            String line = null;
            
            metainfo.setText(null);
            
            while ((line = input.readLine()) != null) {
                
                String message = line;
                
                metainfo.append(message);
                
                System.out.println(line);
            }
            
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void getUUID() {
        
        try {
            Runtime rt = Runtime.getRuntime();
            String cmd = "cmd /c WMIC";
            
            Process pr = rt.exec(cmd);
            OutputStream os = pr.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write("CSPRODUCT");
            bw.newLine();
            bw.flush();
            
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    pr.getInputStream()));
            
            String line = null;
            
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                metainfo.append(line.substring(14));
            }
            
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    
    @Override
    public void memoryChip() {
        
        try {
            Runtime rt = Runtime.getRuntime();
            String cmd = "cmd /c WMIC";
            
            Process pr = rt.exec(cmd);
            OutputStream os = pr.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write("MEMORYCHIP");
            bw.newLine();
            bw.flush();
            
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    pr.getInputStream()));
            
            String line = null;
            metainfo.setText(null);
            
            while ((line = input.readLine()) != null) {
                metainfo.append(line);
                
            }
            
            pr.destroy();
            os.close();
            bw.close();
            input.close();
            
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void CPU() {
        try {
            Runtime rt = Runtime.getRuntime();
            String cmd = "cmd /c WMIC";
            
            Process pr = rt.exec(cmd);
            OutputStream os = pr.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write("CPU");
            bw.newLine();
            bw.flush();
            
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    pr.getInputStream()));
            
            String line = null;
            metainfo.setText(null);
            
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                metainfo.append(line);
                
            }
            
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    
    @Override
    public void getSysVariables() {
        try {
            Runtime rt = Runtime.getRuntime();
            String cmd = "cmd /c WMIC";
            
            Process pr = rt.exec(cmd);
            OutputStream os = pr.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write("ENVIRONMENT");
            bw.newLine();
            bw.flush();
            
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    pr.getInputStream()));
            
            String line = null;
            metainfo.setText(null);
            
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                metainfo.append(line);
            }
            
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    
    @Override
    public void baseBoard() {
        try {
            Runtime rt = Runtime.getRuntime();
            String cmd = "cmd /c WMIC";
            
            Process pr = rt.exec(cmd);
            OutputStream os = pr.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write("BASEBOARD");
            bw.newLine();
            bw.flush();
            
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    pr.getInputStream()));
            
            String line = null;
            
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                metainfo.append(line.substring(14));
            }
            
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    
    @Override
    public void installedPrograms() {
        try {
            Runtime rt = Runtime.getRuntime();
            String cmd = "cmd /c WMIC";
            
            Process pr = rt.exec(cmd);
            OutputStream os = pr.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write("PRODUCT");
            bw.newLine();
            bw.flush();
            
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    pr.getInputStream()));
            
            String line = null;
            metainfo.setText(null);
            
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                metainfo.append(line);
            }
            
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    
    @Override
    public void ipConfig() {
        
        try {
            Runtime rt = Runtime.getRuntime();
            String cmd = "cmd /c ipconfig";
            
            Process pr = rt.exec(cmd);
            
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    pr.getInputStream()));
            
            String line = null;
            
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                metainfo.append(line);
            }
            
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            
        }
    }
    
    @Override
    public void userAccount() {
        try {
            Runtime rt = Runtime.getRuntime();
            String cmd = "cmd /c WMIC";
            
            Process pr = rt.exec(cmd);
            OutputStream os = pr.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write("USERACCOUNT");
            bw.newLine();
            bw.flush();
            
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    pr.getInputStream()));
            
            String line = null;
            
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                metainfo.append(line.substring(14));
            }
            
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}
