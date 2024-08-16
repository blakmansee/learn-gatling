package perf

import io.gatling.javaapi.core.CoreDsl.csv

object Feeders {
    val userFeeder = csv("users.csv").circular()
    val cityFeeder = csv("cities.csv").random()
}