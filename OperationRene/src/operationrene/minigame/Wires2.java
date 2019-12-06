package operationrene.minigame;

import java.awt.Label;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.plaf.RootPaneUI;
import static java.lang.Thread.sleep;

/**
 *
 * @author Giuse
 */
public class Wires2 extends Application {
    
    private final String PATH = "C:\\Users\\Rickma\\Documents\\NetBeansProjects\\OperationRene\\OperationRene\\assets\\sprites\\minigames\\";

    /**
     * @param args the command line arguments
     */
        Thread t;
        Pane root = new Pane();
        Integer time = 30;
        TextField ltime=new TextField(time.toString());
        Line[] arrayline = new Line[4];
        ArrayList array = new ArrayList();
        ArrayList<Integer> arrayred = new ArrayList<Integer>();
        ArrayList<Integer> arrayblue = new ArrayList<Integer>();
        ArrayList<Integer> arrayyellow = new ArrayList<Integer>();
        Boolean risolto = false;
    
        
        /*
   public void run(){
        try {
            launch();
        } catch (Exception ex) {
            Logger.getLogger(Wires2.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
*/
        

    @Override
    public void start(Stage stage) throws Exception {

       // Pane root = new Pane();
        Random rand = new Random();
        
        ltime.setEditable(false);
        ltime.setLayoutX(0);
        ltime.setLayoutY(0);
      
        root.getChildren().add(ltime);
        Scene scene = new Scene(root, 450, 250);
        stage.setScene(scene);
        stage.setTitle("WIRES2");
        stage.setResizable(false);
        stage.show();
        t = new Thread() {
            @Override
            public void run() {
                 while (time >= 0 && risolto==false){
                try {
                    sleep(1000);
                    ltime.setText((time--).toString());
                } catch (InterruptedException ex) {
                }
            }   
                if (!risolto){
                JOptionPane.showMessageDialog(null, 
                              "HAI PERSO", 
                              "HAI PERSO", 
                              JOptionPane.WARNING_MESSAGE);}
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        // if you change the UI, do it here !
                        stage.close();
                    }
                });
                Thread.currentThread().interrupt();
                
            }
        };
        t.start();
        Image scissors = new Image("file:"+PATH+"scissor.png");
        Image vite = new Image("file:"+PATH+"screw.png");
        Image circuit = new Image("file:"+PATH+"circuit.png");

        //SCISSORS CURSOR
        ImageCursor cursor = new ImageCursor(scissors);
        scene.setCursor(cursor);

        //BACKGROUND
        BackgroundImage bi;
        bi = new BackgroundImage(circuit, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(bi));

        int linewidth = 7;
        int xstart = 50;
        int ystart = 100;
        int xend = 400;

        Color[] colors = createArrayColor();
        int w = rand.nextInt(2);
        
        //SE LA VARIABILE W GENERATA CASUALEMNTE VALE 0, ALLORA GENERO UN CIRCUITO CON 4 CAVI
        if (w == 0) {
            
            createRandomCircuit(colors, vite, xstart, ystart, xend, linewidth, w );
            
            if (arrayred.size() > 1 ) { //SE CI SONO PIU' CAVI ROSSI
                arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        handleWRONG(stage,arrayline[0]);
                    }

                }
                );
                arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        if (arrayline[1].getStroke() == Color.RED && arrayred.get(arrayred.size() - 1) == 1) {
                            handleOK (stage);
                            
                        } else {
                         handleWRONG(stage,arrayline[1]);

                        }

                    }

                }
                );
                arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        if (arrayline[2].getStroke() == Color.RED && arrayred.get(arrayred.size() - 1) == 2) {
                            handleOK (stage);
                        } else {
                            handleWRONG(stage,arrayline[2]);
                        }

                    }

                }
                );
                arrayline[3].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        if (arrayline[3].getStroke() == Color.RED && arrayred.get(arrayred.size() - 1) == 3) {
                            handleOK (stage);
                        } else {
                            handleWRONG(stage,arrayline[3]);
                        }

                    }

                }
                );

            } else if (arrayline[3].getStroke() == Color.YELLOW && arrayred.isEmpty()) { //SE L'ULTIMO CAVO E' GIALLO E NON CI SONO CAVI ROSSI
                arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        handleOK (stage);

                    }
                });
                arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleWRONG(stage,arrayline[1]);
                    }

                });
                arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleWRONG(stage,arrayline[2]);
                    }

                });
                arrayline[3].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleWRONG(stage,arrayline[3]);
                    }

                });

            } else if (arrayblue.size() == 1) { //SE C'E' UN SOLO CAVO BLUE
                arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        handleOK (stage);
                    }
                });
                arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleWRONG(stage,arrayline[1]);
                    }

                });
                arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleWRONG(stage,arrayline[2]);
                    }

                });
                arrayline[3].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleWRONG(stage,arrayline[3]);
                    }

                });

            } else if (arrayyellow.size() > 1) { //SE C'è PIU DI UN CAVO GIALLO
                arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        handleWRONG(stage,arrayline[0]);
                    }
                });
                arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleWRONG(stage,arrayline[1]);
                    }

                });
                arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleWRONG(stage,arrayline[2]);
                    }

                });
                arrayline[3].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleOK (stage);

                    }

                });

            } else {
                arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        handleWRONG(stage,arrayline[0]);
                    }
                });
                arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleOK (stage);
                    }

                });
                arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleWRONG(stage,arrayline[2]);
                    }

                });
                arrayline[3].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleWRONG(stage,arrayline[3]);
                    }

                });

            }

        } else if (w == 1) { //SE LA VARIABILE W VALE 1, ALLORA CREO UN CIRCUITO CON 3 CAVI

            createRandomCircuit(colors, vite, xstart, ystart, xend, linewidth, w);
            
            if (!array.contains(Color.RED)) { //SE NON CI SONO CAVI ROSSI
                arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        handleWRONG(stage,arrayline[0]);
                    }
                });
                arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleOK (stage);
                    }

                });
                arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleWRONG(stage,arrayline[2]);
                    }

                });

            } else if (arrayline[2].getStroke() == Color.WHITE) {
                arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                       handleWRONG(stage,arrayline[0]);

                    }
                });
                arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleWRONG(stage,arrayline[1]);
                    }

                });
                arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleOK (stage);
                    }

                });

            } else if (((arrayline[0].getStroke() == Color.BLUE)
                    && ((arrayline[1].getStroke() == Color.BLUE) || (arrayline[2].getStroke() == Color.BLUE)))
                    || ((arrayline[1].getStroke() == Color.BLUE) && (arrayline[2].getStroke() == Color.BLUE))) {

                if (arrayline[0].getStroke() == Color.BLUE && arrayline[1].getStroke() == Color.BLUE && arrayline[2].getStroke() != Color.BLUE) {

                    arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent t) {

                            handleWRONG(stage,arrayline[0]);
                        }
                    });
                    arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent t) {

                            handleOK (stage);

                        }

                    });
                    arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent t) {

                            handleWRONG(stage,arrayline[2]);
                        }

                    });

                } else if (arrayline[2].getStroke() == Color.BLUE) {
                    arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent t) {

                            handleWRONG(stage,arrayline[0]);

                        }
                    });
                    arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent t) {

                            handleWRONG(stage,arrayline[1]);
                        }

                    });
                    arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent t) {

                            handleOK (stage);
                        }

                    });
                }

            } else {
                arrayline[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleWRONG(stage,arrayline[0]);
                    }
                });
                arrayline[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleWRONG(stage,arrayline[1]);
                    }

                });
                arrayline[2].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        handleOK (stage);
                    }

                });

            }

        }

    }
    //FUNZIONE CHE CREA UN ARRAY CONTENTENTE I COLORUI ROSSO, BIANCO, BLUE E GIALLO
    public Color[] createArrayColor(){
    Color[] colors = new Color[4];
        colors[0] = Color.RED;
        colors[1] = Color.WHITE;
        colors[2] = Color.BLUE;
        colors[3] = Color.YELLOW;
        return colors;
    }
    
   //FUNZIONE CHE CREA UN CIRCUITO RANDOM CON 3 O 4 CAVI
    public void createRandomCircuit(Color[] colors, Image vite, int xstart, int ystart, int xend, int linewidth, int w){
        
             Random rand = new Random();
             int i=0;
             int num; 
             if (w==0){ // se sono 4 cavi
                num=4;
                  }
                else { // se sono 3 cavi
                 num=3;
                }
            for (i = 0; i < num; i++) {

                Line line = new Line(xstart, ystart, xend, ystart);
                Circle circlesx = new Circle(xstart, ystart, 10);
                Circle circledx = new Circle(xend, ystart, 10);
                circlesx.setFill(new ImagePattern(vite));
                circledx.setFill(new ImagePattern(vite));
                int c = rand.nextInt(colors.length);
                line.setStrokeWidth(linewidth);
                line.setStroke(colors[c]);
                if(w==0){
                if (colors[c] == Color.RED) {
                    arrayred.add(i);
                }
                if (colors[c] == Color.BLUE) {
                    arrayblue.add(i);
                }
                if (colors[c] == Color.YELLOW) {
                    arrayyellow.add(i);
                }
                }else{
                array.add(colors[c]);
                }
                arrayline[i] = line;
                root.getChildren().addAll(line, circlesx, circledx);
                ystart = ystart + 40;
                }
                }

    //FUNZIONE CHE LANCIA UN AVVISO DI ERRORE E CHIUDE L'APPLICAZIONE
    public void handleWRONG (Stage stage,Line line){
        Alert alertWRONG = new Alert(Alert.AlertType.INFORMATION);
        alertWRONG.setContentText("YOU CUT THE WRONG WIRE!");
        this.time-=10;
        root.getChildren().remove(line);
        //alertWRONG.show();
        //stage.close();
    }
     //FUNZIONE CHE LANCIA UN AVVISO DI SUCCESSO E CHIUDE L'APPLICAZIONE
    public void handleOK(Stage stage){
        Alert alertOK = new Alert(Alert.AlertType.INFORMATION);
        alertOK.setContentText("YOU HAVE SUCCESSFULLY COMPLETED THE MINIGAME!");
        risolto= true;
        stage.close();
        alertOK.show();
        
    }

}