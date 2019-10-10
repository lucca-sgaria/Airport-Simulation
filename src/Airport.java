import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Airport extends  Thread {
    List<Runway> runwaysLanding = new ArrayList<>();
    List<Runway> runwaysTakeoff = new ArrayList<>();
    List<Airway> airways = new ArrayList<>();
    List<Plane> planes = new ArrayList<>();
    Hangar hangar = new Hangar();

    @Override
    public void run() {
        while (true) {

            System.out.println("Airport -------------------------------------------------------------------------------------- ");
            System.out.println("RunwayTakeOff -> ");
            System.out.println(runwaysTakeoff.get(0).toString());
            System.out.println("runwaysLanding -> ");
            System.out.println(runwaysLanding.get(0).toString());
            System.out.println("airways -> ");
            airways.forEach(airway -> {
                System.out.println(airway.toString());
            });
            System.out.println("hangar -> ");
            hangar.getPlanes().stream().forEach(plane -> System.out.println("plane -> " + plane.toString()));
            System.out.println("-------------------------------------------------------------------------------------- ");
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addRunwayLanding(Runway runway) {
        this.runwaysLanding.add(runway);
    }

    public void addRunwayTakeoff(Runway runway) {
        this.runwaysTakeoff.add(runway);
    }

    public void addAirway(Airway airway) {
        this.airways.add(airway);
    }

    public void addPlane(Plane plane) {
        this.planes.add(plane);
    }

    public void startPlanes() {
        planes.forEach(Thread::start);
    }

    public void sendToHangar(Plane plane) {
        this.runwaysLanding.get(0).removePlane(plane);

        this.hangar.addPlane(plane);
    }

    public long sendToAirway(Plane plane) {
        runwaysTakeoff.get(0).removePlane(plane);
        return  sendToHighestAirway(plane);
    }

    public long sendToHighestAirway(Plane plane) {
        Airway next = this.airways
                .stream()
                .max(Comparator.comparing(Airway::getHeight))
                .orElse(null);

        next.addPlane(plane);

        return next.getFlighTime();
    }

    public boolean hasNextAirway(Plane plane) {
        Airway airway = findPlaneInAirway(plane);

        long count = this.airways
                .stream()
                .filter(way -> way.getHeight() < airway.getHeight())
                .count();

        return count > 0;
    }

    private Airway findPlaneInAirway(Plane plane) {
        Airway airway = this.airways
                .stream()
                .filter(way -> way.getPlanes().contains(plane))
                .findFirst().orElse(null);

        return airway;
    }

    public long sendToLowerAirway(Plane plane) {
        Airway airway = findPlaneInAirway(plane);

        Airway nextAirway = this.airways
                .stream()
                .filter(way -> way.getHeight() < airway.getHeight())
                .max(Comparator.comparing(Airway::getHeight))
                .orElse(null);

        airway.removePlane(plane);
        nextAirway.addPlane(plane);

        return nextAirway.getFlighTime();
    }

    public long sendToLandRunway(Plane plane) {
        Airway airway = findPlaneInAirway(plane);

        airway.removePlane(plane);

        Runway runway = runwaysLanding.get(0);
        runway.addPlane(plane);

        return runway.getUsageTime();
    }

    public long sendToTakeOffRunway(Plane plane) {
        hangar.getPlanes().remove(plane);


        Runway runway = runwaysTakeoff.get(0);
        runway.addPlane(plane);

        return runway.getUsageTime();
    }

    private Runway findPlaneInRunway(Plane plane) {
        Runway runway = this.runwaysLanding
                .stream()
                .filter(way -> way.getPlanes().contains(plane))
                .findFirst().orElse(null);

        if(runway != null) {
            runway = this.runwaysTakeoff
                    .stream()
                    .filter(way -> way.getPlanes().contains(plane))
                    .findFirst().orElse(null);
        }

        return runway;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }

    public List<Runway> getRunwaysLanding() {
        return runwaysLanding;
    }

    public void setRunwaysLanding(List<Runway> runwaysLanding) {
        this.runwaysLanding = runwaysLanding;
    }

    public List<Runway> getRunwaysTakeoff() {
        return runwaysTakeoff;
    }

    public void setRunwaysTakeoff(List<Runway> runwaysTakeoff) {
        this.runwaysTakeoff = runwaysTakeoff;
    }

    public List<Airway> getAirways() {
        return airways;
    }

    public void setAirways(List<Airway> airways) {
        this.airways = airways;
    }

    public Hangar getHangar() {
        return hangar;
    }

    public void setHangar(Hangar hangar) {
        this.hangar = hangar;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "runwaysLanding=" + runwaysLanding +
                ", runwaysTakeoff=" + runwaysTakeoff +
                ", airways=" + airways +
                ", planes=" + planes +
                ", hangar=" + hangar +
                '}';
    }


}
