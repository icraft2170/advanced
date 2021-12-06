package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate<T> {
    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public T excute(String message) {
        TraceStatus status = null;

        try {
            status = trace.begin(message);
            T result = call();
            trace.end(status);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    protected abstract T call();
}
