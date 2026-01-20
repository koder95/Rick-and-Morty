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
    exports pl.koder95.rickandmortyfx;
    opens pl.koder95.rickandmortyfx.api.data to tools.jackson.databind;
    opens pl.koder95.rickandmortyfx.controller to javafx.fxml;
}
