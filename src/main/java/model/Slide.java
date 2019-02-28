package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Data
@Builder
@AllArgsConstructor
public class Slide {
    public static final int TYPE_INDEX = 0;
    public static final int TAGS_NUMBER_INDEX = 1;
    public static final int TAGS_INDEX = 2;

    Set<Photo> photos;
    int rate;
    Set<String> tags;

    int rate(Photo photo1, Photo photo2) {
        Set<String> tags1 = new TreeSet<>((photo1.getTags()));
        Set<String> tags2 = new TreeSet<>((photo2.getTags()));

        int startSize = tags1.size();
        tags1.removeAll(photo2.getTags());
        int diff1 = tags1.size();
        int shareSize = startSize - diff1;
        tags2.removeAll(photo1.getTags());
        int diff2 = tags2.size();

        int min =  Math.min(diff1, diff2);
        return Math.min(min, shareSize);

    }
}
