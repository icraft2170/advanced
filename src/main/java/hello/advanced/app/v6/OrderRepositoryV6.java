package hello.advanced.app.v6;

import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository

public class OrderRepositoryV6 {
    private final TraceTemplate template;

    public OrderRepositoryV6(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(String itemId){

        template.execute("orderRepository.save()",()->{
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            return null;
        });

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
