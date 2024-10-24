package org.stellar;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.kafka.common.security.auth.SslEngineFactory;

public class TrustAllSslEngineFactory implements SslEngineFactory {

  private final TrustManager TRUST_ALL_MANAGER =
      new X509TrustManager() {

        public X509Certificate[] getAcceptedIssuers() {
          return null;
        }

        public void checkClientTrusted(X509Certificate[] certs, String authType) {
          // empty
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType) {
          // empty
        }
      };

  @Override
  public SSLEngine createClientSslEngine(
      String peerHost, int peerPort, String endpointIdentification) {
    TrustManager[] trustManagers = new TrustManager[] {TRUST_ALL_MANAGER};
    try {
      SSLContext sslContext = SSLContext.getInstance("SSL");
      sslContext.init(null, trustManagers, new SecureRandom());
      SSLEngine sslEngine = sslContext.createSSLEngine(peerHost, peerPort);
      sslEngine.setUseClientMode(true);
      return sslEngine;
    } catch (NoSuchAlgorithmException | KeyManagementException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public SSLEngine createServerSslEngine(String peerHost, int peerPort) {
    return null;
  }

  @Override
  public boolean shouldBeRebuilt(Map<String, Object> nextConfigs) {
    return false;
  }

  @Override
  public Set<String> reconfigurableConfigs() {
    return null;
  }

  @Override
  public KeyStore keystore() {
    return null;
  }

  @Override
  public KeyStore truststore() {
    return null;
  }

  @Override
  public void close() {}

  @Override
  public void configure(Map<String, ?> configs) {}
}
