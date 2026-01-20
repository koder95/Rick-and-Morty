package pl.koder95.rickandmortyfx.dto;

import java.util.Scanner;

public record LocationLinkDto(Long id, String name, String type, String dimension) {
    @Override
    public String toString() {
        return "#" + id + " [" + type + "][" + dimension + "]: " + name;
    }

    public static LocationLinkDto fromString(String line) {
        if (!line.startsWith("#")) {
            throw new IllegalArgumentException("Expected # as the first character");
        }
        Scanner sc = new Scanner(line.substring(1));
        Long id = sc.nextLong();
        String[] tags = sc.next().transform(string -> {
            int beginIndex = string.indexOf("[") + 1;
            int endIndex = string.lastIndexOf("]");
            return string.substring(beginIndex, endIndex);
        }).split("]\\[");
        String name = sc.next();
        return new LocationLinkDto(id, name, tags[0], tags[1]);
    }
}
