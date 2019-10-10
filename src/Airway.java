import java.util.ArrayList;
import java.util.List;

public class Airway extends Thread {
    private long airwayId;
    private long height;
    private long flighTime;
    private List<Plane> planes = new ArrayList<>();

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

    @Override
    public String toString() {
        return "Airway{" +
                "airwayId=" + airwayId +
                ", height=" + height +
                ", flighTime=" + flighTime +
                ", planes=" + planes +
                '}';
    }

    public void addPlane(Plane plane) {
        this.planes.add(plane);
    }

    public void removePlane(Plane plane) {
        this.planes.remove(plane);
    }
}
