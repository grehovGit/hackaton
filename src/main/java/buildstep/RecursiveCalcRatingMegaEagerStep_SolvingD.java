package buildstep;

import builder.MemoryWarningSystem;
import builder.SlideBuilder;
import model.Photo;
import model.PhotoPair;
import model.Slide;

import java.util.*;
import java.util.stream.Collectors;

public class RecursiveCalcRatingMegaEagerStep_SolvingD {
    public static Map<String, Set<String>> combinations = new HashMap<>();
    public static Map<String, Set<Photo>> photosCombinations = new HashMap<>();
    static {
        MemoryWarningSystem.setPercentageUsageThreshold(0.9); ;
    }

    MemoryWarningSystem mws = new MemoryWarningSystem();
    SlideBuilder slideBuilder;
    private Map<String, Integer> calculatedStates;
    private List<Photo> photos = new ArrayList<>();
    private int startSliceNumber = 3;

    public RecursiveCalcRatingMegaEagerStep_SolvingD(SlideBuilder pizzaBuilder) {
        this.calculatedStates = SlideBuilder.calculatedStates;
        this.slideBuilder = pizzaBuilder;
        this.photos = pizzaBuilder.getPhotos();

        mws.addListener(new MemoryWarningSystem.Listener() {
            public void memoryUsageLow(long usedMemory, long maxMemory) {
                System.out.println("Memory usage!!!");
                MemoryWarningSystem.timeToStop = true;
                combinations.clear();
                photosCombinations.clear();
            }
        });
    }

    public List<Slide> makeStep() {
        return rateVertical(photos);
    }

    private List<Slide> rateVertical(List<Photo> photos) {
        Set<Slide> slides =  transformToSlide(photos);
        List<Slide> result = new LinkedList<>();
        int monitor = 0;
        List<Slide> roots = slides.stream().limit(2).collect(Collectors.toList());
        Slide root = roots.get(0);
        root.getPhotos().addAll(roots.get(1).getPhotos());
        root.getTags().addAll(roots.get(1).getTags());

        slides.remove(roots.get(0));
        slides.remove(roots.get(1));

        result.add(root);

        while(slides.size() > 0) {
            monitor++;
            Slide tail = root;
            slides.remove(tail);
            Slide bestTail = nextTail(tail, slides);
            result.add(bestTail);
            root = bestTail;

            if (monitor % 1000 == 0){
                System.out.println("rateVertical:" + monitor + " " + System.currentTimeMillis());
//                break;
            }
        }
        return result;
    };

    private Set<Slide> transformToSlide(List<Photo> photos) {
        return photos
            .stream()
            .filter(photo -> photo.getType() == 2)
            .map(photo -> {
                Set<Photo> phots = new HashSet<>();
                phots.add(photo);
                Set<String> tags = new HashSet<>();
                tags.addAll(photo.getTags());
                return new Slide(photo.getIdex(), phots, 0, tags, null, null);})
            .collect(Collectors.toCollection(() -> new HashSet<>()));
    }


    private Slide nextTail(Slide tail, Set<Slide> slides) {
        Slide bestNexTail = slides.stream()
            .limit(100)
            .flatMap(slide1 -> slides.stream()
                .limit(100)
                .filter(slide2 -> slide2.getIndex() != slide1.getIndex())
                .map(slide2 -> {
                Slide mergedSlide = new Slide(0, getMergedPhotos(slide1, slide2), 0, getMergedTags(slide1, slide2), slide1, slide2);
                mergedSlide.getPhotos().addAll(slide2.getPhotos());
                int currentRate = PhotoPair.rate(tail, mergedSlide);
                mergedSlide.setRate(currentRate);
                return mergedSlide;
            }))
            .max(Comparator.comparingInt(Slide::getRate)).get();
        slides.remove(bestNexTail.getV1());
        slides.remove(bestNexTail.getV2());
        System.out.println("nextTail - best : " + " : " + System.currentTimeMillis() + " : " + " : comb:" + combinations.size() + " : " + bestNexTail.getRate() + " : " + bestNexTail);
        return bestNexTail;
    }

    private Set<String> getMergedTags(Slide s1, Slide s2) {
        String key = "";
        key = s1.getIndex() < s2.getIndex()
            ? key + s1.getIndex() + s2.getIndex()
            : key + s2.getIndex() + s1.getIndex();

        Set<String> mergedTags = combinations.get(key);

        if (mergedTags != null)
            return mergedTags;

        mergedTags = new HashSet<>();
        mergedTags.addAll(s1.getTags());
        mergedTags.addAll(s2.getTags());
        combinations.put(key, mergedTags);
        return mergedTags;
    }

    private Set<Photo> getMergedPhotos(Slide s1, Slide s2) {
        String key = "";
        key = s1.getIndex() < s2.getIndex()
            ? key + s1.getIndex() + s2.getIndex()
            : key + s2.getIndex() + s1.getIndex();

        Set<Photo> mergedPhotos = photosCombinations.get(key);

        if (mergedPhotos != null)
            return mergedPhotos;

        mergedPhotos = new HashSet<>();
        mergedPhotos.addAll(s1.getPhotos());
        mergedPhotos.addAll(s2.getPhotos());
        photosCombinations.put(key, mergedPhotos);
        return mergedPhotos;
    }

}


