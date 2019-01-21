package postman.orchestrator.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PostmanUrlTest {
  @Test
  void testSerializationConstructor() {
    PostmanUrl url = new PostmanUrl();

    assertNull(url.getRaw());
    assertNull(url.getProtocol());

    assertNotNull(url.getHost());
    assertTrue(url.getHost().isEmpty());

    assertNotNull(url.getPath());
    assertTrue(url.getPath().isEmpty());

    assertNotNull(url.getQueryParameters());
    assertTrue(url.getQueryParameters().isEmpty());
  }

  @Test
  void testFullyParametrizedConstructor() {
    String raw = "Raw url";
    String protocol = "http";
    PostmanUrl url = new PostmanUrl(raw, protocol, null, null, null);

    assertEquals(raw, url.getRaw());
    assertEquals(protocol, url.getProtocol());

    assertNull(url.getHost());
    assertNull(url.getPath());
    assertNull(url.getQueryParameters());
  }

  @Test
  void testOverrideValues() {
    String raw = "Raw url";
    String protocol = "http";
    PostmanUrl url = new PostmanUrl(raw, protocol, null, null, null);

    assertEquals(raw, url.getRaw());
    assertEquals(protocol, url.getProtocol());

    assertNull(url.getHost());
    assertNull(url.getPath());
    assertNull(url.getQueryParameters());

    String raw2 = "https://www.google.com/search?q=test";
    String protocol2 = "https";

    url.setRaw(raw2);
    url.setProtocol(protocol2);
    url.setHost(Arrays.asList("www.google.com"));
    url.setPath(Arrays.asList("search"));
    url.setQueryParameters(Arrays.asList(new QueryParam("q", "test")));

    assertEquals(raw2, url.getRaw());
    assertEquals(protocol2, url.getProtocol());

    assertNotNull(url.getHost());
    assertFalse(url.getHost().isEmpty());
    assertEquals("www.google.com", url.getHost().get(0));

    assertNotNull(url.getPath());
    assertFalse(url.getPath().isEmpty());
    assertEquals("search", url.getPath().get(0));

    assertNotNull(url.getQueryParameters());
    assertFalse(url.getQueryParameters().isEmpty());
    assertEquals("test", url.getQueryParameters().get(0).getValue());
  }
}