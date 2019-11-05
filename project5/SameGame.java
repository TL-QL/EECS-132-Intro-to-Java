import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.Arrays;

/** A class represents a game called SameGame
 *  @author <em> Qiwen Luo </em>
 */
public class SameGame extends Application{
  
  /** Store the main window of the application */ 
  private Stage primaryStage;
  /** Store a two-dimensional grid of Buttons */
  private Button[][] buttons;
  /** Store a two-dimensional grid of Circles */
  private Circle[][] circles;
  /** Store a two-dimensional boolean array that represents whether the buttons at the specific position should be removed */
  private boolean[][] willBeRemoved;
  /** Store a series of color that may be used in the game */
  Color[] color = new Color[] {Color.BLUE, Color.GREEN, Color.RED, Color.BLACK, Color.ORANGE};
  /** Store the count of the row */
  private static int rowSum;
  /** Store the count of the column */
  private static int columnSum;
  /** Store the number of the color needed */
  private static int numColors;
  
  /**
   * get the count of the row
   * @return the count of the row
   */ 
  public int getRowSum(){
    return rowSum;
  }
 
  /**
   * get the count of the column
   * @return the count of the column
   */ 
  public int getColumnSum(){
    return columnSum;
  }
 
  /**
   * get the number of the color needed
   * @return the number of the color needed
   */ 
  public int getNumColors(){
    return numColors;
  }
  
  /**
   * Initialize the JavaFX application
   * @param primaryStage the main window of the application
   */
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    /** Store a grid pane that is used to arranges the visual contents of the applicaiton into a grid */
    GridPane gridPane = new GridPane();
    buttons = new Button[getRowSum()][getColumnSum()];
    circles = new Circle[getRowSum()][getColumnSum()];
    willBeRemoved = new boolean[getRowSum()][getColumnSum()];
    visualizeButtons(createBoard(buttons, gridPane), circles);
    /** create the scene with this layout */
    Scene scene = new Scene(gridPane);
    primaryStage.setScene(scene);             
    primaryStage.show();
  }
  
  /**
   * create a board for the application
   * @return the board that is created for the application
   */
  public Button[][] createBoard(Button[][] buttons, GridPane gridPane){
    /**
      * Goal: create a two-dimensional grid of Buttons
      * Precondition: the count of the row of the buttons is at least i+1 and the count of the column of the buttons is at least j+1
      */
    for(int i = 0;i < buttons.length;i++){
      for(int j = 0;j < buttons[i].length;j++){
        buttons[i][j] = new Button();
        buttons[i][j].setOnAction(new BasicClickHandler());
        gridPane.add(buttons[i][j], j, i);
      }
    }
    return buttons;
  }
  
  /**
   * create visuals for the buttons
   * @param buttons a two-dimensional grid of Buttons that is going to be visualized
   */
  public void visualizeButtons(Button[][] buttons, Circle[][] circles){
    /**
      * Goal: create a two-dimensional grid of colorful Circles and use them to visualize the two-dimensional grid of Buttons
      * Precondition: the count of the row of the circles is at least i+1 and the count of the column of the circles is at least j+1
      */
    for(int i = 0;i < circles.length;i++){
      for(int j = 0;j < circles[i].length;j++){
        circles[i][j] = new Circle(10);
        circles[i][j].setFill(color[(int)(Math.random() * getNumColors())]);
        buttons[i][j].setGraphic(circles[i][j]);
      }
    }
  }
  
  /**
      * set the condition of each button to "should not be removed"
      */
    public void reset(boolean[][] willBeRemoved){
      /**
        * Goal: set the condition of each button to "should not be removed"
        * Precondition: the count of the row of willBeRemoved is at least i+1 and the count of the column of willBeRemoved is at least j+1
        */
      for(int i = 0;i < willBeRemoved.length;i++){
        for(int j = 0;j < willBeRemoved[i].length;j++){
          willBeRemoved[i][j] = false;
        }
      }
    }
    
    /**
      * find the buttons that should be removed
      * @param row store the Y-position of the button that is pressed
      * @param column X-position of the button that is pressed
      * @return whether there is any button that should be removed
      */
    public boolean find(int row, int column, Circle[][] circles, boolean[][] willBeRemoved){
      /** Indicate whether there is any button that should be removed*/
      boolean found = false;
      /** Store the X-position of the button that is visited */
      int currentColumn = column;
      
      /**
        * Goal: look right to check whether there is any contiguous buttons with the same color in a row
        * Precondition: the count of the column of willBeRemoved is at least currentColumn + 2 and the adjacent buttons have the same color
        */
      while((circles.length > 0 && currentColumn < circles[0].length - 1) && circles[row][currentColumn].getFill().equals(circles[row][currentColumn + 1].getFill())){
        willBeRemoved[row][currentColumn + 1] = true;
        found = true;
        currentColumn++;
      }
      currentColumn = column;
      /**
        * Goal: look left to check whether there is any contiguous buttons with the same color in a row
        * Precondition: the currentColumn is at least 1 and the adjacent buttons have the same color
        */
      while(currentColumn > 0 && circles[row][currentColumn].getFill().equals(circles[row][currentColumn - 1].getFill())){
        willBeRemoved[row][currentColumn - 1] = true;
        found = true;
        currentColumn--;
      }
      /** Store the Y-position of the button that is visited */
      int currentRow = row;
      /**
        * Goal: look up to check whether there is any contiguous buttons with the same color in a row
        * Precondition: the currentRow is at least 1 and the adjacent buttons have the same color
        */
      while(currentRow > 0 && circles[currentRow][column].getFill().equals(circles[currentRow - 1][column].getFill())){
        willBeRemoved[currentRow - 1][column] = true;
        found = true;
        currentRow--;
      }
      currentRow = row;
      /**
        * Goal: look down to check whether there is any contiguous buttons with the same color in a row
        * Precondition: the count of the row of willBeRemoved is at least currentRow + 2 and the adjacent buttons have the same color
        */
      while(currentRow < circles.length -1 && circles[currentRow][column].getFill().equals(circles[currentRow + 1][column].getFill())){
        willBeRemoved[currentRow + 1][column] = true;
        found = true;
        currentRow++;
      }
      /** Check whether there is any button that should be removed */
      if(found) willBeRemoved[row][column] = true;
      return found;
    }
    
    /**
     * remove contiguous buttons with the same color in a row
     */
    public void removeSingle(int row, int column, Circle[][] circles, boolean[][] willBeRemoved){
      /**
       * Goal: remove contiguous buttons with the same color in a row
       * Precondition: the count of the row of willBeRemoved is at least i + 1 and the count of the column of willBeRemoved is at least j + 1
       */
      for(int i = 0;i < circles.length;i++){
        if((i != row) && willBeRemoved[i][column]){
          /**
            * Goal: remove adjacent buttons with the same color in a row
            * Precondition: the row of the button that is visited is at least 1
            */
          for(int k = i;k > 0;k--){
            circles[k][column].setFill(circles[k-1][column].getFill());
          }
          circles[0][column].setFill(Color.LIGHTGRAY);
        }
        else{
          for(int j = 0;j < circles[i].length;j++){
            /** check whether the button should be removed */
            if(willBeRemoved[i][j]){
              /**
               * Goal: remove adjacent buttons with the same color in a row
               * Precondition: the row of the button that is visited is at least 1
               */
              for(int k = i;k > 0;k--){
                circles[k][j].setFill(circles[k-1][j].getFill());
              }
              circles[0][j].setFill(Color.LIGHTGRAY);
            }
          }
        }
      }
    }
    
    /** Check if there are any columns that are now empty
      * If there are, "shift" the columns to the right of the empty column over
      */
    public void removeColumn(Circle[][] circles){
      /**
       * Goal: Check if there are any columns that are now empty
       * Precondition: the row that is visited is at least 0
       */
      for(int m = circles[0].length - 1;m >= 0;m--){
        /** Indicate whether the column is empty */
        boolean isColumnEmpty = true;
        /**
          * Goal: Check if there are any columns that are now empty
          * Precondition: the count of the row of circles is at least n + 1 and until the current position the column is empty
          */
        for(int n = 0;n< circles.length && isColumnEmpty;n++){
          if(!circles[n][m].getFill().equals(Color.LIGHTGRAY)) isColumnEmpty = false;
        }
        /** If the column is empty, "shift" the columns to the right of the empty column over */
        if(isColumnEmpty){
          /**
            * Goal: "shift" the columns to the right of the empty column over
            * Precondition: the count of the row of circles is at least p + 1
            */
          for(int p = m;p < circles[0].length;p++){
            /**
              * Goal: "shift" the columns to the right of the empty column over
              * Precondition: the count of the column of circles is at least q + 1
              */
            for(int q = 0;q < circles.length;q++){
              if(p == circles[0].length - 1) circles[q][p].setFill(Color.LIGHTGRAY);
              else
                circles[q][p].setFill(circles[q][p + 1].getFill());
            }
          }
        }
      }
    }
  
    /**
      * judge whether the two specified arrays of booleans are equal to one another
      * @param a stores a boolean array that is used to be compared
      * @param b stores a boolean array that is used to be compared
      * @return true if the two specified arrays of booleans are equal to one another.
      */
    public static boolean isBooleanArraySame(boolean[][] a, boolean[][] b){
      boolean isSame = true;
      if(a.length == b.length){
        /**
          * Goal: Check whether the two specified arrays of booleans are equal to one another
          * Precondition: the length of two boolean arrays is at least i+1
          */
        for(int i = 0;i < a.length && isSame;i++){
          isSame = Arrays.equals(a[i], b[i]);
        }
      }
      else
        isSame = false;
      return isSame;
    }
    
    /**
      * judge whether the two specified arrays of circles are equal to one another
      * @param a stores a circle array that is used to be compared
      * @param b stores a circle array that is used to be compared
      * @return true if the two specified arrays of circles are equal to one another.
      */
    public static boolean isCircleArraySame(Circle[][] a, Circle[][] b){
      boolean isSame = true;
      if(a.length == b.length){
        /**
          * Goal: Check whether the two specified arrays of circles are equal to one another
          * Precondition: the length of two boolean arrays is at least i+1
          */
        for(int i = 0;i < a.length && isSame;i++){
          if(a[i].length == b[i].length){
            /**
              * Goal: Check whether the two specified arrays of circles are equal to one another
              * Precondition: the width of two boolean arrays is at least j+1
              */
            for(int j = 0;j < a[i].length && isSame;j++){
              isSame = a[i][j].getFill().equals(b[i][j].getFill());
            }
          }
          else
            isSame =false;
        }
      }
      else
        isSame = false;
      return isSame;
    }
         
  /** A class that handles button clicks by removing contiguous buttons with the same color in a row */
  private class BasicClickHandler implements EventHandler<ActionEvent> {
    /**
     * Respond to button clicks by removing contiguous buttons with the same color in a row
     * @param e information about the button click
     */
    public void handle(ActionEvent e) {
      /** Store a two-dimensional boolean array that represents whether the buttons at the specific position should be removed */
      Button b = (Button)e.getSource();
      /** Store the Y-position of the button that is pressed */
      int row = -1;
      /** Store the X-position of the button that is pressed */
      int column = -1;
      /** Indicate whether the position of the button has been found */
      boolean hasFound = false;
      /**
        * Goal: find the position of the button that is pressed
        * Precondition: the count of the row of the buttons is at least i+1 and the count of the column of the buttons is at least j+1
        */
      for(int i = 0;i < getRowSum() && (!hasFound);i++){
        for(int j = 0;j < getColumnSum() && (!hasFound);j++){
          if(buttons[i][j] == b){
            row = i;
            column = j;
            hasFound = true;
          }
        }
      }
      if(!circles[row][column].getFill().equals(Color.LIGHTGRAY)){
        /** set the condition of each button to "should not be removed" */
        reset(willBeRemoved);
        /** check whether there is contiguous buttons with the same color in a row */
        if(find(row, column, circles, willBeRemoved)){
          /** remove contiguous buttons with the same color in a row*/
          removeSingle(row, column, circles, willBeRemoved);
          /** Check if there are any columns that are now empty. 
            * If there are, "shift" the columns to the right of the empty column over. 
            */
          removeColumn(circles);
        }
      }
    }
  }
  
  /**
    * The start of the application.
    * @param args the command line arguments
    */
  public static void main(String[] args) {
    try{
      if(args.length > 3){
        rowSum = 12;
        columnSum = 12;
        numColors = 3;
        System.out.println("We can not meet your requirement of the game, but you can play the basic version!");
      }
      else{
        rowSum = Integer.parseInt(args[0]);
        columnSum = Integer.parseInt(args[1]);
        if(Integer.parseInt(args[2]) > 5 || Integer.parseInt(args[2]) <= 0) {
          numColors = 3;
          System.out.println("We can not meet your requirement of the game, but you can play the basic version!");
        }
        else
          numColors = Integer.parseInt(args[2]);
      }
    }
    catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
      rowSum = 12;
      columnSum = 12;
      numColors = 3;
      System.out.println("We can not meet your requirement of the game, but you can play the basic version!");
    }
    Application.launch(args);                  
  }
}