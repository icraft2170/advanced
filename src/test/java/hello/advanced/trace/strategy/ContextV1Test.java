package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void templateMethodV0(){
        logic1();
        logic2();
    }

    private void logic1(){
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직 1 실행");
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        log.info("resultTime : {}", endTime - startTime);
    }
    private void logic2(){
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직 2 실행");
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        log.info("resultTime : {}", endTime - startTime);
    }

    @Test
    void strategyV1(){
        Strategy strategyLogic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        Strategy strategyLogic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }


    @Test
    void strategyV2(){
        ContextV1 context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1 실행");
            }
        });
        context1.execute();

        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 2 실행");
            }
        });
        context2.execute();
    }

    @Test
    void strategyV3(){
        ContextV1 context1 = new ContextV1(()-> log.info("비즈니스 로직 1 실행"));
        context1.execute();

        ContextV1 context2 = new ContextV1(()->log.info("비즈니스 로직 2 실행"));
        context2.execute();
    }

    @Test
    void strategyV4(){
        ContextV2 context = new ContextV2();
        context.execute(()-> log.info("비즈니스 로직 1 실행"));
        context.execute(()->log.info("비즈니스 로직 2 실행"));
    }

}
