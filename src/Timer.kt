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