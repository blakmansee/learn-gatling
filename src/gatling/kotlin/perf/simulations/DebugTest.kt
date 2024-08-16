package perf.simulations

import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.core.Simulation
import perf.Protocols
import perf.Scenarios

class DebugTest : Simulation() {
    init {
        setUp(
            Scenarios.scn.injectOpen(
                atOnceUsers(1)
            )
        ).protocols(Protocols.httpProtocol)
    }
}