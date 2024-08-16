package perf

import io.gatling.javaapi.core.CoreDsl.regex
import io.gatling.javaapi.core.CoreDsl.substring
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status


object Actions {

    val openMainPage = http("Open root page")
        .get("/webtours")
        .check(
            status().not(500)
        )

    val openMainPageGetWelcome = http("Get welcome.pl")
        .get("/cgi-bin/welcome.pl?signOff=true")

    val openMainPageGetNav = http("Get nav.pl")
        .get("/cgi-bin/nav.pl")
        .queryParam("in", "home")
        .check(
            regex("name=\"userSession\" value=\"(.*)\"").saveAs("userSession")
        )

    val login = http("Login")
        .post("/cgi-bin/login.pl")
        .formParamMap(mapOf(
            "userSession" to "#{userSession}",
            "username" to "#{userName}",
            "password" to "#{password}",
        ))

    val loginGetNav = http("Get nav.pl")
        .get("/cgi-bin/nav.pl")
        .queryParam("in", "home")
        .queryParam("page", "menu")

    val loginGetLogin = http("Get login.pl")
        .get("/cgi-bin/login.pl")
        .queryParam("intro", "true")
        .check(
            substring("Welcome, <b>#{userName}</b>, to the Web Tours reservation pages.")
        )

    val openFlightsPage = http("Open flights page")
        .get("/cgi-bin/reservations.pl")
        .queryParam("page", "welcome")

    val openFlightsPageGetWelcome = http("Get welcome.pl")
        .get("/cgi-bin/welcome.pl")
        .queryParam("page", "search")

    val openFlightsPageGetNav = http("Get nav.pl")
        .get("/cgi-bin/nav.pl")
        .queryParam("page", "menu")
        .queryParam("in", "flights")

    val buyFlightSelectRoute = http("Select route")
        .post("/cgi-bin/reservations.pl")
        .formParamMap(mapOf(
            "depart" to "#{city}",
            "arrive" to "#{city}",
            "departDate" to "08/15/2024",
            "returnDate" to "08/16/2024",
            "numPassengers" to "1",
            "seatPref" to "None",
            "seatType" to "Coach",
            ".cgifields" to "roundtrip",
            ".cgifields" to "seatType",
            ".cgifields" to "seatPref",
            "findFlights.x" to "55",
            "findFlights.y" to "5",
        ))
        .check(
            regex("name=\"outboundFlight\" value=\"(.*)\"").findRandom().saveAs("randomFlightOut"),
            substring("Flight departing from")
        )

    val buyFlightSelectDepartureTime = http("Select departure time")
        .post("/cgi-bin/reservations.pl")
        .formParamMap(mapOf(
            "outboundFlight" to "#{randomFlightOut}",
            "numPassengers" to "1",
            "seatPref" to "None",
            "seatType" to "Coach",
            "reserveFlights.x" to "55",
            "reserveFlights.y" to "5",
            "advanceDiscount" to "0"
        ))
        .check(
            substring("Payment Details")
        )

    val buyFlightSubmitTrip = http("Submit trip")
        .post("/cgi-bin/reservations.pl")
        .formParamMap(mapOf(
            "outboundFlight" to "#{randomFlightOut}",
            "firstName" to "TEST",
            "lastName" to "TEST",
            "address1" to "TEST",
            "address2" to "TEST",
            "pass1" to "TEST",
            "creditCard" to "5431240123145213",
            "expDate" to "2512",
            "oldCCOption" to "TEST",
            "numPassengers" to "1",
            "seatType" to "Coach",
            "seatPref" to "None",
            "advanceDiscount" to "0",
            "returnFlight" to "Coach",
            "JSFormSubmit" to "off",
            ".chifields" to "saveCC",
            "buyFlights.x" to "35",
            "buyFlights.y" to "10",
        ))
        .check(
            substring("Thank you for booking through Web Tours.")
        )
}