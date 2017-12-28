package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		String[] result = sourceText.split("[\\s]+");// TODO: Implement this method
		if (starter == "") {
		starter = result[0];
		} else {
			return;
		}
		System.out.print(result[0] + "\n");
		String prevWord = starter;
		
		for(int i =1; i< result.length; i++) {
			boolean flag = false;
			String w = result[i];
			//System.out.print("--------------------\n");
			//System.out.print(w + "\n");
			//System.out.print("--------------------\n");
		for (ListNode check : wordList) {
			if (check.getWord().equals(prevWord)) {
				flag = true;
				check.addNextWord(w);
			}
		}
			if (flag == false) {
				ListNode newNode = new ListNode(prevWord);
				newNode.addNextWord(w);
				wordList.add(newNode);
			}
			
			prevWord = w;
			
		}
		boolean flagForLast = false;
		String lastWord = result[result.length -1];
		for (ListNode check : wordList) {
			if (check.getWord().equals(lastWord)) {
				check.addNextWord(starter);
				flagForLast = true;
			}
		}
		if (flagForLast == false) {
			ListNode newNode = new ListNode(lastWord);
			newNode.addNextWord(starter);
			wordList.add(newNode);
		}
	  String trials = toString();
	  //System.out.print(trials);
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		if (numWords == 0 || starter == null) {
			return "";
		}
	    String currWord = starter;
	    String output = "";
	    String w = "";
	    output += currWord;
	    numWords--;
	    while(numWords >0) {
	    	for(ListNode check : wordList) {
	    		if (check.getWord().equals(currWord)) {
	    			 w = check.getRandomNextWord(rnGenerator);
	    			output += " "+w;
	    			break;
	    			}
	    		}
	    	currWord = w;
	    	numWords--;
	    }
	    
	   // System.out.print("*****"+output+"*******");
	    // TODO: Implement this method
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		starter = "";
		 List<ListNode> newWordLists = new LinkedList<ListNode>();
		 wordList = newWordLists;
		 train(sourceText);// TODO: Implement this method.
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		//String textString ="I love cats. I hate dogs. I I I I I I I I I I I I I I I I love cats. I I I I I I I I I I I I I I I I hate dogs. I I I I I I I I I like books. I love love. I am a text generator. I love cats. I love cats. I love cats. I love love love socks.";
		//String textString = "hi there hi Leo";
		//System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(10));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		//System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		int length = nextWords.size();
		//System.out.println("The nextWord size is :" + length);
		String result = null;
		int n = generator.nextInt(length);// TODO: Implement this method
		//System.out.println("The number which is generated is :" +n);
//		int counter = 0;
//		for(String x : nextWords) {
//			if (counter == n) {
//				result = x;
//				break;
//			}
//			counter++;
//		}
		result = nextWords.get(n);
	   // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
	    return result;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


