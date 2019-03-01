package io;

import model.Photo;
import model.PhotoPair;
import model.Slide;

import java.util.*;
import java.util.stream.Collectors;

public class Parser {

    public static List<Photo> getMainEntity(LinkedList<String> mainEntityPlan) {
        String photos = mainEntityPlan.poll();
        int i = 0;
        List<Photo> photoss = new LinkedList<>();

        for (String row : mainEntityPlan) {
            String [] fields = row.split(" ");
            HashSet<String> tags = Arrays.stream(fields).skip(2).collect(Collectors.toCollection(()-> new HashSet<>()));
            Photo photo = new Photo(i++, fields[0].equals("H") ? 1 : 2, Integer.parseInt(fields[1]), tags);
            photoss.add(photo);
        }
        return photoss;
    }


    public static List<String> exportResult(List<Slide> maxState) {
        LinkedList<String> result = maxState.stream()
            .map(slide ->
                "" + slide.getPhotos().stream().findFirst().get().getIdex())
            .collect(Collectors.toCollection(() -> new LinkedList<>()));
        result.push(String.valueOf(maxState.size()));
        return result;
    }
}
