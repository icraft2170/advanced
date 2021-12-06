package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    @DisplayName("템플릿 메소드패턴 적용 이전")
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

    /**
     * 템플릿 메소드 패턴 적용
     */
    @Test
    @DisplayName("템플릿 메소드패턴 적용")
    void templateMethodV1(){
        AbstractTemplate template1 = new SubClassLogic1();
        AbstractTemplate template2 = new SubClassLogic2();

        template1.execute();
        template2.execute();
    }

    /**
     * 익명내부 클래스 사용하
     */
    @Test
    @DisplayName("템플릿 메소드패턴의 콜백을 익명내부클래스로 작성하기")
    void templateMethodV2(){
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직 1 실행");
            }
        };
        log.info("template1 FQCN = {}", template1.getClass());

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직 2 실행");
            }
        };
        log.info("template2 FQCN = {}", template2.getClass());

        template1.execute();
        template2.execute();
    }


}
