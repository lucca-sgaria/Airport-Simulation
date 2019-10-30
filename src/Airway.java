import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

public class Airway extends Thread {
    private long airwayId;
    private long height;
    private long flighTime;
    private List<Plane> planes = new ArrayList<>();

    private Semaphore semaphore = new Semaphore(1);

    public Airway(long airwayId, long height, long flighTime) {
        this.airwayId = airwayId;
        this.height = height;
        this.flighTime = flighTime;
    }

    @Override
    public void run() {
//        while (true) {
//            if(planes.size() > 0)
//        }
    }

    public long getAirwayId() {
        return airwayId;
    }

    public void setAirwayId(long airwayId) {
        this.airwayId = airwayId;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getFlighTime() {
        return flighTime;
    }

    public void setFlighTime(long flighTime) {
        this.flighTime = flighTime;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public String toString() {
        return "Airway{" +
                "airwayId=" + airwayId +
                ", height=" + height +
                ", flighTime=" + flighTime +
                ", semaphore=" + semaphore +
                '}';
    }

    @SuppressWarnings("Duplicates")
    public String toPrintString() {
        String str = "Estrada " + airwayId;
        str += " - altura " + height;
//        str += " - tempo de voo " + flighTime;
        str += " - aviões [ ";
        str += planes
                .stream()
                .map(plane -> "[" + plane.getPlaneId()
                              + ";" + plane.getPlaneState() + "] ")
                .collect(Collectors.joining(" - "));
        str+= "].";
        return str;
    }


    public void addPlane(Plane plane) {
        this.planes.add(plane);
    }

    public void removePlane(Plane plane) {
        this.planes.remove(plane);
    }
}
