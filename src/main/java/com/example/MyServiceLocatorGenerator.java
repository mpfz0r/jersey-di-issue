package com.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.external.generator.ServiceLocatorGeneratorImpl;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;


public class MyServiceLocatorGenerator extends ServiceLocatorGeneratorImpl {
    private static Injector injector;

    @Override
    public ServiceLocator create(String name, ServiceLocator parent) {
        final ServiceLocator serviceLocator = super.create(name, parent);

        if (injector == null) {
            injector = Guice.createInjector(new MyServiceModule());
        }
        initGuiceIntoHK2Bridge(serviceLocator, injector);

        return serviceLocator;
    }

    private void initGuiceIntoHK2Bridge(ServiceLocator serviceLocator, Injector injector) {
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(injector);
    }
}
