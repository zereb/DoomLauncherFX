/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doomlauncher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
/**
 *
 * @author Plett Oleg
 */
public class DoomLauncher extends Application implements Constants{
    
    
    private static Stage pStage;

            
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void init(){
        new Printer();
    }
    
    @Override
    public void start(Stage stageDoomLauncher) throws IOException{
        Parent rootNode = FXMLLoader.load(getClass().getResource("/gui/DoomLauncher.fxml"));
        stageDoomLauncher.setTitle(TITLE_STRING);
        stageDoomLauncher.setMinHeight(WINDOW_HEIGHT);
        stageDoomLauncher.setMinWidth(WINDOW_WIDTH);
        setPrimaryStage(stageDoomLauncher);
        Scene sceneDoomLauncher = new Scene(rootNode, WINDOW_WIDTH, WINDOW_HEIGHT);
        sceneDoomLauncher.getStylesheets().add("gui/DoomLauncher.css");
        stageDoomLauncher.setScene(sceneDoomLauncher);
        stageDoomLauncher.show();
       
        
    }
    
    @Override
    public void stop(){
        
    }
    
     private void setPrimaryStage(Stage pStage) {
        DoomLauncher.pStage = pStage;
    }
  
    public static Stage getPrimaryStage() {
        return pStage;
    }

        
    
    
    
}
