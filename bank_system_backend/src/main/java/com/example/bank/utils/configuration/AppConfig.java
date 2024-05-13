package com.example.bank.utils.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${app.XML_FILES_LOCATION}")
    private String xmlFilesLocation;

    @Value("${app.CLEAR_XML_FILES_LOCATION}")
    private boolean clearXmlFilesLocation;

    public String getXmlFilesLocation() {
        return xmlFilesLocation;
    }

    public boolean getClearXmlFilesLocation() {
        return clearXmlFilesLocation;
    }
}
