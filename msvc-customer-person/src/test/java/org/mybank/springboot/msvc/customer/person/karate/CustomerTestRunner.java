package org.mybank.springboot.msvc.customer.person.karate;

import com.intuit.karate.junit5.Karate;

class CustomerTestRunner {

    @Karate.Test
    Karate testCustomers() {
        return Karate.run("classpath:karate/customer.feature");
    }
}