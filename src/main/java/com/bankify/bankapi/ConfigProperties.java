package com.bankify.bankapi;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("database")
public record ConfigProperties(String dbUri, String dbName, String secretToken,String jwtSecret) { }