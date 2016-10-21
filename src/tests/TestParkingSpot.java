package tests;

import static org.junit.Assert.*;
import wenmin.*;

import org.junit.Test;

public class TestParkingSpot {

	@Test
	public void parkingSpotTest1() {
		ParkingSpot ps= new ParkingSpot(new Geoloc(9, 9), 3, true);
		assertTrue(ps.getPosition().equals(new Geoloc(9, 9)));
		assertEquals(ps.getType(), 3);
		assertEquals(ps.isAvailable(), true);
	}
	@Test
	public void parkingSpotTest2() {
		ParkingSpot ps= new ParkingSpot(new Geoloc(1, 1), 3, false);
		assertTrue(ps.getPosition().equals(new Geoloc(1, 1)));
		assertEquals(ps.getType(), 3);
		assertEquals(ps.isAvailable(), false);
	}
	@Test
	public void parkingSpotTest3() {
		ParkingSpot ps= new ParkingSpot(new Geoloc(10, 10), 3, true);
		assertFalse(ps.getPosition().equals(new Geoloc(5, 10)));
		assertEquals(ps.getType(), 3);
		assertEquals(ps.isAvailable(), true);
	}
	
	@Test
	public void ParkingSpotTest4() {
		ParkingSpot ps1 = new ParkingSpot(new Geoloc(10, 10), 1, true);
		ParkingSpot ps2 = new ParkingSpot(new Geoloc(10, 10), 1, true);
		assertTrue(ps1.equals(ps2));
	}

	@Test
	public void ParkingSpotTest5() {
		ParkingSpot ps1 = new ParkingSpot(new Geoloc(10, 10), 1, true);
		ParkingSpot ps2 = new ParkingSpot(new Geoloc(10, 1), 2, false);
		assertFalse(ps1.equals(ps2));
	}

}
