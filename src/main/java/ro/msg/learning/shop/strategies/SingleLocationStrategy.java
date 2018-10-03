package ro.msg.learning.shop.strategies;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import ro.msg.learning.shop.dtos.OrderDetailDto;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.StockNotFoundException;
import ro.msg.learning.shop.repositories.StockRepository;

@Slf4j
@AllArgsConstructor
public class SingleLocationStrategy implements LocationStrategy {

    private StockRepository stockRepository;


    //for next strategy should return a list of stocks not a single Stock!!!
    @Override
    public Stock getStockForProduct(OrderDetailDto orderDetailDto) {

        val product = new Product();
        product.setId(orderDetailDto.getProductId());

        final val stock = stockRepository.
            findByProductAndQuantityGreaterThanEqual(product, orderDetailDto.getQuantity());

        if (stock != null) {
            return stock;
        }

        log.error("No stocks found for product {}", product);
        throw new StockNotFoundException("No stocks found for product with the id " + product.getId(), null);
    }
}
