package com.proxy;

import com.bean.ConversionValue;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="currency-exchange-service")
@RibbonClient(name="currency-exchange-service")
public interface ConversionValueProxy {
    @GetMapping("/currency-exchange-DB/to/{to}/from/{from}")
    public ConversionValue getExchnageValue_DataBase(@PathVariable("from") String from, @PathVariable("to") String to) ;

    }
