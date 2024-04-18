class Timer {
	private var startingTime: Double = 0.0
	private var timeUntilLastStop = 0.0
	private var resumeTime = 0.0
	private var isRunning = false
	private var hasStopped = false
	private var hasStarted = false
	val currentTimeSeconds: Double
		get() = calcTime()

	private fun getCurrentTime(): Double {
		return System.currentTimeMillis().toDouble() / 1000
	}

	fun start() {
		if (startingTime == 0.0) startingTime = getCurrentTime()
		else if (!isRunning) resumeTime = getCurrentTime()
		isRunning = true
		hasStarted = true
	}

	private fun calcTime(): Double {
		if (!hasStopped) {
			return if (startingTime == 0.0) 0.0
			else getCurrentTime() - startingTime
		}
		return if (isRunning) (getCurrentTime() - resumeTime) + timeUntilLastStop
		else timeUntilLastStop
	}

	fun stop() {
		if (!isRunning) return
		timeUntilLastStop = calcTime()
		hasStopped = true
		isRunning = false
		resumeTime = 0.0
	}

	fun reset() {
		isRunning = false
		hasStopped = false
		timeUntilLastStop = 0.0
		resumeTime = 0.0
		hasStarted = false
		startingTime = 0.0
	}

	fun restart() {
		reset()
		start()
	}

	fun hasPassed(time: Double): Boolean {
		return if ((calcTime() - time) > 0) true
		else return false
	}
}


fun main() {
	val timer = Timer()
	Thread.sleep(2000)
	timer.start()
	Thread.sleep(4000)
	println(timer.hasPassed(2.0)) // Should print true
	println(timer.currentTimeSeconds) // Should print 4
	timer.stop()
	Thread.sleep(2000)
	println(timer.currentTimeSeconds) // Should print 4
	timer.start()
	Thread.sleep(3000)
	println(timer.currentTimeSeconds) // Should print 7
	timer.restart()
	println(timer.hasPassed(1.0)) // Should print false
	Thread.sleep(2000)
	println(timer.currentTimeSeconds) // Should print 2
	timer.reset()
}