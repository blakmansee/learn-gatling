package perf

import io.gatling.javaapi.core.CoreDsl.*


object Scenarios {

    private val mainPageGroup = group("Main Page").on(
        exec(Actions.openMainPage)
            .exec(Actions.openMainPageGetWelcome)
            .exec(Actions.openMainPageGetNav)
    )

    private val loginGroup = group("Login Page").on(
        exec(Actions.login)
            .exec(Actions.loginGetNav)
            .exec(Actions.loginGetLogin)
    )

    private val flightsGroup = group("Flights Page").on(
        exec(Actions.openFlightsPage)
            .exec(Actions.openFlightsPageGetWelcome)
            .exec(Actions.openFlightsPageGetNav)
    )
    private val buyFlightGroup = group("Buy Flight").on(
        exec(Actions.buyFlightSelectRoute)
            .exec(Actions.buyFlightSelectDepartureTime)
            .exec(Actions.buyFlightSubmitTrip)
    )


    val scn = scenario("Otus homework scenario")
        .feed(Feeders.userFeeder)
        .feed(Feeders.cityFeeder)
        .exec(mainPageGroup)
        .exec(loginGroup)
        .exec(flightsGroup)
        .exec(buyFlightGroup)
        .exec(Actions.openMainPage)

}