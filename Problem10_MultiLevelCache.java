import java.util.*;

class LRUCache<K,V> extends LinkedHashMap<K,V>{

    private int capacity;

    public LRUCache(int capacity){

        super(capacity,0.75f,true);
        this.capacity=capacity;
    }

    protected boolean removeEldestEntry(Map.Entry<K,V> eldest){

        return size()>capacity;
    }
}

public class Problem10_MultiLevelCache {

    LRUCache<String,String> L1 = new LRUCache<>(10000);

    HashMap<String,String> L2 = new HashMap<>();

    HashMap<String,String> database = new HashMap<>();

    public String getVideo(String videoId){

        if(L1.containsKey(videoId)){

            return "L1 HIT";
        }

        if(L2.containsKey(videoId)){

            String video=L2.get(videoId);

            L1.put(videoId,video);

            return "L2 HIT → promoted to L1";
        }

        String video=database.get(videoId);

        if(video!=null){

            L2.put(videoId,video);

            return "L3 HIT → stored in L2";
        }

        return "Video not found";
    }
}