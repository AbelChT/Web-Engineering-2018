package soa.eip;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Router extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:timeline")
                .to("twitter://timeline/user?consumerKey={{twitter.consumerKey}}&"
                        + "consumerSecret={{twitter.consumerSecret}}&"
                        + "accessToken={{twitter.accessToken}}&"
                        + "accessTokenSecret={{twitter.accessTokenSecret}}");


                from("twitter://timeline/home?consumerKey={{twitter.consumerKey}}&"
                        + "consumerSecret={{twitter.consumerSecret}}&"
                        + "accessToken={{twitter.accessToken}}&"
                        + "accessTokenSecret={{twitter.accessTokenSecret}}").to("direct:timeline-consult");
    }
}
