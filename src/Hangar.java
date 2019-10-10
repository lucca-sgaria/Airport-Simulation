import java.util.ArrayList;
import java.util.List;

public class Hangar extends Thread {

    private long hangarId = 0;
    private List<Plane> planes = new ArrayList<>();
    private Airport airport;

    public long getHangarId() {
        return hangarId;
    }

    public void setHangarId(long hangarId) {
        this.hangarId = hangarId;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }

    public void addPlane(Plane plane) {
        this.planes.add(plane);
    }

    @Override
    public void run() {
        try {
            Plane plane = null;
            while (true) {
                sleep(10000);

                if(planes.size() > 0) {
                    plane = planes.get(0);
                    if(plane.getPlaneState() == PlaneState.WAITTAKEOFF) {
                        plane.setPlaneState(PlaneState.READYTAKEOFF);
                    }
                }

            }
        } catch (InterruptedException e) {
        }
    }
}
