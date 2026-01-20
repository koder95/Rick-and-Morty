package pl.koder95.rickandmortyfx.api;

import java.util.List;

public record Page<T>(Info info, List<T> results) {
    public record Info(Integer count, Integer pages, String next, String prev) {}
}
