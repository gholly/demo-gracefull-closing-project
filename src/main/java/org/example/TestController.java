package org.example;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @NacosInjected
    private NamingService namingService;

    /**
     * 根据服务名称获取服务的所有（机器）实例信息
     * @param serviceName 服务名称
     * @return
     * @throws NacosException
     */
    @GetMapping("getInstances")
    public List<Instance> getInstances(String serviceName) throws  NacosException {
        return namingService.getAllInstances(serviceName);
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
