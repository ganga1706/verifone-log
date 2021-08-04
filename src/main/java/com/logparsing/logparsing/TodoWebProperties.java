package com.logparsing.logparsing;

import java.io.IOException;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

@SuppressWarnings("deprecation")

public final class TodoWebProperties extends PropertyPlaceholderConfigurer {//NOSONAR
	//this can't be addressed because of the version used in junit it will affect the test cases too 

  private static java.util.Properties propsExported = new java.util.Properties();

  @Override
  protected java.util.Properties mergeProperties() throws IOException {
    propsExported = super.mergeProperties();  // NOSONAR
    return propsExported;
  }

  public static void mergeProperties(java.util.Properties properties) {
    propsExported = properties;
  }

  public static String getProperty(final String key) {
    return propsExported.getProperty(key);
  }
}
