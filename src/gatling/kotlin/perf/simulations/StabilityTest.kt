package perf.simulations

import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.core.Simulation
import perf.Protocols
import perf.Scenarios
import java.time.Duration

class StabilityTest : Simulation() {
    private val intensity = 50.0
    private val totalRequests = 13
    private val upToIntensity = (intensity / totalRequests)

    init {
        setUp(
            Scenarios.scn.injectOpen(
                rampUsersPerSec(0.0).to(upToIntensity).during(Duration.ofMinutes(10)),
                constantUsersPerSec(upToIntensity).during(Duration.ofMinutes(60))
            )
        ).protocols(Protocols.httpProtocol)
    }
}