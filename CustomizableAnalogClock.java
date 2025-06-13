/**
   Name: Krishen Persand
   Assignment: Final Project
   Due Date: Thursday, December 14, 11:59pm

   Grade report:
   10/10: Project proposal (submitted, discussed, approved)
   20/20: Depth/Complexity
   10/10: Comprehensive (contains all expected/standard features)
   30/30: Functionality (everything works as described)
   20/20: Aesthetics (design, alignment, graphics, fonts, colors, etc.)
   10/10: Demonstration during Final Exam period

   Comments:
   All conditions met from criteria to earn a 100.

   Total estimated grade: 100/100
   
 */

import javafx.application.*;
import javafx.stage.*;
import javafx.stage.FileChooser.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.beans.value.*;
import javafx.event.*; 
import javafx.animation.*;
import javafx.geometry.*;
import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;


/**
 *   
 *   JavaFX API: https://openjfx.io/javadoc/11/
 */ 
public class CustomizableAnalogClock extends Application 
{
    // run the application
    public static void main(String[] args) 
    {
        try
        {
            // creates Stage, calls the start method
            launch(args);
        }
        catch (Exception error)
        {
            error.printStackTrace();
        }
        finally
        {
            System.exit(0);
        }
    }

    // Application is an abstract class,
    //  requires the method: public void start(Stage s)
    public void start(Stage mainStage) 
    {
        // set the text that appears in the title bar
        mainStage.setTitle("Analog Clock");
        mainStage.setResizable(true);

        // layout manager: organize window contents
        BorderPane root = new BorderPane();

        // set font size of objects
        root.setStyle(  "-fx-font-size: 12;"  );

        // May want to use a Box to add multiple items to a region of the screen
        VBox box = new VBox();
        // add padding/margin around area
        box.setPadding( new Insets(16) );
        // add space between objects
        box.setSpacing( 5 );
        // set alignment of objects (default: Pos.TOP_LEFT)
        box.setAlignment( Pos.CENTER );
        // Box objects store contents in a list
        List<Node> boxList = box.getChildren();
        // if you do not want to use the VBox in the central area,
        //   comment out the following line of code:
        root.setCenter( box );
        
        
        // Scene: contains window content
        // need to specify layout manager. optionally: width window, height window
        Scene mainScene = new Scene(root);
        // attach/display Scene on Stage (window)
        mainStage.setScene( mainScene );

        // custom application code below -------------------
        
        // window icon
        Image windowIcon = new Image("icons/time.png");
        mainStage.getIcons().add( windowIcon );
        
        // MenuBar contains menu objects
        MenuBar menuBar = new MenuBar();
        root.setTop( menuBar );
        // menus on menu bar
        Menu fileMenu = new Menu("File");
        fileMenu.setStyle("-fx-font-size: 14;");
        // add these menus to the list of menus managed by menu bar
        menuBar.getMenus().add(fileMenu);
        // help menu items
        MenuItem about = new MenuItem("About");
        MenuItem quit = new MenuItem("Quit");
        fileMenu.getItems().addAll( about, quit );
        
        // add icons and key codes to MenuItems 
        about.setGraphic( new ImageView( new Image("icons/information.png") ) );
        quit.setGraphic( new ImageView( new Image("icons/door_out.png") ) ); 
        quit.setAccelerator(
         new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN) );
        
        // add interactivity to each MenuItem
        about.setOnAction(
            (ActionEvent event) ->
            {
                Alert aboutAlert = new Alert(AlertType.INFORMATION);
                aboutAlert.setTitle("About CAC");
                aboutAlert.setHeaderText("Customizable Analog Clock");
                aboutAlert.setContentText(
                    "Developed by Krishen Persand.\nVersion 1.0\n\nThanks for reading!");
                aboutAlert.showAndWait();
            }
        );
        
        quit.setOnAction(
            (ActionEvent event) ->
            {
                mainStage.close();
            }
        );

        // row 1
        Canvas canvas = new Canvas(560,520);
        GraphicsContext context = canvas.getGraphicsContext2D();
        boxList.add( canvas );
        
        // background color
        Label backgroundColorLabel = new Label("Background  \n\tColor:  ");
        ColorPicker backgroundColorPicker = new ColorPicker();
        backgroundColorPicker.setValue( Color.WHITE );
        backgroundColorPicker.setPrefWidth(130);
        
        // face color
        Label faceColorLabel = new Label("   Face  \n   Color:  ");
        ColorPicker faceColorPicker = new ColorPicker();
        faceColorPicker.setValue( Color.PINK );
        faceColorPicker.setPrefWidth(130);
        
        // border color
        Label borderColorLabel = new Label("   Border  \n    Color: ");
        ColorPicker borderColorPicker = new ColorPicker();
        borderColorPicker.setValue(Color.RED);
        borderColorPicker.setPrefWidth(130);
        
        // row 2
        HBox row2 = new HBox();
        row2.setAlignment( Pos. CENTER );
        row2.getChildren().addAll( backgroundColorLabel, backgroundColorPicker, faceColorLabel, 
                                         faceColorPicker, borderColorLabel, borderColorPicker );
        box.getChildren().add(row2);
        
        // border size 
        Label borderSizeLabel = new Label("Border Size: ");
        Slider borderSizeSlider = new Slider();
        borderSizeSlider.setMin(1);
        borderSizeSlider.setMax(15);
        borderSizeSlider.setValue(3);
        borderSizeSlider.setShowTickLabels(true);
        borderSizeSlider.setShowTickMarks(true);
        borderSizeSlider.setMajorTickUnit(2);
        borderSizeSlider.setPrefWidth(312);
        
        // row 3
        HBox row3 = new HBox();
        row3.setAlignment( Pos. CENTER );
        row3.getChildren().addAll( borderSizeLabel, borderSizeSlider );
        box.getChildren().add(row3);
        
        // second hand color
        Label secondHandColorLabel = new Label("Second Hand  \n\t Color: ");
        ColorPicker secondHandColorPicker = new ColorPicker();
        secondHandColorPicker.setValue(Color.RED);
        secondHandColorPicker.setPrefWidth(130);
        
        // minutes and hour hand color
        Label minAndHrHandColorLabel = new Label("   Minute & Hour  \n      Hand Color: ");
        ColorPicker minAndHrHandColorPicker = new ColorPicker();
        minAndHrHandColorPicker.setValue( Color.LIME );
        minAndHrHandColorPicker.setPrefWidth(130);
        
        // row 4
        HBox row4 = new HBox();
        row4.setAlignment( Pos. CENTER );
        row4.getChildren().addAll( secondHandColorLabel, secondHandColorPicker, 
                                        minAndHrHandColorLabel, minAndHrHandColorPicker );
        box.getChildren().add(row4);
        
        // hand size
        Label handSizeLabel = new Label("Hand Size: ");
        Slider handSizeSlider = new Slider();
        handSizeSlider.setMin(1);
        handSizeSlider.setMax(10);
        handSizeSlider.setValue(1);
        handSizeSlider.setShowTickLabels(true);
        handSizeSlider.setShowTickMarks(true);
        handSizeSlider.setMajorTickUnit(1);
        handSizeSlider.setPrefWidth(312);
        
        // row 5
        HBox row5 = new HBox();
        row5.setAlignment( Pos. CENTER );
        row5.getChildren().addAll( handSizeLabel, handSizeSlider );
        box.getChildren().add(row5);
        
        // time color
        Label timeColorLabel = new Label("  Time  \n  Color:  ");
        ColorPicker timeColorPicker = new ColorPicker();
        timeColorPicker.setValue( Color.LIME );
        timeColorPicker.setPrefWidth(130);
        
        // date color
        Label dateColorLabel = new Label("  Date  \n  Color:  ");
        ColorPicker dateColorPicker = new ColorPicker();
        dateColorPicker.setValue( Color.RED );
        dateColorPicker.setPrefWidth(130);
        
        // clock # color
        Label clockNumColorLabel = new Label("  Clock #  \n    Color: ");
        ColorPicker clockNumColorPicker = new ColorPicker();
        clockNumColorPicker.setValue( Color.BLACK );
        clockNumColorPicker.setPrefWidth(130);
        
        // row 6
        HBox row6 = new HBox();
        row6.setAlignment( Pos. CENTER );
        row6.getChildren().addAll( timeColorLabel, timeColorPicker, dateColorLabel, 
                                dateColorPicker, clockNumColorLabel, clockNumColorPicker );
        box.getChildren().add(row6);
        
        //  clock #'s size
        Label clockNumSizeLabel = new Label("Clock # Size: ");
        Slider clockNumSizeSlider = new Slider();
        clockNumSizeSlider.setMin(1);
        clockNumSizeSlider.setMax(4);
        clockNumSizeSlider.setValue(1);
        clockNumSizeSlider.setShowTickLabels(true);
        clockNumSizeSlider.setShowTickMarks(true);
        clockNumSizeSlider.setMajorTickUnit(1);
        clockNumSizeSlider.setPrefWidth(312);
        
        // row 7
        HBox row7 = new HBox();
        row7.setAlignment( Pos. CENTER );
        row7.getChildren().addAll( clockNumSizeLabel, clockNumSizeSlider );
        box.getChildren().add(row7);
               
        AnimationTimer timer = new AnimationTimer()
        {
            public void handle(long time)
            {
                // clear the canvas with a white background
                context.setFill( backgroundColorPicker.getValue() );
                context.fillRect(0,0, 560,520);
                
                // automatic changes when color is clicked
                EventHandler<ActionEvent> renderHandler = 
                    (event) ->
                {
                    // set background color based on GUI control values
                    context.setFill( backgroundColorPicker.getValue() );
                    // set face color based on GUI control values
                    context.setFill( faceColorPicker.getValue() );
                    // set clock border color based on GUI control values
                    context.setStroke( borderColorPicker.getValue() );
                    // set second hand color based on GUI control values
                    context.setFill( secondHandColorPicker.getValue() );
                    // set minute and hour hand color based on GUI control values
                    context.setFill( minAndHrHandColorPicker.getValue() );
                    // set clock # color based on GUI control values
                    context.setFill( clockNumColorPicker.getValue() );
                    // set time color based on GUI control values
                    context.setFill( timeColorPicker.getValue() );
                    // set date based on GUI control values
                    context.setFill( dateColorPicker.getValue() );
                };
                
                // automatic changes when slider is moved
                ChangeListener<Object> renderListener = 
                    (property, oldValue, newValue) ->
                {
                    // set border size (line width) based on a slider
                    context.setLineWidth( borderSizeSlider.getValue() );
                    // set hand size (line width) based on a slider
                    context.setLineWidth( handSizeSlider.getValue() );
                    // set hand size (line width) based on a slider
                    context.setLineWidth( clockNumSizeSlider.getValue() );
                };
            
                backgroundColorPicker.setOnAction( renderHandler );
                faceColorPicker.setOnAction( renderHandler );
                borderColorPicker.setOnAction( renderHandler );
                secondHandColorPicker.setOnAction( renderHandler );
                minAndHrHandColorPicker.setOnAction( renderHandler );
                clockNumColorPicker.setOnAction( renderHandler );
                timeColorPicker.setOnAction( renderHandler );
                dateColorPicker.setOnAction( renderHandler );
                borderSizeSlider.valueProperty().addListener( renderListener );
                handSizeSlider.valueProperty().addListener( renderListener );
                clockNumSizeSlider.valueProperty().addListener( renderListener );
                
                // clock circle
                context.setStroke( borderColorPicker.getValue() );
                context.setLineWidth( borderSizeSlider.getValue() );
                context.strokeOval(80,20, 400,400);
                context.setFill( faceColorPicker.getValue() );
                context.fillOval(80, 20, 400, 400);
                
                // convert seconds to degrees (and then to radians)
                // 0 s -> 90 degrees 
                // 15 s -> 0 degrees
                // 30 s -> -90 degrees
                // 45 s -> -180 degrees
                // 60 s -> -270 degrees
                
                // conversion: -90 degrees / 15 seconds = -6 deg/sec
                LocalDateTime now = LocalDateTime.now();
                
                // draw numbers
                for (int i = 1; i <= 12; i++) 
                {
                    double clockNumRadians = Math.toRadians(90 - i * 30);
                    double x = 280 + 175 * Math.cos(clockNumRadians);
                    double y = 220 - 175 * Math.sin(clockNumRadians);
                    context.setFont(new Font("Times New Roman", 30 ));
                    context.setFill( clockNumColorPicker.getValue() );
                    context.fillText(Integer.toString(i), x - 8, y + 10);
                    context.setLineWidth( clockNumSizeSlider.getValue() );
                    context.setStroke( clockNumColorPicker.getValue() );
                    context.strokeText(Integer.toString(i), x - 8, y + 10);
                }
                
                // get seconds, minutes, and hours 
                double hours = now.getHour();
                double minutes = now.getMinute();
                double seconds = now.getSecond();
                
                double hrDegrees = 90 - ( (hours % 12 + minutes / 60.0) * 30 );
                double hrRadians = Math.toRadians(hrDegrees);
                
                // hour hand
                context.setStroke( minAndHrHandColorPicker.getValue() );
                context.setLineWidth( handSizeSlider.getValue() + 4 );
                context.strokeLine(280,220,  
                280+120*Math.cos(hrRadians), 220-120*Math.sin(hrRadians) );
                
                double minDegrees = 90 - 6 * (minutes + seconds / 60.0);
                double minRadians = Math.toRadians(minDegrees);
                
                // minute hand
                context.setStroke( minAndHrHandColorPicker.getValue() );
                context.setLineWidth( handSizeSlider.getValue() + 4 );
                context.strokeLine(280,220,  
                280+192*Math.cos(minRadians), 220-192*Math.sin(minRadians) );
                
                seconds += now.getNano() / 1000000000.0; // get decimal part of seconds
                double degrees = 90 - 6 * seconds;
                double secRadians = Math.toRadians(degrees);
                
                // second hand
                context.setStroke( secondHandColorPicker.getValue() );
                context.setLineWidth( handSizeSlider.getValue() );
                context.strokeLine(280,220,  
                280+195*Math.cos(secRadians), 220-195*Math.sin(secRadians) );
                
                // opposite side of second hand
                context.setStroke( secondHandColorPicker.getValue() );
                context.strokeLine(280,220,  
                280+20*Math.cos(secRadians + Math.PI), 220-20*Math.sin(secRadians + Math.PI) );
                
                // circle in middle of clock
                context.setFill( Color.BLACK );
                context.fillOval(270,210,20,20);
                
                // get seconds, minutes, and hours 
                int hours2 = now.getHour();
                int minutes2 = now.getMinute();
                int seconds2 = now.getSecond();
                
                // format date
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
                String date = now.format(dateFormatter);
                
                // declare and initialize amPM
                String amPM = "";
                
                // change 24-hour time to 12-hour time and set am and pm
                if ( hours2 == 0 || hours2 == 24 )
                {
                    hours2 = 12;
                    amPM = "am";
                }
                else if (hours >= 12)
                {
                    hours2 -= 12;
                    amPM = "pm";
                }
                else
                {
                    amPM = "am";
                }
                
                // declare and initialize formattedTime
                String formattedTime = "";
                
                // format time to be displayed in Label
                if (seconds2 < 10 || minutes2 < 10)
                {
                    formattedTime = String.format("%d:%02d:%02d %s", hours2, minutes2, seconds2, amPM);
                }
                else
                {
                    formattedTime = hours2 + ":" + minutes2 + ":" + seconds2 + " " + amPM;
                }
                
                // time text
                context.setFont( new Font("Phosphate Solid", 48) );
                context.setFill( timeColorPicker.getValue() );
                context.fillText(formattedTime, 175, 470);
                
                // date text
                context.setFont( new Font("Phosphate Solid", 24) );
                context.setFill( dateColorPicker.getValue() );
                context.fillText(date, 130, 500);
            }
        };
        
        timer.start();
        
        // custom application code above -------------------

        // after adding all content, make the Stage visible
        mainStage.show();
        // automatically resize stage (window) to scene contents
        mainStage.sizeToScene();
    }
}
