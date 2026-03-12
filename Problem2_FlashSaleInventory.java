import java.util.*;

class FlashSaleInventory {

    private HashMap<String, Integer> stock = new HashMap<>();
    private HashMap<String, Queue<Integer>> waitingList = new HashMap<>();

    public void addProduct(String productId, int quantity) {
        stock.put(productId, quantity);
        waitingList.put(productId, new LinkedList<>());
    }

    public int checkStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }

    public synchronized String purchaseItem(String productId, int userId) {

        int currentStock = stock.getOrDefault(productId, 0);

        if(currentStock > 0) {

            stock.put(productId, currentStock - 1);

            return "Success, remaining stock: " + (currentStock - 1);
        }

        Queue<Integer> queue = waitingList.get(productId);

        queue.add(userId);

        return "Added to waiting list, position #" + queue.size();
    }
}