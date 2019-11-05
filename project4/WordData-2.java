import java.util.LinkedList;
import java.util.Scanner;

/** A class represents the information of the words in the specific file
 *  @author <em> Qiwen Luo </em>
 */
public class WordData{
  /** indexOfWord stores an int that represents the index of a word */
  private int indexOfWord = 0;
  /** word stores a String that represents a word that is on the line of the indexOfWord */
  private String word = "";
  /** hasVisited stores a boolean that represents whether the word has been visited */
  private boolean hasVisited = false;
  /** list stores a LinkedList that contains Integer to represents the indices of the words which differ by one letter from this word */
  private LinkedList<Integer> list = new LinkedList<Integer>();
  
  /**
   *  Initialize the index, word that on the line of the indexOfWord , condition(has visited or not), 
   *  and the indices of the words which differ by one letter from this word
   *  @param indexOfWord store an int that represents the index of a word
   *  @param word store a String that represents a word which is on the line of the indexOfWord
   *  @param hasVisited store a boolean that represents whether the word has been visited
   *  @param list store a LinkedList that contains Integer to represents the indices of the words which differ by one letter from this word 
   */
  public WordData(int indexOfWord, String word, boolean hasVisited, LinkedList<Integer> list){
    this.indexOfWord = indexOfWord;
    this.word = word;
    this.hasVisited = hasVisited;
    this. list = list;
  }
  
  /**
   * set the index of the word
   * @param indexOfWord store an int that represents the index of a word
   */ 
  public void setIndexOfWord(int indexOfWord){
    this.indexOfWord = indexOfWord;
  }
  
  /**
   * get the index of the word
   * @return the index of the word
   */ 
  public int getIndexOfWord(){
    return indexOfWord;
  }
  
  /**
   * set the word which is on the line of the indexOfWord
   * @param word store a String that represents a word which is on the line of the indexOfWord
   */ 
  public void setWord(String word){
    this.word = word;
  }
  
  /**
   * get the word which is on the line of the indexOfWord
   * @return the word which is on the line of the indexOfWord
   */ 
  public String getWord(){
    return word;
  }
  
  /**
   * set the condition(has visited or not) of the word
   * @param hasVisited store a boolean that represents whether the word has been visited
   */
  public void setHasVisited(boolean hasVisited){
    this.hasVisited = hasVisited;
  }
  
  /**
   * get the condition(has visited or not) of the word
   * @return the condition(has visited or not) of the word
   */
  public boolean getHasVisited(){
    return hasVisited;
  }
  
  /**
   * set the list that contains Integer to represents the indices of the words which differ by one letter from this word
   * @param list stores a LinkedList that contains Integer to represents the indices of the words which differ by one letter from this word
   */
  public void setList(LinkedList<Integer> list){
    this.list = list;
  }
  
  /**
   * get the list that contains Integer to represents the indices of the words which differ by one letter from this word
   * @return the list that contains Integer to represents the indices of the words which differ by one letter from this word
   */
  public LinkedList<Integer> getList(){
    return list;
  }
  
  /**
   * Compare an instance of WordData with another Object
   * @param o stores an object that is used to be compared with an instance of WordData
   * @return true if o represents a WordData instance and the WordData instances have the same structure
   */
  @Override
  public boolean equals(Object o){
    if(o instanceof WordData){
      WordData wd = (WordData) o;
      return getIndexOfWord() == wd.getIndexOfWord() && getWord().equals(wd.getWord()) && getHasVisited() == wd.getHasVisited() && getList().equals(wd.getList()); 
    }
    else
      return false;
  }
  
  /**
   * Compare an instance of WordData with another Object
   * @param informationOfWord store the index, context, condition and a list that represents the indices of the words which differ by one letter from this word of the word
   * @return an WordData instance that contains all of the information stores in the input string
   */
  public static WordData parseWordData(String informationOfWord){
    /** scanner stores the context in the String informationOfWord */
    Scanner scanner = new Scanner(informationOfWord);
    /** word stores the WordData instance which is going to be returned by this method */
    WordData word = new WordData(0, "", true, new LinkedList<Integer>());
    word.setIndexOfWord(scanner.nextInt());
    word.setWord(scanner.next());
    word.setHasVisited(false);
    /**
     * Goal: read the information of the indices of the words which differ by one letter from this word in the String one by one
     * Precondition: at least 1 int has not been read
     */
    while(scanner.hasNextInt()){
      word.getList().addLast(scanner.nextInt());
    }
    return word;
  }
}