package io;

import model.Photo;

import java.util.*;
import java.util.stream.Collectors;

public class Parser {

    public static List<Photo> getMainEntity(LinkedList<String> mainEntityPlan) {
        String photos = mainEntityPlan.poll();
        int i = 0;
        List<Photo> photoss = new LinkedList<>();

        for (String row : mainEntityPlan) {
            String [] fields = row.split(" ");
            TreeSet<String> tags = Arrays.stream(fields).skip(2).collect(Collectors.toCollection(()-> new TreeSet<>()));
            Photo photo = new Photo(i++, fields[0] == "H" ? 1 : 2, Integer.parseInt(fields[1]), tags);
            photoss.add(photo);
        }
        return photoss;
    }


/*    public static List<String> exportResult(Set<Photo> maxState) {
        LinkedList<String> result = maxState.stream()
            .map(slice ->
                "" + slice.getYTopLeft()
                    + " "
                    + slice.getXTopLeft()
                    + " "
                    + (slice.getYTopLeft() + slice.getHeight() - 1)
                    + " "
                    + (slice.getXTopLeft() + slice.getWidth() - 1))
            .collect(Collectors.toCollection(() -> new LinkedList<>()));
        result.push(String.valueOf(maxState.size()));
        return result;
    }*/
}
