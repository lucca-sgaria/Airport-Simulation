package model;

import util.PlaneState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hangar extends Thread {

    private long hangarId = 0;
    private List<Plane> planes = new ArrayList<>();
    private Airport airport;

    public Hangar(Airport airport) {
        super();
        this.airport = airport;
    }

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

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
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
                        airport.getRunwaysTakeoff().get(0).getSemaphore().acquire();
                        plane.setPlaneState(PlaneState.READYTAKEOFF);
                    }
                }

            }
        } catch (InterruptedException e) {
        }
    }

    @SuppressWarnings("Duplicates")
    public String toPrintString() {
        String str = "Hangar ";
        str += " - aviões [ ";
        str += planes
                .stream()
                .map(plane -> "[ id=" + plane.getPlaneId()
                        + ";" + plane.getPlaneState() + "] ")
                .collect(Collectors.joining(" - "));
        str+= "].";
        return str;
    }
}
