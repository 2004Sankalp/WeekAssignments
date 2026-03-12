import java.util.*;

class PlagiarismDetector {

    private HashMap<String, Set<String>> ngramIndex = new HashMap<>();

    public void indexDocument(String docId, String text) {

        String[] words = text.split(" ");

        int n = 5;

        for(int i = 0; i <= words.length - n; i++) {

            StringBuilder gram = new StringBuilder();

            for(int j = 0; j < n; j++)
                gram.append(words[i+j]).append(" ");

            String key = gram.toString().trim();

            ngramIndex
                    .computeIfAbsent(key, k -> new HashSet<>())
                    .add(docId);
        }
    }

    public int analyze(String text) {

        String[] words = text.split(" ");
        int matches = 0;

        for(int i = 0; i <= words.length - 5; i++) {

            String gram = words[i]+" "+words[i+1]+" "+words[i+2]+" "+words[i+3]+" "+words[i+4];

            if(ngramIndex.containsKey(gram))
                matches++;
        }

        return matches;
    }
}