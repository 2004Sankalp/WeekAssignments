import java.util.*;

class AnalyticsDashboard {

    HashMap<String,Integer> pageViews = new HashMap<>();
    HashMap<String,Set<String>> uniqueVisitors = new HashMap<>();
    HashMap<String,Integer> trafficSource = new HashMap<>();

    public void processEvent(String url, String userId, String source) {

        pageViews.put(url, pageViews.getOrDefault(url,0)+1);

        uniqueVisitors
                .computeIfAbsent(url, k -> new HashSet<>())
                .add(userId);

        trafficSource.put(source,
                trafficSource.getOrDefault(source,0)+1);
    }

    public void getDashboard() {

        System.out.println("Top Pages:");

        pageViews.entrySet()
                .stream()
                .sorted((a,b)->b.getValue()-a.getValue())
                .limit(10)
                .forEach(e -> {

                    int unique = uniqueVisitors.get(e.getKey()).size();

                    System.out.println(
                            e.getKey()+" - "+e.getValue()+
                                    " views ("+unique+" unique)"
                    );
                });

        System.out.println("\nTraffic Sources:");

        for(String s : trafficSource.keySet()) {

            System.out.println(s+" : "+trafficSource.get(s));
        }
    }
}