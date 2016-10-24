package test;

import static org.junit.Assert.*;
import wenmin.*;
import org.junit.Test;
import java.util.*;

public class TestParkingStructure {

	@Test
	public void structureTest1() {
		ArrayList<ParkingSpot> list = new ArrayList<ParkingSpot>();
		ParkingSpot ps1 = new ParkingSpot(new Geoloc(10, 10), 3, true);
		ParkingSpot ps2 = new ParkingSpot(new Geoloc(5, 5), 1, false);
		list.add(ps1);
		list.add(ps2);
		ParkingStructure ps = new ParkingStructure(list, new Geoloc(4, 4));
		assertEquals(ps.getPosition().getX(), 4);
		assertEquals(ps.getPosition().getY(), 4);
		assertEquals(ps.getNumavailable(), 1);
		assertEquals(ps.getNumoccupied(), 1);
		assertEquals(ps.getspotArr().get(1).getType(), 1);
		assertEquals(ps.getspotArr().get(1).isAvailable(), false);
	}
	
	@Test
	public void structureTest2() {
		ArrayList<ParkingSpot> list = new ArrayList<ParkingSpot>();
		ParkingSpot ps1 = new ParkingSpot(new Geoloc(10, 10), 2, false);
		list.add(ps1);
		ParkingStructure ps = new ParkingStructure(list, new Geoloc(3, 3));
		assertEquals(ps.getNumavailable(), 0);
		assertEquals(ps.getNumoccupied(), 1);
		assertEquals(ps.getspotArr().get(0).getType(), 2);
		assertEquals(ps.getspotArr().get(0).isAvailable(), false);
	}

}
