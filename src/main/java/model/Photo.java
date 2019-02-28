package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.TreeSet;

@Data
@Builder
@AllArgsConstructor
public class Photo implements Comparable<Photo> {
    public static final int TYPE_INDEX = 0;
    public static final int TAGS_NUMBER_INDEX = 1;
    public static final int TAGS_INDEX = 2;

    int idex;
    int type; //1 = H, 2 = V
    int tagsNumber;
    TreeSet<String> tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Photo photo = (Photo) o;

        return idex == photo.idex;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idex;
        return result;
    }

    @Override
    public int compareTo(Photo o) {
        return this.getTags().hashCode();
    }
}
