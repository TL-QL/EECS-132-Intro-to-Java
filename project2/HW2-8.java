/**
 * Homework2 written by Qiwen Luo  student ID: qxl216 
 * The class HW2 is used to change the context of string in specific way which is written in the instruction.
 */
public class HW2{
  
  /**
   * capitalize the first character (if it is a letter in lower case) of each sentence
   * @param s the string that is going to be capitalized
   */
  public static String capitalizeSentences(String s){      
    // builder stores the string that has been capitalized
    StringBuilder builder = new StringBuilder();  
    // isFirstLetter stores a boolean value which indicates whether the character is the first letter of the string
    boolean isFirstLetter = false;  
    // endOfSentence stores a boolean value which indicates whether the character is the end of the sentence
    boolean endOfSentence = false;              
    int i = 0;
    // Goal: find the first letter of the string and capitalize it if it is a letter in lower case
    // Precondition: the first letter of the string is not found && the length of s is at least i+1 
    while(!isFirstLetter && i<s.length()) {
      // c stores the ith character of the string s
      char c = s.charAt(i);                                
      // judge whether c represents a letter
      if(Character.isLetter(c)) {
        isFirstLetter=true;
        // judge whether c represents a lower case letter
        if(c>='a' && c<='z') c = (char)(c-'a'+'A');
      }
      builder.append(c);
      i++;
    }
    // Goal: find the first character of every sentence (except for the first one) in the string and capitalize it if it is a letter in lower case
    // Precondition: the length of s is at least i+1
    for( ; i<s.length(); i++){
      // c stores the ith character of the string s
      char c = s.charAt(i);                               
      // capitalize the first letter of a sentence except for the first sentence
      if(endOfSentence && Character.isLetter(c)) {
        if(c>='a' && c<='z') c = (char)(c-'a'+'A');
        endOfSentence = false;
      }
      builder.append(c);
      // judge whether the character is the end of the sentence
      if(c == '.' || c == '?' || c == '!'){
        endOfSentence=true;
      }
    }
    return builder.toString();
  }
  
  /**
   * judge whether String check is a subsequence of String reference
   * @param check the string that is going to be judged whether it is a subsequence of String reference
   * @param reference the string that is used as a reference
   */
  public static boolean subSequence(String check, String reference){ 
    int j = 0;
    // Goal:judge whether String check is a subsequence of String reference
    // Precondition: the length of check is at least i+1
    for(int i = 0; i<check.length(); i++){
      // charCheck stores the ith character of the string check
      char charCheck = check.charAt(i);                                 
      // Goal:judge whether the ith character of String check can be find in String reference 
      // Precondition: the length of reference is at least j+1 and the ith character of String check is not equal to the jth character of String reference
      while(j<reference.length() && charCheck != reference.charAt(j)){
        j++;
      }
      // if j is not less than the length of reference, check is not a sebsequence of reference
      if (j>=reference.length()) return false;
      j++;
    }
    return true;
  }
  
  /**
   * return a string that is formed by using each digit of the second string as an index to identify the character from the first input string
   * @param dictionary the string whose context is choosen to compose a new string
   * @param index the string whose context is use as an index to identify the character from the first input string
   */
  public static String indexWord(String dictionary, String index){
    // builder stores the new string that will be returned
    StringBuilder builder = new StringBuilder(); 
    // Goal: create a string that is formed by using each digit of the second string as an index to identify the character from the first input string
    // Precondition: the length of index is at least i+1
    for(int i = 0; i<index.length(); i++){
      // charIndex stores the ith character in String index
      char charIndex = index.charAt(i);                          
      charIndex = dictionary.charAt(charIndex-'0');
      builder.append(charIndex);
    }
    return builder.toString();
  }
  
  /**
   * remove any spaces at the beginning of the string and end of the string and repalce extra spaces between two non-space characters by a single space
   * @param s the string whose extra spaces are going to be removed
   */
  public static String removeExtraSpaces(String s){
    // builder stores the new string that will be returned
    StringBuilder builder = new StringBuilder();         
    int i = 0;
    int j = s.length()-1;
    // numberOfSpaces stores the number of spaces between two contiguous words
    int numberOfSpaces = 0;                           
    // Goal: remove any spaces at the beginning of the string
    // Precondition: the ith character of s is not a space and the length of s is at least i+1
    while(i<s.length() && s.charAt(i) == ' '){
      i++;
    }
    // Goal: remove any spaces at the end of the string
    // Precondition: the jth character of s is not a space and j is at least 0
    while(j>=0 && s.charAt(j) == ' '){
      j--;
    }
    // Goal: repalce extra spaces between two non-space characters by a single space
    // Precondition: j is at least m+1
    for(int m = i; m<=j; m++){
      if(s.charAt(m) == ' ') numberOfSpaces++;
      else
        numberOfSpaces = 0;
      if(numberOfSpaces<=1) builder.append(s.charAt(m));
    }
    return builder.toString();
  }
  
  /**
   * remove every kth word and the whitespace immediately following that word
   * @param s the string whose every kth word and the whitespace immediately following it is going to be removed
   * @param k the int which determines which words in String s is going to be removed
   */
  public static String removeEveryKthWord(String s, int k){
    // numberOfWords stores the number of words that is before the ith character in the string
    int numberOfWords = 0;                          
    int i = 0;
    // startOfWords stores a boolean value that indicates whether the next character is the start of a new word
    boolean startOfWords = true;
    // isPunctuation stores a boolean value that indicates whether the character is a punctuation or the whitespaces immediately following
    boolean isPunctuation = false;
    // builder stores the new string that will be returned
    StringBuilder builder = new StringBuilder();    
    if(k<=0) return s;
    else{
      // Goal: append all the spaces before the first character in the string
      // Precondition: the length of s is at least i+1 and the ith character is space
      while(i<s.length() && s.charAt(i) == ' '){
        builder.append(s.charAt(i));
        i++;
      }
      // Goal: remove every kth word and the whitespace immediately following it
      // Precondition: the length of s is at least i+1
      for( ; i<s.length(); i++){
        // find the start of a new word and count it
        if(startOfWords) {
          numberOfWords++;
          startOfWords = false;
        }
        if(i<s.length()-1 && isPunctuation && Character.isLetter(s.charAt(i+1))) isPunctuation = false;
        if(s.charAt(i) == '.' || s.charAt(i) == '!' || s.charAt(i) == '?') isPunctuation = true;
        // append the character to the builder if the character does not belong to the kth words or the whitespace immediately following the kth words
        if((numberOfWords % k)!=0 || isPunctuation) builder.append(s.charAt(i));
        // judge whether the next character is the start of a new word
        if(s.charAt(i) == ' ' && i<s.length()-1 && Character.isLetter(s.charAt(i+1))) startOfWords = true;
      }
      return builder.toString();
    }
  }
  
  /**
   * judge whether the first input string contains a substring that is the same as any of the words in the second input string
   * @param check the string that is going to be judged whether it has a substring that is the same as any of the words in the second input string
   * @param reference the string that is used as a reference 
   */
  public static boolean containsWord(String check, String reference){
    // Goal: judge whether the first input string contains a substring that is the same as any of the words in the second input string
    // Precondition: the length of reference is at least i+1
    for(int i = 0; i<reference.length(); ){
      // builder stores one word in the String reference
      StringBuilder builder = new StringBuilder();
      
      while (i<reference.length() && reference.charAt(i) == ' '){
        i++;
      }
      // Goal: find the words in the String reference one by one
      // Precondition: the length of reference is at least i+1 and the ith character can not be a space
      while (i<reference.length() && reference.charAt(i) != ' ') {
        builder.append(reference.charAt(i));
        i++;
      }
      // find the space immediately before the beginning of the next word
      // Precondition: the length of reference is at least i+2 and the (i+1)th character can not be a space
      i++;
      // newReference stores the word getten.
      String newReference = builder.toString();
      // beginningOfNewReference stores the index in the check where we should start to check
      int beginningOfNewReference = 0;
      if(HW2.subSequence(newReference,check) && newReference.charAt(0) != ' '){
        // Goal: find the index in the check where we should start to check and check whether there is a word that is the same as newReference
        // Precondition: the length of check is at least i+1
        for(int j = 0; j<check.length(); j++){
          if(check.charAt(j) == newReference.charAt(0)){ 
            beginningOfNewReference = j;
            // isContainsWord stores a boolean value which is used to check whether there is a word that is the same as newReference
            boolean isContainsWord = true;
            // Goal: check whether the check contains a substring that is the same as any of the words in the reference
            // Precondition: beginningOfNewReference plus the length of newReference is at least k+1 and isContainWord is true 
            for(int k = beginningOfNewReference, m = 0; k<(beginningOfNewReference+newReference.length()) && isContainsWord; k++,m++){
              if(check.charAt(k) != newReference.charAt(m)) isContainsWord=false;
            }
            if(isContainsWord) return true;
          }
        }
      }
    }
    return false;
  }
  
  /**
   * return the String that composes of the words which are both in String check and array board
   * @param board the array that stores several strings
   * @param check the words in the check will be judge whether they can be found in board either forwards, backwards, vertically, or diagonally in each possible direction 
   */
  public static String wordSearch(String[] board, String check){
   // result stores the string that will be returned
    StringBuilder result=new StringBuilder();
    // Goal: find the words which are both in String check and array board
    // Precondition: the length of check is at least i+1
    check=HW2.removeExtraSpaces(check);
    for(int i = 0; i<check.length(); ){
      // builder stores the word in the check one for one time
      StringBuilder builder=new StringBuilder();
      // Goal: find the first non-space character in String check
      // Precondition: the length of check is at least i+1 and the ith character is a space 
      while(i<check.length() && check.charAt(i)==' '){
        i++;
      }
      // Goal: find the word in the String check one by one
      // Precondition: the length of check is at least i+1 and the ith character is not a space
      while(i<check.length() && check.charAt(i)!=' '){
        builder.append(check.charAt(i));
        i++;
      }
      // newCheck stores one of the word in the String check
      String newCheck= builder.toString();
      int m=0;
      int k=0;
      int markRow=0;
      int markCol=0;
      // bingo stores a boolean value which indicates whether the specific letter can be found
      boolean bingo=false;
      while(!bingo && m<board.length && k<board[board.length-1].length()){
        int j=0;
        boolean findFirstLetter=false;
        // Goal: find the first letter of newCheck in array board
        // Precondition: the length of board is at least m+1 and the first letter is not found
        while(m<board.length && !findFirstLetter){
          if(markCol==0) k=0;
          else {
            k=markCol+1;
            markCol=0;
          }
          // Goal: find the first letter of newCheck in String board[m]
          // Precondition: the length of board[m] is at least k+1 and the first letter is not found
          while (k<board[m].length() && !findFirstLetter){
            if (board[m].charAt(k) == newCheck.charAt(0)) findFirstLetter = true;
            else
              k++;
          }
          if(findFirstLetter) j++;
          else
            m++;
        }
        // judge whether the first letter has been found
        if(!findFirstLetter) bingo=false;
        else{
          markRow=m;
          markCol=k;
          bingo=false;
          // judge whether newCheck appears forwards in board
          if(k<board[m].length()-1 && board[m].charAt(k+1)==newCheck.charAt(1)) {
            bingo=true;
            if((board[m].length()-k)<newCheck.length()) bingo=false;
            else{
              k=k+2;
              // Goal: judge whether newCheck appears forwards in board
              // Precondition: bingo is true, the length of board[m] is at least k+1 and the length of newCheck is at least j+1
              for( j=2;bingo && k<board[m].length() && j<newCheck.length();j++,k++){
                if(board[m].charAt(k)!=newCheck.charAt(j)) bingo=false;
              }
            }
            if(bingo){
              // Goal: append the word in the newCheck whether it has been found in board
              // Precondition: the length of newCheck is at least j+1
              for(j=0;j<newCheck.length();j++){
                result.append(newCheck.charAt(j));
              }
              if(i!=check.length()) result.append(' ');
            }
          }
          m=markRow;
          k=markCol;
          // judge whether newCheck appears backwards in board
          if(!bingo && k>=1 && board[m].charAt(k-1)==newCheck.charAt(1)){
            bingo=true;
            if(k<newCheck.length()) bingo=false;
            else{
              k=k-2;
              // Goal: judge whether newCheck appears backwards in board
              // Precondition: bingo is true, k is at least 0 and the length of newCheck is at least j+1
              for(j=2;bingo && k>=0 && j<newCheck.length();j++,k--){
                if(board[m].charAt(k)!=newCheck.charAt(j)) bingo=false;
              }
            }
            if(bingo){
              // Goal: append the word in the newCheck whether it has been found in board
              // Precondition: the length of newCheck is at least j+1
              for(j=0;j<newCheck.length();j++){
                result.append(newCheck.charAt(j));
              }
              if(i!=check.length()) result.append(' ');
            }
          }
          m=markRow;
          k=markCol;
          // judge whether newCheck appears vertically down in board
          if(!bingo && m<board.length-1 && k<board[m+1].length() && board[m+1].charAt(k)==newCheck.charAt(1)){
            bingo=true;
            if((board.length-m)<newCheck.length()) bingo=false;
            else{
              m=m+2;
              // Goal: judge whether newCheck appears vertically down in board
              // Precondition: bingo is true, the length of board is at least m+1 and the length of newCheck is at least j+1
              for(j=2;bingo && m<board.length && j<newCheck.length();j++,m++){
                if(board[m].charAt(k)!=newCheck.charAt(j)) bingo=false;
              }
            }
            if(bingo){
              // Goal: append the word in the newCheck whether it has been found in board
              // Precondition: the length of newCheck is at least j+1
              for(j=0;j<newCheck.length();j++){
                result.append(newCheck.charAt(j));
              }
              if(i!=check.length()) result.append(' ');
            }
          }
          m=markRow;
          k=markCol;
          // judge whether newCheck appears vertically up in board 
          if(!bingo && m>=1 && k<board[m-1].length() && board[m-1].charAt(k)==newCheck.charAt(1)){
            bingo=true;
            if(m<newCheck.length()) bingo=false;
            else{
              m=m-2;
              // Goal: judge whether newCheck appears vertically up in board
              // Precondition: bingo is true, m is at least 0 and the length of newCheck is at least j+1
              for(j=2;bingo && m>=0 && j<newCheck.length();j++,m--){
                if(board[m].charAt(k)!=newCheck.charAt(j)) bingo=false;
              }
            }
            if(bingo){
              // Goal: append the word in the newCheck whether it has been found in board
              // Precondition: the length of newCheck is at least j+1
              for(j=0;j<newCheck.length();j++){
                result.append(newCheck.charAt(j));
              }
              if(i!=check.length()) result.append(' ');
            }
          }
          m=markRow;
          k=markCol;
          // judge whether newCheck appears diagonally(left) down in board
          if(!bingo && m<board.length-1 && k<board[m+1].length()-1 && board[m+1].charAt(k+1)==newCheck.charAt(1)){
            bingo=true;
            m=m+2;
            k=k+2;
            // Goal: judge whether newCheck appears diagonally down in board
            // Precondition: bingo is true, the length of board is at least m+1 the length of board[m] is at least k+1 and the length of newCheck is at least j+1
            for(j=2;bingo && m<board.length && k<board[m].length() && j<newCheck.length();j++,m++,k++){
              if(board[m].charAt(k)!=newCheck.charAt(j)) bingo=false;
            }
            if (bingo && j==newCheck.length()){
              // Goal: append the word in the newCheck whether it has been found in board
              // Precondition: the length of newCheck is at least j+1
              for(j=0;j<newCheck.length();j++){
                result.append(newCheck.charAt(j));
              }
              if(i!=check.length()) result.append(' ');
            }
          }
          m=markRow;
          k=markCol;
          // judge whether newCheck appears diagonally(left) up in board
          if(!bingo && m>=1 && k>=1 && k<board[m-1].length() && board[m-1].charAt(k-1)==newCheck.charAt(1)){
            bingo=true;
            m=m-2;
            k=k-2;
            // Goal: judge whether newCheck appears diagonally up in board
            // Precondition: bingo is true, m is at least 0, k is at least 0 and the length of newCheck is at least j+1
            for(j=2;bingo && m>=0 && k>=0 && j<newCheck.length();j++,m--,k--){
              if(board[m].charAt(k)!=newCheck.charAt(j)) bingo=false;
            }
            if (bingo && j==newCheck.length()){
              // Goal: append the word in the newCheck whether it has been found in board
              // Precondition: the length of newCheck is at least j+1
              for(j=0;j<newCheck.length();j++){
                result.append(newCheck.charAt(j));
              }
              if(i!=check.length()) result.append(' ');
            }
          }
          m=markRow;
          k=markCol;
          // judge whether newCheck appears diagonally(right) down in board
          if(!bingo && m<board.length-1 && k>=1 && k<board[m+1].length() && board[m+1].charAt(k-1)==newCheck.charAt(1)){
            bingo=true;
            m=m+2;
            k=k-2;
            // Goal: judge whether newCheck appears diagonally down in board
            // Precondition: bingo is true, the length of board is at least m+1, k is at least 0 and the length of newCheck is at least j+1
            for(j=2;bingo && m<board.length && k>=0 && j<newCheck.length();j++,m++,k--){
              if(board[m].charAt(k)!=newCheck.charAt(j)) bingo=false;
            }
            if (bingo && j==newCheck.length()){
              // Goal: append the word in the newCheck whether it has been found in board
              // Precondition: the length of newCheck is at least j+1
              for(j=0;j<newCheck.length();j++){
                result.append(newCheck.charAt(j));
              }
              if(i!=check.length()) result.append(' ');
            }
          }
          m=markRow;
          k=markCol;
          // judge whether newCheck appears diagonally(right) up in board
          if(!bingo && m>=1 && k<board[m-1].length()-1 && board[m-1].charAt(k+1)==newCheck.charAt(1)){
            bingo=true;
            m=m-2;
            k=k+2;
            // Goal: judge whether newCheck appears diagonally up in board
            // Precondition: bingo is true, m is at least 0, the length of board[m] is at least k+1 and the length of newCheck is at least j+1
            for(j=2;bingo && m>=0 && k<board[m].length() && j<newCheck.length();j++,m--,k++){
              if(board[m].charAt(k)!=newCheck.charAt(j)) bingo=false;
            }
            if (bingo && j==newCheck.length()){
              // Goal: append the word in the newCheck whether it has been found in board
              // Precondition: the length of newCheck is at least j+1
              for(j=0;j<newCheck.length();j++){
                result.append(newCheck.charAt(j));
              }
              if(i!=check.length()) result.append(' ');
            }
          }
        }
      }
    }
     return HW2.removeExtraSpaces(result.toString());
  }
}