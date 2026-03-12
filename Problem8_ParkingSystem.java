import java.util.*;

class ParkingSpot {

    String plate;
    long entryTime;

    ParkingSpot(String plate) {
        this.plate = plate;
        this.entryTime = System.currentTimeMillis();
    }
}

public class Problem8_ParkingSystem {

    ParkingSpot[] table;
    int size;

    public Problem8_ParkingSystem(int capacity) {

        table = new ParkingSpot[capacity];
        size = capacity;
    }

    private int hash(String plate) {

        return Math.abs(plate.hashCode()) % size;
    }

    public int parkVehicle(String plate) {

        int index = hash(plate);

        int probes = 0;

        while(table[index] != null) {

            index = (index + 1) % size;
            probes++;
        }

        table[index] = new ParkingSpot(plate);

        System.out.println("Assigned spot " + index + " probes:" + probes);

        return index;
    }

    public void exitVehicle(String plate) {

        for(int i=0;i<size;i++) {

            if(table[i]!=null && table[i].plate.equals(plate)) {

                long duration = System.currentTimeMillis() - table[i].entryTime;

                table[i]=null;

                System.out.println("Spot freed. Duration: "+duration/60000+" mins");

                return;
            }
        }
    }
}