public class Plane extends Thread {

    private long planeId;
    private String planeState = PlaneState.CREATED;
    private int numLandings;
    private int numTakeoffs;
    private Airport airport;

    public Plane(long planeId, Airport airport) {
        super();
        this.planeId = planeId;
        this.airport = airport;
    }

    @Override
    public void run() {

        //System.out.println("Plane started - > " + toString());
        try {
            sleep(10000);
            if(this.planeId == 2) sleep(8000);
            long waitTime = 0;
            while (true) {
                //System.out.println("Plane here -> " + toString());

                if (planeState == PlaneState.CREATED) {
                    waitTime = airport.sendToHighestAirway(this);
                    this.planeState = PlaneState.FLYING;

                } else if (planeState == PlaneState.FLYING) {
                    sleep(waitTime);

                    if(airport.hasNextAirway(this)) {
                        waitTime = airport.sendToLowerAirway(this);
                    } else {
                        waitTime = airport.sendToLandRunway(this);
                        this.planeState = PlaneState.LANDING;
                    }

                } else if(planeState == PlaneState.LANDING) {
                    sleep(waitTime);

                    airport.sendToHangar(this);
                    this.planeState = PlaneState.WAITTAKEOFF;

                } else if(planeState == PlaneState.READYTAKEOFF) {

                    waitTime = airport.sendToTakeOffRunway(this);
                    this.planeState = PlaneState.TAKEOFF;

                } else if(planeState == PlaneState.TAKEOFF) {
                    sleep(waitTime);

                    waitTime = airport.sendToAirway(this);
                    this.planeState = PlaneState.FLYING;
                } else {
                    System.out.println("Waiting hangar");
                    sleep(6000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public long getPlaneId() {
        return planeId;
    }

    public void setPlaneId(long planeId) {
        this.planeId = planeId;
    }

    public String getPlaneState() {
        return planeState;
    }

    public void setPlaneState(String planeState) {
        this.planeState = planeState;
    }

    public int getNumLandings() {
        return numLandings;
    }

    public void setNumLandings(int numLandings) {
        this.numLandings = numLandings;
    }

    public int getNumTakeoffs() {
        return numTakeoffs;
    }

    public void setNumTakeoffs(int numTakeoffs) {
        this.numTakeoffs = numTakeoffs;
    }

    @Override
    public String toString() {
        return "{" +
                "planeId=" + planeId +
                ", planeState=" + planeState +
                ", numLandings=" + numLandings +
                ", numTakeoffs=" + numTakeoffs +
                '}';
    }
}
