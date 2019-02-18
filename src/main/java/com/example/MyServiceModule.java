package com.example;

import com.google.inject.AbstractModule;

public class MyServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MyService.class);
    }
}
