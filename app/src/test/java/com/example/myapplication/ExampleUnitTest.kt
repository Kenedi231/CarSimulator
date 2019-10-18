package com.example.myapplication

import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun car_testing_turn_on_engine() {
        val car = Car()
        assertEquals(car.TurnOnEngine(), true)
        assertEquals(car.TurnOnEngine(), false)
    }

    @Test
    fun car_testing_turn_off_engine() {
        val car = Car()
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

    @Test
    fun car_testing_set_gear() {
        val car = Car()
        car.TurnOnEngine()
        assertEquals(car.SetGear(1), true)

        car.SetSpeed(19)
        assertEquals(car.SetGear(2), false)

        car.SetSpeed(25)
        assertEquals(car.SetGear(2), true)

        car.SetSpeed(45)
        assertEquals(car.SetGear(4), true)
        assertEquals(car.SetGear(-1), false)

        car.SetSpeed(90)
        assertEquals(car.SetGear(5), true)

        car.SetSpeed(150)
        assertEquals(car.SetGear(0), true)

        car.SetSpeed(0)
        assertEquals(car.SetGear(-1), true)

        car.SetSpeed(10)
        assertEquals(car.SetGear(1), false)

        car.SetSpeed(0)
        assertEquals(car.SetGear(1), true)

        car.SetGear(-1)
        car.TurnOffEngine()
    }

    @Test
    fun car_testing_set_speed() {
        val car = Car()
        car.TurnOnEngine()

        assertEquals(car.SetSpeed(10), false)

        car.SetGear(-1)
        assertEquals(car.SetSpeed(10), true)
        assertEquals(car.SetSpeed(0), true)

        car.SetGear(1)
        assertEquals(car.SetSpeed(35), false)
        assertEquals(car.SetSpeed(30), true)

        car.SetGear(3)
        assertEquals(car.SetSpeed(60), true)

        car.SetGear(5)
        assertEquals(car.SetSpeed(120), true)
        assertEquals(car.SetSpeed(160), false)

        car.SetGear(0)
        assertEquals(car.SetSpeed(30), true)
        assertEquals(car.SetSpeed(40), false)
        assertEquals(car.SetSpeed(0), true)
        assertEquals(car.SetSpeed(-10), false)

        car.TurnOffEngine()

    }
}
