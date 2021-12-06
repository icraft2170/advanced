
package hello.advanced.app.v5;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final LogTrace trace;


    @GetMapping("/v5/request")
    public String request(String itemId){

        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };

        return template.excute("OrderController.request()");
   }
}
