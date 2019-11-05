import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.TextInputControl;

public class Painter extends Application {
  
  static private final int defaultPenSize = 10;
  private Stage primaryStage;
  private ColorPicker colorPicker = new ColorPicker(Color.BLACK);
  private Slider slider = new Slider(1, 101, defaultPenSize);
  private TextField textField = new TextField(Integer.toString(defaultPenSize));
   
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    
    Canvas canvas = new Canvas (500,500); 
    canvas.setOnMouseDragged(new BasicClickHandler());
    canvas.setOnMousePressed(new BasicClickHandler());
    
    slider.setShowTickMarks(true);
    slider.setShowTickLabels(true);
    slider.setMajorTickUnit(10);
    slider.setBlockIncrement(1);
    slider.valueProperty().addListener(new BasicChangeListener());
    
    textField.setPrefColumnCount(4);
    textField.setEditable(false);
    
    HBox hboxPane = new HBox();
    hboxPane.setAlignment(Pos.CENTER);
    hboxPane.getChildren().add(slider);
    hboxPane.getChildren().add(textField);
   
    BorderPane borderPane = new BorderPane();   
    borderPane.setCenter(canvas); 
    borderPane.setTop(colorPicker);
    borderPane.setRight(hboxPane);
    
    Scene scene = new Scene(borderPane);      
    primaryStage.setScene(scene);             
    primaryStage.show();   
    
  } 
 
 private class BasicClickHandler implements EventHandler<MouseEvent> {
  
    public void handle(MouseEvent e) {
    Canvas b = (Canvas)e.getSource();
    b.getGraphicsContext2D().setFill(colorPicker.getValue());
    b.getGraphicsContext2D().fillOval(e.getX() - 0.5 * slider.getValue(), e.getY() - 0.5 * slider.getValue(), slider.getValue(), slider.getValue());
    }
  }
 
 private class BasicChangeListener<Integer> implements ChangeListener<Integer>{
   
   @Override
   public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue){
     textField.setText("" + newValue);
   }
 }
 public static void main(String[] args) {
    Application.launch(args);          
  }
}