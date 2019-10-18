package com.example.myapplication

class Car {

    private var engine: Boolean = false
    private var direction: String = Companion.STOP
    private var currentSpeed: Int = 0
    private var isBack: Boolean = false
    private var currentGear: Int = 0 // between -1 and 5

    private fun getRangeOfSpeed(gear: Int): Array<Int> {
        var min = 0;
        var max = 150;

        when (gear) {
            -1 -> {
                max = 20
            }
            1 -> {
                max = 30
            }
            2 -> {
                min = 20
                max = 50
            }
            3 -> {
                min = 30
                max = 60
            }
            4 -> {
                min = 40
                max = 90
            }
            5 -> {
                min = 50
                max = 150
            }
            else -> {
                min = 0
                max = 150
            }
        }

        return arrayOf<Int>(min, max) // 0 -> min, 1 -> max
    }

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
        val rangeOfSpeed = getRangeOfSpeed(gear)
        if (gear != 0) {
            if (currentSpeed < rangeOfSpeed[0] || currentSpeed > rangeOfSpeed[1]) {
                return false
            }
        }
        if (currentSpeed != 0) {
            if (currentGear == -1 && gear != 0 || gear == -1) {
                return false
            }
        }

        if (currentGear == -1) {
            isBack = gear - currentGear < 2
        } else if (currentGear != 0) {
            isBack = false
        }
        currentGear = gear
        return true
    }

    fun SetSpeed(speed: Int): Boolean {
        val rangeOfSpeed = getRangeOfSpeed(currentGear)
        if (speed < rangeOfSpeed[0] || speed > rangeOfSpeed[1] || (currentGear == 0 && currentSpeed < speed)) {
            return false
        }

        if (speed == 0) {
            direction = Companion.STOP
        } else if (currentGear == -1 || (isBack && currentGear != 1)) {
            direction = Companion.BACK
        } else {
            direction = Companion.FORWARD
        }

        currentSpeed = speed
        return true
    }

    fun GetStatusEngine(): Boolean {
        return engine
    }

    fun GetCurrentGear(): Int {
        return currentGear
    }

    fun GetCurrentSpeed(): Int {
        return currentSpeed
    }

    fun GetCurrentDirection(): String {
        return direction
    }

    companion object {
        private const val STOP = "stop"
        private const val FORWARD = "forward"
        private const val BACK = "back"
    }
}