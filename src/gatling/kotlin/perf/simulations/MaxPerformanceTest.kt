package perf.simulations

import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.core.Simulation
import perf.Protocols
import perf.Scenarios
import java.time.Duration

class MaxPerformanceTest : Simulation() {

    private val intensity = 50.0
    private val totalRequests = 13
    private val stagesNumber = 10
    private val upToIntensity = (intensity / totalRequests / stagesNumber)
    private val stageDuration: Duration = Duration.ofMinutes(3)
    private val rampDuration: Duration = Duration.ofMinutes(2)

    init {
        setUp(
            Scenarios.scn.injectOpen(
                incrementUsersPerSec(upToIntensity)
                    .times(stagesNumber)
                    .eachLevelLasting(stageDuration)
                    .separatedByRampsLasting(rampDuration)
                    .startingFrom(0.0)
        )
        ).protocols(Protocols.httpProtocol)
    }
}