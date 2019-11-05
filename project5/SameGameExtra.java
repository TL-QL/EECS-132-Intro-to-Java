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
import  javafx.scene.Node;

/** A class represents a game called SameGame
 *  @author <em> Qiwen Luo </em>
 */
public class SameGameExtra extends Application{
  
  /** Store the main window of the application */ 
  private Stage primaryStage;
  /** Store a two-dimensional grid of Buttons */
  private Button[][] buttons;
  /** Store a two-dimensional grid of Circles */
  private Circle[][] circles;
  /** Store a two-dimensional boolean array that represents whether the buttons at the specific position should be removed */
  private boolean[][] willBeRemoved;
  /** Store a grid pane that is used to arranges the visual contents of the applicaiton into a grid */
  private GridPane gridPane = new GridPane();
  /** Store a series of color that may be used in the game */
  Color[] color = new Color[] {Color.BLUE, Color.GREEN, Color.RED, Color.BLACK, Color.ORANGE};
  /** Store the count of the row */
  private static int rowSum;
  /** Store the count of the column */
  private static int columnSum;
  /** Store the number of the color needed */
  private static int numColors;
  /** Store the version that the user chooses */
  private static String gameVersion;
  
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
   * get the version of the color needed
   * @return the version of the color needed
   */ 
  public String getGameVersion(){
    return gameVersion;
  }
  
  /**
   * Initialize the JavaFX application
   * @param primaryStage the main window of the application
   */
   public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    buttons = new Button[getRowSum()][getColumnSum()];
    circles = new Circle[getRowSum()][getColumnSum()];
    willBeRemoved = new boolean[getRowSum()][getColumnSum()];
    visualizeButtons(createBoard());
    /** create the scene with this layout */
    Scene scene = new Scene(gridPane);
    primaryStage.setScene(scene);             
    primaryStage.show();
  }
  
  /**
   * create a board for the application
   * @return the board that is created for the application
   */
  public Button[][] createBoard(){
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
  public void visualizeButtons(Button[][] buttons){
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
    public void reset(){
      /**
        * Goal: set the condition of each button to "should not be removed"
        * Precondition: the count of the row of willBeRemoved is at least i+1 and the count of the column of willBeRemoved is at least j+1
        */
      for(int i = 0;i < getRowSum();i++){
        for(int j = 0;j < getColumnSum();j++){
          willBeRemoved[i][j] = false;
        }
      }
    }
    
    /**
      * find the buttons that should be removed in the version of plus
      * @param row store the Y-position of the button that is pressed
      * @param column X-position of the button that is pressed
      * @return whether there is any button that should be removed
      */
    public boolean plusFind(int row, int column){
      /** Indicate whether there is any button that should be removed*/
      boolean found = false;
      /** Store the X-position of the button that is visited */
      int currentColumn = column;
      /**
        * Goal: look right to check whether there is any contiguous buttons with the same color in a row
        * Precondition: the count of the column of willBeRemoved is at least currentColumn + 2 and the adjacent buttons have the same color
        */
      while(currentColumn < getColumnSum() -1 && circles[row][currentColumn].getFill().equals(circles[row][currentColumn + 1].getFill())){
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
      while(currentRow < getRowSum() - 1 && circles[currentRow][column].getFill().equals(circles[currentRow + 1][column].getFill())){
        willBeRemoved[currentRow + 1][column] = true;
        found = true;
        currentRow++;
      }
      /** Check whether there is any button that should be removed */
      if(found) willBeRemoved[row][column] = true;
      return found;
    }
    
    /**
      * find the buttons that should be removed in the version of star
      * @param row store the Y-position of the button that is pressed
      * @param column X-position of the button that is pressed
      * @return whether there is any button that should be removed
      */
    public boolean starFind(int row, int column){
      /** Store the Y-position of the button that is visited */
      int currentRow = row;
      /** Store the X-position of the button that is visited */
      int currentColumn = column;
      /** Indicate whether there is any button that should be removed*/
      boolean found = false;
      /** Indicate whether we should stop finding in one direction */
      boolean stop = false;
      /** An array that the direction that the code will search */
      int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
      /**
        * Goal: find the buttons that should be removed in the version of star
        * Precondition: the length of the array direction is at least i+1
        */
      for(int i = 0;i < direction.length;i++){
        try{
          /**
            * Goal: find the buttons that should be removed in the version of star
            * Precondition: adjacent buttons have the same color and we should not stop searching
            */
          while((!stop) && circles[row][column].getFill().equals(circles[currentRow + direction[i][0]][currentColumn + direction[i][1]].getFill())){
            willBeRemoved[currentRow + direction[i][0]][currentColumn + direction[i][1]] = true;
            found = true;
            currentRow = currentRow + direction[i][0];
            currentColumn = currentColumn + direction[i][1];
          }
        }
        catch(ArrayIndexOutOfBoundsException e){
          stop = true;
        }
        currentRow = row;
        currentColumn = column;
        stop = false;
      }
      /** if there is any button should be removed, mark the button that is clicking to be ready to be removed */
      if(found) willBeRemoved[row][column] = true;
      return found;
    }
    
    /**
      * find the buttons that should be removed in the version of same
      * @param row store the Y-position of the button that is pressed
      * @param column X-position of the button that is pressed
      * @return whether there is any button that should be removed
      */
    public boolean sameFind(int row, int column){
      /** Store the Y-position of the button that is visited */
      int currentRow = row;
      /** Store the X-position of the button that is visited */
      int currentColumn = column;
      /** Indicate whether there is any button that should be removed */
      boolean found = false;
      willBeRemoved[row][column] = true;
      while(currentColumn < circles[row].length-1 && (!willBeRemoved[currentRow][currentColumn + 1]) && circles[currentRow][currentColumn].getFill().equals(circles[currentRow][currentColumn + 1].getFill())){
        willBeRemoved[currentRow][currentColumn + 1] = true;
        found = true;
        currentColumn++;
        sameFind(currentRow, currentColumn);
      }
      currentRow = row;
      currentColumn = column;
      while(currentColumn > 0 && (!willBeRemoved[currentRow][currentColumn - 1]) && circles[currentRow][currentColumn].getFill().equals(circles[currentRow][currentColumn - 1].getFill())){
        willBeRemoved[currentRow][currentColumn - 1] = true;
        found = true;
        currentColumn--;
        sameFind(currentRow, currentColumn);
      }
      currentRow = row;
      currentColumn = column;
      while(currentRow > 0 && (!willBeRemoved[currentRow - 1][currentColumn]) && circles[currentRow][currentColumn].getFill().equals(circles[currentRow - 1][currentColumn].getFill())){
        willBeRemoved[currentRow][currentColumn] = true;
        found = true;
        currentRow--;
        sameFind(currentRow, currentColumn);
      }
      currentRow = row;
      currentColumn = column;
      while(currentRow < circles.length - 1 && (!willBeRemoved[currentRow + 1][currentColumn]) && circles[currentRow][currentColumn].getFill().equals(circles[currentRow + 1][currentColumn].getFill())){
        willBeRemoved[currentRow + 1][currentColumn] = true;
        found = true;
        currentRow++;
        sameFind(currentRow, currentColumn);
      }
      return found;
    }
    
    /**
     * remove contiguous buttons with the same color in a row
     */
    public void removeSingle(int row, int column){
      /**
       * Goal: remove contiguous buttons with the same color in a row
       * Precondition: the count of the row of willBeRemoved is at least i + 1 and the count of the column of willBeRemoved is at least j + 1
       */
      for(int i = 0;i < getRowSum();i++){
          for(int j = 0;j < getColumnSum();j++){
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
    
    /** Check if there are any columns that are now empty
      * If there are, "shift" the columns to the right of the empty column over
      */
  public void removeColumn(){
      /**
       * Goal: Check if there are any columns that are now empty
       * Precondition: the row that is visited is at least 0
       */
      for(int m = getColumnSum() - 1;m >= 0;m--){
        /** Indicate whether the column is empty */
        boolean isColumnEmpty = true;
        /**
          * Goal: Check if there are any columns that are now empty
          * Precondition: the count of the row of circles is at least n + 1 and until the current position the column is empty
          */
        for(int n = 0;n< getRowSum() && isColumnEmpty;n++){
          if(!circles[n][m].getFill().equals(Color.LIGHTGRAY)) isColumnEmpty = false;
        }
        /** If the column is empty, "shift" the columns to the right of the empty column over */
        if(isColumnEmpty){
          /**
            * Goal: "shift" the columns to the right of the empty column over
            * Precondition: the count of the row of circles is at least p + 1
            */
          for(int p = m;p < getColumnSum();p++){
            /**
              * Goal: "shift" the columns to the right of the empty column over
              * Precondition: the count of the column of circles is at least q + 1
              */
            for(int q = 0;q < getRowSum();q++){
              if(p == getColumnSum() - 1) circles[q][p].setFill(Color.LIGHTGRAY);
              else
                circles[q][p].setFill(circles[q][p + 1].getFill());
            }
          }
        }
      }
    }
  
  public void clear(){
    for(int i = 0;i < circles.length;i++){
      for(int j = 0;j < circles.length;j++){
        if(circles[i][j].getFill().equals(Color.LIGHTGRAY)) buttons[i][j].setVisible(false);
        else
          buttons[i][j].setVisible(true);
      }
    }
  }
  
    /** A class that handles button clicks by removing contiguous buttons with the same color in a row */
  private class BasicClickHandler implements EventHandler<ActionEvent> {
    
    /**
     * Respond to button clicks by removing contiguous buttons with the same color in a row
     * @param e information about the button click
     */
    public void handle(ActionEvent e) {
      Button b = (Button)e.getSource();
      /** Store the Y-position of the button that is pressed */
      int row = -1;
      /** Store the X-position of the button that is pressed */
      int column = -1;
      /** Indicate whether the position of the button has been found */
      boolean hasFound = false;
      boolean readyRemoved = false;
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
      /** set the condition of each button to "should not be removed" */
      reset();
      if(gameVersion.equals("plus")) readyRemoved = plusFind(row, column);
      else if(gameVersion.equals("star")) readyRemoved = starFind(row, column);
      else
        readyRemoved = sameFind(row, column);
      /** check whether there is contiguous buttons with the same color in a row */
      if(readyRemoved){
        /** remove contiguous buttons with the same color in a row*/
        removeSingle(row, column);
        /** Check if there are any columns that are now empty. 
          * If there are, "shift" the columns to the right of the empty column over. 
          */
        removeColumn();
        clear();
      }
      else
        willBeRemoved[row][column] = false;
      if(circles[getRowSum() - 1][0].getFill().equals(Color.LIGHTGRAY)) System.out.println("You win!!!");
    }
  }
  
  /**
    * The start of the application.
    * @param args the command line arguments
    */
  public static void main(String[] args) {
    try{
      if(args.length > 4){
        gameVersion = "plus";
        rowSum = 12;
        columnSum = 12;
        numColors = 3;
        System.out.println("We can not meet your requirement, but you can play the basic version!");
      }
      else{
        gameVersion = args[0];
        rowSum = Integer.parseInt(args[1]);
        columnSum = Integer.parseInt(args[2]);
        if(Integer.parseInt(args[3]) > 5 || Integer.parseInt(args[3]) <= 0){
          numColors = 3;
          System.out.println("We can not meet your requirement, but you can play the basic version!");
        }
        else
          numColors = Integer.parseInt(args[3]);
      }
    }
    catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
      gameVersion = "plus";
      rowSum = 12;
      columnSum = 12;
      numColors = 3;
      System.out.println("We can not meet your requirement, but you can play the basic version!");
    }
    Application.launch(args);                  
  }
}