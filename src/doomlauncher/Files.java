/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doomlauncher;

import JConfig.JConfig;
import java.util.List;
import java.io.File;
import java.rmi.server.RemoteServer;
import java.util.ArrayList;

/**
 *
 * @author lul
 */
public class Files implements Constants{
    
    public JConfig dlConfig;
    
    
    
    private List<DLFile> engines = new ArrayList<DLFile>();
    private List<DLFile> iwads = new ArrayList<DLFile>();
    private List<DLFile> pwads = new ArrayList<DLFile>();
    
    public Files(String config){
           dlConfig=new JConfig(config);
        if(dlConfig.isConfigEmpty()){
           //Printer.print("Setting defauld config");
            dlConfig.setConfigValue(CFG_PR_ENGINE, "Set engine");
            dlConfig.setConfigValue(CFG_PR_IWAD_PATH, System.getProperty("user.home"));
            dlConfig.setConfigValue(CFG_PR_DEFAULT_FOLDER, System.getProperty("user.home"));
            dlConfig.setConfigValue(CFG_PR_PWADS, "");
        }
        
        readConfig();
        
       
    }

  

    public JConfig getConfig(){
        return dlConfig;
    }
    
    public void addEngine(File f){
        String separator=",";
        if(dlConfig.getConfigValue(CFG_PR_ENGINE).isEmpty()) separator="";
        dlConfig.setConfigValue(CFG_PR_ENGINE,dlConfig.getConfigValue(CFG_PR_ENGINE)+separator+f.getAbsolutePath());
        readConfig();
    }
    
    public void addPwad(File f){
        String separator=",";
        if(dlConfig.getConfigValue(CFG_PR_PWADS).isEmpty()) separator="";
        dlConfig.setConfigValue(CFG_PR_PWADS,dlConfig.getConfigValue(CFG_PR_PWADS)+separator+f.getAbsolutePath());
        readConfig();
    }
    
    public void addPwad(List<File> selectedFile) {
        String separator=",";
        if(dlConfig.getConfigValue(CFG_PR_PWADS).isEmpty()) separator="";
        for (File file : selectedFile) {
            dlConfig.setConfigValue(CFG_PR_PWADS,dlConfig.getConfigValue(CFG_PR_PWADS)+separator+file.getAbsolutePath());
            readConfig();
        }
    }
    
    public void setEngine(File f, int index){
        int i=0; String combination = "";
        for (String engine : dlConfig.getConfigValue(CFG_PR_ENGINE).split(",")) {
            if(i==index){
                combination=dlConfig.getConfigValue(CFG_PR_IWAD_PATH).replace(engine, f.getAbsolutePath());
            }
            i++;
        }
        dlConfig.setConfigValue(CFG_PR_ENGINE, combination);
        readConfig();
    }
    
    public void setIwad(File f){
        dlConfig.setConfigValue(CFG_PR_IWAD_PATH, f.getParent());
        readConfig();
    }
    
    public List<DLFile> getEngines(){
        return engines;
        
    }
    
    public List<DLFile> getIwads(){
        return iwads;
    }
   
    public List<DLFile> getPwads(){
        return pwads;
    }
    
    public DLFile getEngine(int index){
        return engines.get(index);
    }
    
    public DLFile getIwad(int  index) throws IndexOutOfBoundsException{
        if(iwads.isEmpty()) throw new IndexOutOfBoundsException("No iwad");
        return iwads.get(index);
    }
  
    public DLFile getPwad(int  index){
        return pwads.get(index);
    }
    
    public void removePwad(int index){
        pwads.remove(index);
        for (DLFile pwad : pwads) {
            dlConfig.setConfigValue(CFG_PR_PWADS, pwad.getPath());
        }
    }
    
    public void removeAllPwad(){
        pwads.clear();
        dlConfig.setConfigValue(CFG_PR_PWADS, "");
    }
    
    public void movePwadUp(int index) throws IndexOutOfBoundsException{
        if(index<1) throw new IndexOutOfBoundsException("Already on top");
        DLFile buff=pwads.get(index-1);
        pwads.set(index-1, pwads.get(index));
        pwads.set(index, buff);
         for (DLFile pwad : pwads) {
            dlConfig.setConfigValue(CFG_PR_PWADS, pwad.getPath());
        }
    }

    public void movePwadDown(int index) throws IndexOutOfBoundsException{
        if(index>=pwads.size()) throw new IndexOutOfBoundsException("Already on bottom");
        DLFile buff = pwads.get(index + 1);
        pwads.set(index + 1, pwads.get(index));
        pwads.set(index, buff);
        for (DLFile pwad : pwads) {
            dlConfig.setConfigValue(CFG_PR_PWADS, pwad.getPath());
        }
    }
    
        

    private void readConfig(){
        engines.clear();
        iwads.clear();
        pwads.clear();
        
        for (String engine : dlConfig.getConfigValue(CFG_PR_ENGINE).split(",")) {
            engines.add(new DLFile(engine));
        }
        if(dlConfig.getConfigValue(CFG_PR_PWADS).length()>0){
            for (String pwad : dlConfig.getConfigValue(CFG_PR_PWADS).split(",")) {
                pwads.add(new DLFile(pwad));
            }
        }
        if(dlConfig.getConfigValue(CFG_PR_IWAD_PATH).length()>0){
            for (String iwadDirectory : dlConfig.getConfigValue(CFG_PR_IWAD_PATH).split(",")) {
                for (File file : new File(iwadDirectory).listFiles()) {
                    for (String iwadName : IWAD_NAMES) {
                        if (iwadName.contentEquals(file.getName().toLowerCase())) {
                            iwads.add(new DLFile(file));
                        }
                    }
                }
            }
        }
    }

  

    
}
