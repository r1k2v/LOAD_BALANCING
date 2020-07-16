package com.controllers;

import com.bean.ConversionValue;
import com.proxy.ConversionValueProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ConversionController {

    @GetMapping("currency-conversion/to/{to}/from/{from}/quantity/{quantity}")
    public ConversionValue getValue(@PathVariable String to, @PathVariable String from, @PathVariable String quantity){
        ConversionValue value=new ConversionValue(1l,from,to,BigDecimal.valueOf(65));
        value.setQuantity(BigDecimal.valueOf(Double.parseDouble(quantity)));
        return value;
    }
    @GetMapping("currency-conversion-template/to/{to}/from/{from}/quantity/{quantity}")
    public ConversionValue getValue_template(@PathVariable String to, @PathVariable String from, @PathVariable String quantity){
        Map<String,String> uriVariable=new HashMap<>();
        uriVariable.put("to",to);
        uriVariable.put("from",from);
        RestTemplate restTemplate=new  RestTemplate();
        ResponseEntity<ConversionValue> responseEntity =restTemplate.getForEntity("http://localhost:8000/currency-exchange-DB/to/{to}/from/{from}",
                                                        ConversionValue.class,uriVariable);
        ConversionValue value=responseEntity.getBody();
        value.setQuantity(BigDecimal.valueOf(Double.parseDouble(quantity)));
        value.setTotalCalculatedAmount(value.getConversionMultiple().multiply(value.getQuantity()));
        return value;
    }
    @Autowired
    ConversionValueProxy proxy;
    @GetMapping("currency-conversion-proxy/to/{to}/from/{from}/quantity/{quantity}")
    public ConversionValue getValue_proxy(@PathVariable String to, @PathVariable String from, @PathVariable String quantity){
        ConversionValue value=proxy.getExchnageValue_DataBase(from,to);

        value.setQuantity(BigDecimal.valueOf(Double.parseDouble(quantity)));
        value.setTotalCalculatedAmount(value.getConversionMultiple().multiply(value.getQuantity()));
        return value;
    }
}
