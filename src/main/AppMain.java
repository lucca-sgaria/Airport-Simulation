package main;

import model.Airport;
import model.Airway;
import model.Plane;
import model.Runway;
import util.Constants;
import util.RunwayFunction;

public class AppMain {
    public static void main(String[] args) {

        Airport airport = new Airport();

        createRunways(airport);
        createPlanes(airport);
        createAirways(airport);

        System.out.println("Airport criado " + airport.toString());

        airport.start();
        airport.startPlanes();
        airport.getHangar().start();

        try {
            airport.join();
            airport.getHangar().join();
            for (Plane plane : airport.getPlanes()) {
                plane.join();
            }


        } catch(Exception e) {
        }

    }

    private static void createAirways(Airport airport) {
        int currentHeight = 3000;
        int currentFlightTime = 10000;
        for (int i = 0; i < Constants.AIRWAYAMOUNT; i++) {
            Airway airway = new Airway(i+1,currentHeight,currentFlightTime);

            currentFlightTime += 3000;
            currentHeight += 10000;

            airport.addAirway(airway);
        }
    }

    private static void createPlanes(Airport airport) {
        for (int i = 0; i < Constants.PLANEAMOUNT; i++) {
            Plane plane = new Plane(i+1,airport);
            airport.addPlane(plane);
        }
    }

    private static void createRunways(Airport airport) {
        for (int i = 0; i < Constants.RUNWAYPAIR; i++) {
            Runway runwayLand = new Runway(i+1,10000, RunwayFunction.LAND);
            Runway runwayTakeoff = new Runway(i+2,10000, RunwayFunction.TAKEOFF);

            airport.addRunwayLanding(runwayLand);
            airport.addRunwayTakeoff(runwayTakeoff);
        }
    }
}
