module rickandmortyfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires com.fasterxml.jackson.annotation;
    requires java.base;
    requires java.net.http;
    requires jdk.crypto.ec;
    requires tools.jackson.core;
    requires tools.jackson.databind;
    requires spring.beans;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    requires spring.core;
    exports pl.koder95.rickandmortyfx;
    exports pl.koder95.rickandmortyfx.dto;
    exports pl.koder95.rickandmortyfx.api to spring.beans;
    exports pl.koder95.rickandmortyfx.api.data to spring.beans;
    exports pl.koder95.rickandmortyfx.controller to spring.beans;
    exports pl.koder95.rickandmortyfx.mapper.impl to spring.beans;
    exports pl.koder95.rickandmortyfx.service.impl to spring.beans;
    opens pl.koder95.rickandmortyfx to spring.core;
    opens pl.koder95.rickandmortyfx.api.data to tools.jackson.databind;
    opens pl.koder95.rickandmortyfx.controller to javafx.fxml;
}
