import java.util.ArrayList;
import java.util.List;

public class Runway {
    private long runwayId;
    private long usageTime;
    private List<Plane> planes = new ArrayList<>();
    private int function = RunwayFunction.TAKEOFF;

    public Runway(long runwayId, long usageTime, int function) {
        this.runwayId = runwayId;
        this.usageTime = usageTime;
        this.function = function;
    }

    public long getRunwayId() {
        return runwayId;
    }

    public void setRunwayId(long runwayId) {
        this.runwayId = runwayId;
    }

    public long getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(long usageTime) {
        this.usageTime = usageTime;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }

    public int getFunction() {
        return function;
    }

    public void setFunction(int function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return "Runway{" +
                "runwayId=" + runwayId +
                ", usageTime=" + usageTime +
                ", planes=" + planes +
                ", function=" + function +
                '}';
    }

    public void addPlane(Plane plane) {
        this.planes.add(plane);
    }

    public void removePlane(Plane plane) {
        this.planes.remove(plane);
    }
}
