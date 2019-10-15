package com.example.myapplication

class Car {

    private var engine: Boolean = false
    private var direction: String = Companion.STOP
    private var currentSpeed: Int = 0
    private var currentGear: Int = 0 // between -1 and 5

    fun TurnOnEngine(): Boolean {
        if (engine) {
            return false
        }

        engine = true
        return true
    }

    fun TurnOffEngine(): Boolean {
        if (!engine || direction !== "stop" || currentGear !== 0) {
            return false
        }

        engine = false
        return true
    }

    fun SetGear(gear: Int): Boolean {


        currentGear = gear
        return true
    }

    fun SetSpeed(speed: Int): Boolean {
        if ((currentGear == 0 && currentSpeed < speed) || speed < 0) {
            return false
        }

        if (speed == 0) {
            direction = Companion.STOP
        } else if (currentGear == -1) {
            direction = Companion.BACK
        } else {
            direction = Companion.FORWARD
        }

        currentSpeed = speed
        return true
    }

    companion object {
        private const val STOP = "stop"
        private const val FORWARD = "forward"
        private const val BACK = "back"
    }
}