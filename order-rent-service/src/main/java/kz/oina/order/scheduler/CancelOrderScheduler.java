package kz.oina.order.scheduler;

import kz.oina.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class CancelOrderScheduler {

    private final OrderService orderService;

    @Scheduled(cron = "${schedule.cancel-unpaid-orders-cron}")
    public void cancelUnpaidOrders() {
        log.info("Cancelling unpaid orders");
        orderService.cancelUnpaidOrders();
    }
}
