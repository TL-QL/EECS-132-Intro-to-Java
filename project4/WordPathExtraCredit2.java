import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.NoSuchElementException;

public class WordPathExtraCredit2{
  
  public WordData[] makeWordArray(String fileName) throws IOException{
    WordData[] fileLine = new WordData[WordPathExtraCredit2.numLines(new FileReader(fileName))];
    new FileReader(fileName).close();
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    for(int i = 0; i < fileLine.length;i++){
      fileLine[i] = WordData.parseWordData(br.readLine());
    }
    return fileLine;
  }
  
  public LinkedList<Integer> getPath(int indexOfStart, int indexOfDestination, WordData[] indices){
    LinkedList<Integer> indicesOnThePath = new LinkedList<Integer>();
    if(indexOfStart >= 0 && indexOfStart < indices.length && indexOfDestination >= 0 && indexOfDestination < indices.length){
      if(indexOfStart == indexOfDestination){
        indicesOnThePath.addLast(indexOfStart);
        return indicesOnThePath;
      }
      else{
        int currentIndex = indexOfStart;
        indices[indexOfStart].setHasVisited(true);
        indicesOnThePath.addLast(indexOfStart);
        for(int i = 0; i < indices[currentIndex].getList().size();i++){
          if(!(indices[indices[currentIndex].getList().get(i)].getHasVisited())){
            indices[indices[currentIndex].getList().get(i)].setHasVisited(true);
            if(indices[currentIndex].getList().get(i) == indexOfDestination){
              indicesOnThePath.addLast(indexOfDestination);
              return indicesOnThePath;
            }
            if(i == indices[currentIndex].getList().size() - 1){
              int lastSameChar = -1;
              int thisSameChar = 0;
              LinkedList<Integer> equalSameChar = new LinkedList<Integer>();
              indicesOnThePath.addLast(currentIndex);
              for(int j = 0;j < indices[currentIndex].getList().size();j++){
                for(int k = 0;k < (indices[indexOfDestination].getWord().length() < indices[indices[currentIndex].getList().get(j)].getWord().length() ? indices[indexOfDestination].getWord().length() : indices[indices[currentIndex].getList().get(j)].getWord().length());k++){
                  if(indices[indexOfDestination].getWord().charAt(k) == indices[indices[currentIndex].getList().get(j)].getWord().charAt(k)) thisSameChar++;
                }
                if(thisSameChar > lastSameChar){
                  currentIndex = indices[currentIndex].getList().get(j);
                  lastSameChar = thisSameChar;
                }
                if(thisSameChar == lastSameChar){
                  equalSameChar.addLast(indices[currentIndex].getList().get(j));
                }
                thisSameChar = 0;
              }
              if(!equalSameChar.isEmpty()){
                int nearestIndex = equalSameChar.get(0);
                currentIndex = equalSameChar.get(0);
                for(int m = 0;m < equalSameChar.size();m++){
                  if(Math.abs(equalSameChar.get(m)-indexOfDestination) < Math.abs(nearestIndex-indexOfDestination)) currentIndex = equalSameChar.get(m);
                }
              }
              i = -1;
            }
          }
          if((i == (indices[currentIndex].getList().size()-1)) && (indices[indices[currentIndex].getList().get(i)].getHasVisited())){  
            try{
              currentIndex = indicesOnThePath.removeLast();
            }
            catch(NoSuchElementException e){
              return null;
            }
            i = -1;
          }
        }
      }
    }
    return null;
  }
  
  public void getWordPath(String file) throws IOException{
    WordData[] indices = makeWordArray(file);
    String firstWord = JOptionPane.showInputDialog("choose one word from the file");
    String secondWord = JOptionPane.showInputDialog("choose another word from the file");
    boolean isFirstWordFound = false;
    boolean isSecondWordFound = false;
    int indexOfFirstWord = 0;
    int indexOfSecondWord = 0;
    for(int i = 0; i < indices.length && (!isFirstWordFound);i++){
      if(indices[i].getWord().equals(firstWord)){
        isFirstWordFound = true;
        indexOfFirstWord = i;
      }
    }
    for(int j = 0; j < indices.length && (!isSecondWordFound);j++){
      if(indices[j].getWord().equals(secondWord)){
        isSecondWordFound = true;
        indexOfSecondWord = j;
      }
    }
    if(isFirstWordFound && isSecondWordFound){
      LinkedList<Integer> wordPath = getPath(indexOfFirstWord, indexOfSecondWord, indices);
      if(wordPath == null) System.out.println("Can not find the path!");
      else{
        System.out.println("Word Path:");
        for(int k = 0; k < wordPath.size();k++){
          System.out.print(indices[wordPath.get(k)].getWord()+'\t');
        }
        System.out.println();
      }
    }
    else
      System.out.println("Warning! Can not find the words!");
    for(int t = 0; t < indices.length;t++){
      indices[t].setHasVisited(false);
    }
    getWordPath(file);
  }
  
  public static int numLines(Reader reader) throws IOException{
    int numberOfLines = 0;
    BufferedReader br = new BufferedReader(reader);
    while(br.readLine() != null){
      numberOfLines++;
    }
    br.close();
    return numberOfLines;
  }
  
  public static void main(String[] args) throws IOException{
    WordPathExtraCredit wp = new WordPathExtraCredit();
    try{
      wp.getWordPath(args[0]);
    }
    catch(FileNotFoundException | ArrayIndexOutOfBoundsException e){
      System.out.println("Waining! You may forget to input a file name or your file name is in the wrong format!");
    }
  }
}