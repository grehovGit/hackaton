package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@Builder
@AllArgsConstructor
public class PhotoPair implements Comparable<PhotoPair>{
    public static Map<String, Integer> combinations = new HashMap<>();
    int id1, id2;
    int rate;

    public static int rate(Slide photo1, Slide photo2) {
        Set<String> tags1 = new HashSet<>((photo1.getTags()));
        Set<String> tags2 = new HashSet<>((photo2.getTags()));
        return calcRate(tags1, tags2);
    }

    private static int calcRate (Set tags1, Set tags2) {
        int startSize = tags1.size();
        tags1.removeAll(tags2);
        int diff1 = tags1.size();
        int shareSize = startSize - diff1;
        tags2.removeAll(tags1);
        int diff2 = tags2.size();

        int min =  Math.min(diff1, diff2);
        return Math.min(min, shareSize);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PhotoPair pair = (PhotoPair) o;

        return id1 == pair.id1;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id1;
        return result;
    }

    @Override
    public int compareTo(PhotoPair o) {
        return id1;
    }
}
