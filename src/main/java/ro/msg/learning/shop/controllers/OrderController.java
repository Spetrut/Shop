package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.orders.OrderDtoIn;
import ro.msg.learning.shop.dtos.orders.OrderDtoOut;
import ro.msg.learning.shop.entities.Customer;
import ro.msg.learning.shop.mappers.OrderMapper;
import ro.msg.learning.shop.services.CustomerService;
import ro.msg.learning.shop.services.OrderService;

import java.security.Principal;

@RequestMapping("/order")
@RestController
@RequiredArgsConstructor

/**
 * responsible for controlling the application logic, regarding the Order entity
 */
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;


    /**
     * creates a new Order
     *
     * @return the new ORDER back to the view
     */
    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDtoOut create(@RequestBody OrderDtoIn orderDtoIn, Principal principal) {

        Customer customer = customerService.getProfile(principal.getName());
        return OrderMapper.toOutBound(orderService.createOrder(orderDtoIn, customer));

    }

}
