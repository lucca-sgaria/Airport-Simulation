import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

public class Runway {
    private long runwayId;
    private long usageTime;
    private List<Plane> planes = new ArrayList<>();
    private int function = RunwayFunction.TAKEOFF;

    private Semaphore semaphore = new Semaphore(1);

    public Runway(long runwayId, long usageTime, int function) {
        this.runwayId = runwayId;
        this.usageTime = usageTime;
        this.function = function;
    }

    public void addPlane(Plane plane) {
        this.planes.add(plane);
    }

    public void removePlane(Plane plane) {
        this.planes.remove(plane);
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

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public String toString() {
        return "Runway{" +
                "runwayId=" + runwayId +
                ", usageTime=" + usageTime +
                ", function=" + function +
                ", semaphore=" + semaphore +
                '}';
    }

    @SuppressWarnings("Duplicates")
    public String toPrintString() {
        String str = "Pista " + (function ==2 ? " de Pouso " : "de Decolagem");
        str += " - aviões [ ";
        str += planes
                .stream()
                .map(plane -> "[" + plane.getPlaneId()
                        + ";" + plane.getPlaneState() + "] ")
                .collect(Collectors.joining(" - "));
        str+= "].";
        return str;
    }


}
