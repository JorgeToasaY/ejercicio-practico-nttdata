package org.mybank.springboot.msvc.account.movement.listener;

import org.mybank.springboot.msvc.account.movement.dto.CustomerResponseDTO;
import org.mybank.springboot.msvc.account.movement.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerEventListener {

private final AccountService accountService;

    @RabbitListener(queues = "customerTopicQueue")
    public void handleCustomerUpdate(CustomerResponseDTO customer) {
        //System.out.println("Recibido: " + customer);
        accountService.updateAccountByCustomerId(customer.getCustomerId(), customer.getState());
    }
}

