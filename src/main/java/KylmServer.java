import java.io.IOException;
import java.util.Arrays;

import kylm.model.ngram.NgramLM;
import kylm.model.ngram.reader.*;

public class KylmServer {
	protected NgramLM lm;

	public KylmServer(String fileName) throws IOException {
		NgramReader reader = new ArpaNgramReader();
		lm = reader.read(fileName);
	}

	protected int[] getWordIds(String[] words) {
		int[] ids = lm.getSentenceIds(words);
		return Arrays.copyOfRange(ids,1,lm.getCountTerminals() ? (ids.length-1) : (ids.length));
	}

	protected int[] getSentenceIds(String[] words) {
		return lm.getSentenceIds(words);
	}

	public float getUnigramEntropy(String word) {
		return lm.getWordEntropy(new int[]{ lm.getId(word) },0);
	}

	public float getWordEntropy(String[] words) {
		return lm.getWordEntropy(getWordIds(words),words.length-1);
	}

	public float getWordEntropyAt(String[] words,int pos) {
		return lm.getWordEntropy(getWordIds(words),pos);
	}

	public String[] getVocabulary() {
		return lm.getVocabulary();
	}

	/*
	 * Following code not works well in some environment (´・ω・｀)

	public float getWordEntropy(String[] words) {
		return lm.getWordEntropy(getWordIds(words),words.length-1);
	}

	public float getWordEntropy(String[] words,int pos) {
		return lm.getWordEntropy(getWordIds(words),pos);
	}
	*/

	public float[] getWordEntropies(String[] words) {
		return lm.getWordEntropies(words);
	}

	public float getSentenceEntropy(String[] words) {
		return lm.getSentenceEntropy(words);
	}

	public boolean isInVocab(String word) {
		return lm.isInVocab(word);
	}

	public String getUnknownSymbol() {
		return lm.getUnknownSymbol();
	}

	public String getStartSymbol() {
		return lm.getStartSymbol();
	}

	public String getTerminalSymbol() {
		return lm.getTerminalSymbol();
	}

	public boolean isClosed() {
		return lm.isClosed();
	}

	public int getN() {
		return lm.getN();
	}

	public void setN(int n) {
		lm.setN(n);
	}
}
