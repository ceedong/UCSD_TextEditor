package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 * Two problems here: cannot write the constructor
 * not transfer the string to lowercase version
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
   public DictionaryLL() {
	   dict = new LinkedList<String>();
   }// TODO: Add a constructor


    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	word = word.toLowerCase();
    	// TODO: Implement this method
    	if (dict.contains(word)) {
    		return false;
    	} else {
    		dict.add(word);
    		return true;
    	}
        
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        // TODO: Implement this method
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	s = s.toLowerCase();
        //TODO: Implement this method
        return dict.contains(s);
    }

    
}
