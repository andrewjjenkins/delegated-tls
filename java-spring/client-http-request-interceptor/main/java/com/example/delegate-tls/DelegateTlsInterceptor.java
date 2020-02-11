package com.example.delegateTls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DelegateTlsInterceptor implements ClientHttpRequestInterceptor {
  private static Logger LOGGER = LoggerFactory
    .getLogger(DelegateTlsInterceptor.class);
       
  @Override
  public ClientHttpResponse intercept(
    HttpRequest request, byte[] body, 
    ClientHttpRequestExecution execution) throws IOException {
      return execution.execute(wrapDelegatedTls(request), body);
  }

  private HttpRequest wrapDelegatedTls(HttpRequest request) {
    URI u = request.getURI();
    if (u.getScheme().equals("https")) {
      try {
        // Override: use port 443 but scheme http.  This requires a sidecar
        // or other functionality to provide TLS.
        URI newU = new URI(
            "http",
            u.getUserInfo(),
            u.getHost(),
            443,
            u.getPath(),
            u.getQuery(),
            u.getFragment()
        );
        HttpRequestWrapper delegated = new HttpRequestWrapper(request) {
          @Override
          public URI getURI() { return newU; }
        };
        LOGGER.info("Delegating TLS: {}", delegated.getURI());
        return delegated;
      } catch (URISyntaxException ex) {
        LOGGER.error("Failed delegating TLS: {} {}", request.getURI(), ex);
      }
    }
    return request;
  }
}
