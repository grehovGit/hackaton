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

    int index;

    Set<Photo> photos;
    int rate = 0;
    Set<String> tags;
    Slide v1, v2;

    int rate(Photo photo1, Photo photo2) {
        Set<String> tags1 = new HashSet<>(photo1.getTags());
        Set<String> tags2 = new HashSet<>(photo2.getTags());

        int startSize = tags1.size();
        tags1.removeAll(photo2.getTags());
        int diff1 = tags1.size();
        int shareSize = startSize - diff1;
        tags2.removeAll(photo1.getTags());
        int diff2 = tags2.size();

        int min =  Math.min(diff1, diff2);
        return Math.min(min, shareSize);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Slide slide = (Slide) o;

        return index == slide.index;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + index;
        return result;
    }

    void setMaxRate(int rate) {
        if (rate > this.getRate())
            this.setRate(rate);
    }

    @Override
    public String toString() {
        return "Slide{" +
            "index=" + index +
            ", photos=" + photos +
            ", rate=" + rate +
            ", tags=" + tags +
            '}';
    }
}
