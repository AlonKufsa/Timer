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