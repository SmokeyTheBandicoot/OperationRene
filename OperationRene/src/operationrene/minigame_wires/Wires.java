/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.minigame_wires;

import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author Giuse
 */
public class Wires extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        Pane root = new Pane();
        Scene scene = new Scene (root, 450, 250);
        stage.setScene(scene);
        stage.setTitle("WIRES");
        stage.show();
        Random rand = new Random();
        ArrayList array = new ArrayList();
        Alert alertWRONG = new Alert(Alert.AlertType.INFORMATION);
        alertWRONG.setContentText("YOU CUT THE WRONG WIRE!");
        Alert alertOK = new Alert(Alert.AlertType.INFORMATION);
        alertOK.setContentText("YOU HAVE SUCCESSFULLY COMPLETED THE MINIGAME!");
        Image scissors = new Image("file:src/operationrene/minigame_wires/images/scissor.png");
        Image vite = new Image("file:src/operationrene/minigame_wires/images/vite.png");
        Image circuit = new Image("file:src/operationrene/minigame_wires/images/circuit.png");
        
         //SCISSORS CURSOR
        ImageCursor cursor = new ImageCursor(scissors);
        scene.setCursor(cursor);
        
        //BACKGROUND
        BackgroundImage bi;
        bi = new BackgroundImage(circuit, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(bi));

        int linewidth=7;
        int xstart=50;
        int ystart=100;
        int xend= 400;
        int i = 0;


        Color [] colors = new Color[4];
        colors[0]= Color.RED;
        colors[1]= Color.WHITE;
        colors[2]= Color.BLUE;
        colors[3]=Color.YELLOW;
        int w= rand.nextInt(2);
        
        if(w==0){
        Line [] arrayline = new Line[4];
        ArrayList arrayred = new ArrayList();
        ArrayList arrayblue = new ArrayList();
        ArrayList arrayyellow = new ArrayList();
        for(i=0; i<4; i++){
            
          Line line = new Line(xstart, ystart, xend, ystart );
          Circle circlesx = new Circle(xstart, ystart, 10);
           Circle circledx = new Circle(xend, ystart, 10);
           circlesx.setFill(new ImagePattern(vite));
           circledx.setFill(new ImagePattern(vite));
            int c = rand.nextInt(colors.length);
            line.setStrokeWidth(linewidth);
            line.setStroke(colors[c]);
            array.add(colors[c]);
            if(colors[c] == Color.RED){
            arrayred.add(i);
            }
            if(colors[c] == Color.BLUE){
             arrayblue.add(i);
            }
            if(colors[c] == Color.YELLOW){
             arrayyellow.add(i);
            }
            arrayline[i] = line;
            root.getChildren().addAll(line, circlesx, circledx);
            ystart=ystart+40;
        }
            if(arrayred.size()>1){ //SE CI SONO PIU' CAVI ROSSI
            arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t) {
                    alertWRONG.show();
                    stage.close();
                }
   
            }
            );
            arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t) {
                    if((arrayred.size()-1) == 1){
                    alertWRONG.show();
                    stage.close();
                    } else{
                    alertOK.show();
                    stage.close();
                    }
                    
                }
   
            }
            );
            arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t) {
                    if((arrayred.size()-1) == 2){
                    alertWRONG.show();
                    stage.close();
                    } else{
                    alertOK.show();
                    stage.close();
                    }
                    
                }
   
            }
            );
            arrayline[3].setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t) {
                    if((arrayred.size()-1) == 3){
                    alertWRONG.show();
                    stage.close();
                    } else{
                    alertOK.show();
                    stage.close();
                    }
                    
                }
   
            }
            );
            
            } else if(arrayline[3].getStroke() == Color.YELLOW && arrayred.isEmpty()){ //SE L'ULTIMO CAVO E' GIALLO E NON CI SONO CAVI ROSSI
                arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                         alertOK.show();
                         stage.close();
                        
                    }
                });
                arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                    
                         alertWRONG.show();
                         stage.close();
                    }
                    
                });
                arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                        
                         alertWRONG.show();
                         stage.close();
                    }
                
                
                });
                arrayline[3].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                        
                         alertWRONG.show();
                         stage.close();
                    }
                
                
                });
            
            }
            else if(arrayblue.size()==1){ //SE C'E' UN SOLO CAVO BLUE
                arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                         alertOK.show();
                         stage.close();
                        
                    }
                });
                arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                    
                         alertWRONG.show();
                         stage.close();
                    }
                    
                });
                arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                        
                         alertWRONG.show();
                         stage.close();
                    }
                
                
                });
                arrayline[3].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                        
                         alertWRONG.show();
                         stage.close();
                    }
                
                
                });
            
            }
            else if (arrayyellow.size()>1){
                arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                         alertWRONG.show();
                         stage.close();
                        
                    }
                });
                arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                    
                         alertWRONG.show();
                         stage.close();
                    }
                    
                });
                arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                        
                         alertWRONG.show();
                         stage.close();
                    }
                
                
                });
                arrayline[3].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                        
                         alertOK.show();
                         stage.close();
                    }
                
                
                });
            
            }
            else{
                arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                         alertWRONG.show();
                         stage.close();
                        
                    }
                });
                arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                    
                         alertOK.show();
                         stage.close();
                    }
                    
                });
                arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                        
                         alertWRONG.show();
                         stage.close();
                    }
                
                
                });
                arrayline[3].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                        
                         alertWRONG.show();
                         stage.close();
                    }
                
                
                });
            
            }
           
        
        }
        
        else if (w==1){
            
          Line [] arrayline = new Line[3];

            for(i=0; i<3; i++){
           Line line = new Line(xstart, ystart, xend, ystart );
           Circle circlesx = new Circle(xstart, ystart, 10);
           Circle circledx = new Circle(xend, ystart, 10);
           circlesx.setFill(new ImagePattern(vite));
           circledx.setFill(new ImagePattern(vite));
            int c = rand.nextInt(colors.length-1);
            line.setStrokeWidth(linewidth);
            line.setStroke(colors[c]);
            array.add(colors[c]);
            arrayline[i] = line;
            root.getChildren().addAll(line, circlesx, circledx);
            ystart=ystart+40;
            }
        
            if (!array.contains(Color.RED)){
                arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                         alertWRONG.show();
                         stage.close();
                        
                    }
                });
                arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                    
                         alertOK.show();
                         stage.close();
                    }
                    
                });
                arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                        
                         alertWRONG.show();
                         stage.close();
                    }
                
                
                });
                     
                     }
            else if (arrayline[2].getStroke() == Color.WHITE){
                arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                     
                         alertWRONG.show();
                         stage.close();
                        
                    }
                });
                arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                      
                         alertWRONG.show();
                         stage.close();
                        
                    }
                    
                });
                arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                        
                         alertOK.show();
                         stage.close();
                    }
                
                
                });
                
                
            }
            else if (((arrayline[0].getStroke() == Color.BLUE) && 
                         ((arrayline[1].getStroke() == Color.BLUE) || (arrayline[2].getStroke() == Color.BLUE))) ||
                         ((arrayline[1].getStroke()== Color.BLUE) && (arrayline[2].getStroke() == Color.BLUE))){
                if(arrayline[0].getStroke()== Color.BLUE && arrayline[1].getStroke() == Color.BLUE){
                    
                     arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                       
                         alertWRONG.show();
                         stage.close();
                        
                    }
                });
                arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                      
                         alertOK.show();
                         stage.close();
                        
                    }
                    
                });
                arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
            
                         alertWRONG.show();
                         stage.close();
                    }
                
                
                });
                
                    
                }
                else if(arrayline[1].getStroke()== Color.BLUE && arrayline[2].getStroke() == Color.BLUE){
                arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                  
                         alertWRONG.show();
                         stage.close();
                        
                    }
                });
                arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                         alert.setContentText("YOU CUT THE WRONG WIRE!");
                         alertWRONG.show();
                         stage.close();
                        
                    }
                    
                });
                arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {

                         alertOK.show();
                         stage.close();
                    }
                
                
                });
                }
            
            }
            else {
                arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {

                         alertWRONG.show();
                         stage.close();
                        
                    }
                });
                arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {

                         alertWRONG.show();
                         stage.close();
                        
                    }
                    
                });
                arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
     
                         alertOK.show();
                         stage.close();
                    }
                
                
                });
            
            
            }
            
        }
        
       
    }
    
}
