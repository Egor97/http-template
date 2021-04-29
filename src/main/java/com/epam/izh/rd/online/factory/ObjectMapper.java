package com.epam.izh.rd.online.factory;

public class ObjectMapper implements ObjectMapperFactory {
    @Override
    public com.fasterxml.jackson.databind.ObjectMapper getObjectMapper() {
        return  new com.fasterxml.jackson.databind.ObjectMapper();
    }
}
