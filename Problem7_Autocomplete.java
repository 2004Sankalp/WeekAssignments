import java.util.*;

class TrieNode {

    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord;
}

public class Problem7_Autocomplete {

    TrieNode root = new TrieNode();
    HashMap<String,Integer> frequency = new HashMap<>();

    public void insert(String word) {

        TrieNode node = root;

        for(char c : word.toCharArray()) {

            node.children.putIfAbsent(c,new TrieNode());
            node = node.children.get(c);
        }

        node.isWord = true;

        frequency.put(word, frequency.getOrDefault(word,0)+1);
    }

    public List<String> search(String prefix) {

        List<String> results = new ArrayList<>();

        TrieNode node = root;

        for(char c : prefix.toCharArray()) {

            if(!node.children.containsKey(c))
                return results;

            node = node.children.get(c);
        }

        dfs(node, prefix, results);

        results.sort((a,b)->frequency.get(b)-frequency.get(a));

        return results.subList(0, Math.min(10, results.size()));
    }

    private void dfs(TrieNode node, String word, List<String> results) {

        if(node.isWord)
            results.add(word);

        for(char c : node.children.keySet()) {

            dfs(node.children.get(c), word+c, results);
        }
    }
}