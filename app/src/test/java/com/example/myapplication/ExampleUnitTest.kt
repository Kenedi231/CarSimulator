package com.example.myapplication

import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun car_testing_turn_on_engine() {
        var car = Car()
        assertEquals(car.TurnOnEngine(), true)
        assertEquals(car.TurnOnEngine(), false)
    }

    @Test
    fun car_testing_turn_off_engine() {
        var car = Car()
        car.TurnOnEngine()
        assertEquals(car.TurnOffEngine(), true)
        assertEquals(car.TurnOffEngine(), false)

        car.TurnOnEngine()
        car.SetGear(1)
        assertEquals(car.TurnOffEngine(), false)

        car.SetSpeed(10)
        car.SetGear(0)
        assertEquals(car.TurnOffEngine(), false)

        car.SetSpeed(0)
        assertEquals(car.TurnOffEngine(), true)

        car.TurnOnEngine()
        car.SetGear(-1)
        car.SetSpeed(10)
        assertEquals(car.TurnOffEngine(), false)

        car.SetGear(0)
        assertEquals(car.TurnOffEngine(), false)
        car.SetSpeed(0)
        assertEquals(car.TurnOffEngine(), true)
    }
}
