/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doomlauncher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import JConfig.JConfig;
import java.io.IOException;
import java.util.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
/**
 *
 * @author Plett Oleg
 */
public class DoomLauncher extends Application implements Constants{
    
    public static JConfig doomLauncherJConfig;
    public static DoomLauncher dl;
    
        
            
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void init(){
        doomLauncherJConfig=new JConfig(CONFIG_NAME);
        if(doomLauncherJConfig.isConfigEmpty()){
            doomLauncherJConfig.setConfigValue(CONFIG_PROPERTY_ENGINE, "Set engine");
            doomLauncherJConfig.setConfigValue(CONFIG_PROPERTY_IWAD_PATH, "Set Iwad");
        }
    }
    
    @Override
    public void start(Stage stageDoomLauncher) throws IOException{
        Parent rootNode = FXMLLoader.load(getClass().getResource("/gui/DoomLauncher.fxml"));
        stageDoomLauncher.setTitle(TITLE_STRING);
        stageDoomLauncher.setMinHeight(WINDOW_HEIGHT);
        stageDoomLauncher.setMinWidth(WINDOW_WIDTH);
        Scene sceneDoomLauncher = new Scene(rootNode, WINDOW_WIDTH, WINDOW_HEIGHT);
        stageDoomLauncher.setScene(sceneDoomLauncher);
        stageDoomLauncher.show();
       
        
    }
    
    @Override
    public void stop(){
        
    }
        
    
    
    
}