package com.calaton.travelagency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TravelAgencyApplication {

    //TODO:
    // final edits:
    // - add validation
    // - add Spring Security (basic auth)
    // .
    // ideas for different branches:
    // - use many-to-many relationship (delete Order, use key/value for tour/discount)

    public static void main(String[] args) {
        SpringApplication.run(TravelAgencyApplication.class, args);
    }

}
