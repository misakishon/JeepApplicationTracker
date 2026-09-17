package com.example.passengerdriverapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.passengerdriverapp.data.components.Login;
import org.junit.Test;

public class DemoAccountsTest {
    @Test
    public void sampleDriverAndPassengerAccountsAreValid() {
        Login driverLogin = new Login("driver@demo.com", "driver123");
        Login passengerLogin = new Login("passenger@demo.com", "passenger123");

        assertTrue(driverLogin.isValid());
        assertTrue(passengerLogin.isValid());
        assertEquals("driver@demo.com", driverLogin.getEmail());
        assertEquals("passenger@demo.com", passengerLogin.getEmail());
    }
}
