package model;

import util.PlaneState;

public class Plane extends Thread {

    private long planeId;
    private String planeState = PlaneState.CREATED;
    private Airport airport;

    public Plane(long planeId, Airport airport) {
        super();
        this.planeId = planeId;
        this.airport = airport;
    }

    @Override
    public void run() {

        try {
            sleep(3000);

            long waitTime = 0;
            while (true) {

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

                    sleep(20000);
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

}
