package ro.msg.learning.shop.mappers;

import lombok.experimental.UtilityClass;
import ro.msg.learning.shop.dtos.orders.OrderDtoIn;
import ro.msg.learning.shop.dtos.orders.OrderDtoOut;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.stream.Collectors;

@UtilityClass
public class OrderMapper {

    public OrderDtoOut toOutBound(Order order) {
        OrderDtoOut orderDto = new OrderDtoOut();
        orderDto.setAddress(order.getAddress());
        orderDto.setCustomerId(order.getCustomer().getId());
        orderDto.setOrderDetails(order.getOrderDetails().parallelStream().map(OrderDetailMapper::toOutBound).collect(Collectors.toList()));
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setLocationNames(order.getLocations().parallelStream().map(Location::getAddress).collect(Collectors.toList()));
        return orderDto;
    }

    public Order toInBound(OrderDtoIn orderDto, ProductRepository productRepository) {
        Order order = new Order();

        order.setOrderDetails(orderDto.getOrderDetails().parallelStream().
            map(orderDetailDto -> OrderDetailMapper.toInBound(orderDetailDto, productRepository)).collect(Collectors.toList()));
        order.setAddress(orderDto.getAddress());
        order.setOrderDate(orderDto.getOrderDate());
        return order;
    }

}
