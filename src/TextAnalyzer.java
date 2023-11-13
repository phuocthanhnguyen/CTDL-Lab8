
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class TextAnalyzer {
	// <word, its positions>
	private Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();

	// load words in the text file given by fileName and store into map by using add
	// method in Task 2.1.
	// Using BufferedReader reffered in file TextFileUtils.java
	public void load(String fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = null;
		while (true) {
			line = reader.readLine();

			if (line == null)
				break;
			StringTokenizer tokens = new StringTokenizer(line, " ");

			while (tokens.hasMoreTokens()) {
				System.out.println(tokens.nextToken());
			}
		}
		reader.close();
	}
	// In the following method, if the word is not in the map, then adding that word
	// to the map containing the position of the word in the file. If the word is
	// already in the map, then its word position is added to the list of word
	// positions for this word.
	// Remember to negate the word position if the word is at the end of a line in
	// the text file

	public void add(String word, int position) {
		if (map.containsKey(word)) {
			map.get(word).add(position);
		} else {
			ArrayList<Integer> list = new ArrayList<>();
			list.add(position);
			map.put(word, list);
		}
	}

	// This method should display the words of the text file along with the
	// positions of each word, one word per line, in alphabetical order
	public void displayWords() {
		TreeMap<String, ArrayList<Integer>> treeMap = new TreeMap<String, ArrayList<Integer>>(map);
		for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
			String key = entry.getKey();
			ArrayList<Integer> val = entry.getValue();
			for (Integer en : val) {
				System.out.println(key + en);
			}
		}
	}

	// This method will display the content of the text file stored in the map
	public void displayText() {
		TreeMap<String, ArrayList<Integer>> treeMap = new TreeMap<String, ArrayList<Integer>>(map);
		ArrayList<String> el = new ArrayList<String>();
		for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
			String key = entry.getKey();
			ArrayList<Integer> val = entry.getValue();
			for (Integer position : val) {
				el.add(position, key);
			}
		}
		for (String word : el) {
			System.out.println(word + " ");
		}
	}

	// This method will return the word that occurs most frequently in the text file
	public String mostFrequentWord() {
		ArrayList<ArrayList<Integer>> ar = (ArrayList<ArrayList<Integer>>) map.values();
		int max = ar.get(0).size();
		for (int i = 1; i < ar.size(); i++) {
			if (max < ar.get(i).size())
				max = ar.get(i).size();
		}
		for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
			String key = entry.getKey();
			int size = entry.getValue().size();
			if (max == size)
				return key;
		}
		return null;
	}

}
