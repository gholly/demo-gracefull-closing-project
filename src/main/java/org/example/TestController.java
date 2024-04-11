package org.example;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
@RequiredArgsConstructor
public class TestController implements ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;

    }


    @GetMapping("/shut-down")
    public void shutDown() {
        ((ConfigurableApplicationContext) context).close();
    }

    @GetMapping("/say-hi")
    public String sayHi() {
        return "Hi";
    }

    @SneakyThrows
    @GetMapping("/say-hi-later")
    public String sayHiLater() {
        Thread.sleep(150000);
        return "Hi";
    }

    @GetMapping("/register")
    public void registToNacos() {

    }


}
