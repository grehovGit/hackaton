package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class Slide {
    public static final int TYPE_INDEX = 0;
    public static final int TAGS_NUMBER_INDEX = 1;
    public static final int TAGS_INDEX = 2;

    Set<Photo> photos;
    Set<String> tags;
}
