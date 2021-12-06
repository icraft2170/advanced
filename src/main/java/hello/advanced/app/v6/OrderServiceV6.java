package hello.advanced.app.v6;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV6 {
    private final OrderRepositoryV6 orderRepository;
    private final TraceTemplate template;

    public OrderServiceV6(OrderRepositoryV6 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(String itemId) {
        template.execute("orderService.orderItem()", () -> {
                orderRepository.save(itemId);
                return null;
        });
    }
}
