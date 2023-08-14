package com.zlc.springboot.model;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class Alipay {
   private String app_id;
   private String merchant_private_key;
   private String alipay_public_key;
   private String return_url;
   private String gatewayUrl;

    @Override
    public String toString() {
        return "Alipay{" +
                "app_id='" + app_id + '\'' +
                ", merchant_private_key='" + merchant_private_key + '\'' +
                ", alipay_public_key='" + alipay_public_key + '\'' +
                ", return_url='" + return_url + '\'' +
                ", gatewayUrl='" + gatewayUrl + '\'' +
                '}';
    }
}
