package postman.orchestrator.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QueryParamTest {
  @Test
  void testSerializationConstructor() {
    QueryParam param = new QueryParam();

    assertNull(param.getKey());
    assertNull(param.getValue());
  }

  @Test
  void testFullyParametrizedConstructor() {
    String key = "Test key";
    String value = "Test value";
    QueryParam param = new QueryParam(key, value);

    assertEquals(key, param.getKey());
    assertEquals(value, param.getValue());
  }

  @Test
  void testOverrideValues() {
    String key = "Test key";
    String value = "Test value";
    QueryParam param = new QueryParam(key, value);

    assertEquals(key, param.getKey());
    assertEquals(value, param.getValue());

    key = "Updated test key";
    value = "Updated test value";
    param.setKey(key);
    param.setValue(value);

    assertEquals(key, param.getKey());
    assertEquals(value, param.getValue());
  }
}