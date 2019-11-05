import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;

/** A class represents the path between two words
 *  @author <em> Qiwen Luo </em>
 */
public class WordPath{
  
  /**
   * return the context in the file as a series of WordData instances
   * @param filename stores the name of the file
   * @return the number of lines in the file
   */
  public WordData[] makeWordArray(String fileName) throws IOException{
    /** fileLine stores a series of WordData instances that is going to be returned by this method */
    WordData[] fileLine = new WordData[WordPath.numLines(new FileReader(fileName))];
    new FileReader(fileName).close();
    /** br stores the context of the file */
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    /**
     * Goal: change the format of the information of the word from String to WordDAta and store it in the array fileLine
     * Precondition: the length of the array is at least i+1
     */
    for(int i = 0; i < fileLine.length;i++){
      fileLine[i] = WordData.parseWordData(br.readLine());
    }
    return fileLine;
  }
  
  /**
   * return a list of Integer that contains the index of each word on the path from the start index to the destination index
   * @param indexOfStart store an int that represents a start index
   * @param indexOfDestination store an int that represents a destination index
   * @return a list of Integer that contains the index of each word on the path from the start index to the destination index
   */
  public LinkedList<Integer> getPath(int indexOfStart, int indexOfDestination, WordData[] indices){
    /** indicesOnThePath stores a LinkedList that represents the index of each word on the path from the start index to the destination index*/
    LinkedList<Integer> indicesOnThePath = new LinkedList<Integer>();
    /** check whether the indices of the start word and the end word are in the bound of the array indices */
    if(indexOfStart >= 0 && indexOfStart < indices.length && indexOfDestination >= 0 && indexOfDestination < indices.length){ 
      indices[indexOfStart].setHasVisited(true);
      if(indexOfStart == indexOfDestination){
        indicesOnThePath.addFirst(indexOfStart);
        return indicesOnThePath;
      }
      else{
        /**
         * Goal: find the path from the start index to the destination index
         * Precondition: the length of the list which is on the line of start index is at least i+1
         */
        for(int i = 0;i < indices[indexOfStart].getList().size(); i++){
          if(!(indices[indices[indexOfStart].getList().get(i)].getHasVisited())){
            indices[indices[indexOfStart].getList().get(i)].setHasVisited(true);
            indicesOnThePath = getPath(indices[indexOfStart].getList().get(i), indexOfDestination, indices);
            try{
              if(!(indicesOnThePath.isEmpty())){
                indicesOnThePath.addFirst(indexOfStart);
                return indicesOnThePath;
              }
            }
            catch(NullPointerException e){
              indicesOnThePath = new LinkedList<Integer>();
            }
          }
        }
      }
    }
    return null;
  }
  
  /**
   * change the format of the context in the file to a series of WordData instances,
   * find and print the the path from the start index to the destination index
   * @param file stores the name of the file
   */
  public void getWordPath(String file) throws IOException{
    /** indices stores the information of the word in the format of WordData */
    WordData[] indices = makeWordArray(file);
    /** firstWord stores a word queried form the user which is used as the start of the path */
    String firstWord = JOptionPane.showInputDialog("choose one word from the file");
    /** secondWord stores a word queried form the user which is used as the end of the path */
    String secondWord = JOptionPane.showInputDialog("choose another word from the file");
    /** isFirstWordFound stores a boolean value which indicates whether the word is found in the file */
    boolean isFirstWordFound = false;
    /** isSecondWordFound stores a boolean value which indicates whether the word is found in the file */
    boolean isSecondWordFound = false;
    /** indexOfFirstWord stores the index of the word in the file */
    int indexOfFirstWord = 0;
    /** indexOfSecondWord stores the index of the word in the file */
    int indexOfSecondWord = 0;
    /**
     * Goal: find the index of the first word in the file
     * Precondition: the length of indices is at least i+1
     */
    for(int i = 0; i < indices.length && (!isFirstWordFound);i++){
      if(indices[i].getWord().equals(firstWord)){
        isFirstWordFound = true;
        indexOfFirstWord = i;
      }
    }
    /**
     * Goal: find the index of the second word in the file
     * Precondition: the length of indices is at least j+1
     */
    for(int j = 0; j < indices.length && (!isSecondWordFound);j++){
      if(indices[j].getWord().equals(secondWord)){
        isSecondWordFound = true;
        indexOfSecondWord = j;
      }
    }
    /** if both of the words can be found in the file, then try to find the word path */
    if(isFirstWordFound && isSecondWordFound){
      LinkedList<Integer> wordPath = getPath(indexOfFirstWord, indexOfSecondWord, indices);
      if(wordPath != null){
        System.out.println("Word Path:");
        /**
         * Goal: print the contents of the word path
         * Precondition: the length of wordPath is at least k+1
         */
        for(int k = 0; k < wordPath.size();k++){
          System.out.print(indices[wordPath.get(k)].getWord()+" ");
        }
        System.out.println();
      }
      else
        System.out.println("Can not find the path!");
    }
    else
      System.out.println("Can not find the words!");
    /**
     * Goal: set each condition of the word in indices as "has not been visited"
     * Precondition: the length of indices is at least t+1
     */
    for(int t = 0; t < indices.length;t++){
      indices[t].setHasVisited(false);
    }
    getWordPath(file);
  }
  
  /**
   * count the number of lines in the file
   * @param reader stores the contents of the file
   * @return the number of lines in the file
   */
  public static int numLines(Reader reader) throws IOException{
    /** numberOfLines stores the number of lines in the file */
    int numberOfLines = 0;
    /** br stores the contents of the file */
    BufferedReader br = new BufferedReader(reader);
    /**
     * Goal: count the number of lines in the file
     * Precondition: at least 1 line has not been read
     */
    while(br.readLine() != null){
      numberOfLines++;
    }
    br.close();
    return numberOfLines;
  }
  
  /**  
   *  The start of the application
   *  @param args the command line arguments 
   */
  public static void main(String[] args) throws IOException{
    WordPath wp = new WordPath();
    try{
      wp.getWordPath(args[0]);
    }
    catch(FileNotFoundException | ArrayIndexOutOfBoundsException e){
      System.out.println("You may forget to input a file name or your file name is in the wrong format!");
    }
  }
}