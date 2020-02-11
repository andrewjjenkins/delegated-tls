package com.example.delegateTls;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

public class DelegateTlsCustomizer implements RestTemplateCustomizer {
  @Override
  public void customize(RestTemplate restTemplate) {
    restTemplate.getInterceptors().add(new DelegateTlsInterceptor());
  }
}
