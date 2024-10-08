package perf

import io.gatling.javaapi.http.HttpDsl.http

object Protocols {

    val httpProtocol = http.baseUrl("http://webtours.load-test.ru:1080/")
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("en-US,en;q=0.5")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")
        .disableFollowRedirect()
}