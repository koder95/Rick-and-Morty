package pl.koder95.rickandmortyfx.dto;

import java.util.Scanner;

public record EpisodeLinkDto(Long id, String tag, String title) {
    @Override
    public String toString() {
        return "#" + id + " [" + tag + "]: " + title;
    }

    public static EpisodeLinkDto fromString(String line) {
        if (!line.startsWith("#")) {
            throw new IllegalArgumentException("Expected # as the first character");
        }
        Scanner sc = new Scanner(line.substring(1));
        Long id = sc.nextLong();
        String tag = sc.next().transform(string -> {
            int beginIndex = string.indexOf("[") + 1;
            int endIndex = string.lastIndexOf("]");
            return string.substring(beginIndex, endIndex);
        });
        String title = sc.next();
        return new EpisodeLinkDto(id, tag, title);
    }
}
