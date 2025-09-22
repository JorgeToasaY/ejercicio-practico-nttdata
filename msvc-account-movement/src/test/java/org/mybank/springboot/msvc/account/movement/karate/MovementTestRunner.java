package org.mybank.springboot.msvc.account.movement.karate;

import com.intuit.karate.junit5.Karate;

class MovementTestRunner {

    @Karate.Test
    Karate testMovements() {
        return Karate.run("classpath:karate/movement.feature");
    }
}