import java.util.*;

class UsernameChecker {

    private HashMap<String, Integer> usernameToUserId = new HashMap<>();
    private HashMap<String, Integer> attemptFrequency = new HashMap<>();

    public boolean checkAvailability(String username) {

        attemptFrequency.put(username,
                attemptFrequency.getOrDefault(username, 0) + 1);

        return !usernameToUserId.containsKey(username);
    }

    public void registerUsername(String username, int userId) {
        usernameToUserId.put(username, userId);
    }

    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for(int i = 1; i <= 3; i++) {

            String suggestion = username + i;

            if(!usernameToUserId.containsKey(suggestion))
                suggestions.add(suggestion);
        }

        String modified = username.replace("_", ".");

        if(!usernameToUserId.containsKey(modified))
            suggestions.add(modified);

        return suggestions;
    }

    public String getMostAttempted() {

        String most = "";
        int max = 0;

        for(Map.Entry<String,Integer> entry : attemptFrequency.entrySet()) {

            if(entry.getValue() > max) {

                max = entry.getValue();
                most = entry.getKey();
            }
        }

        return most + " (" + max + " attempts)";
    }
}