package buildstep;

import builder.MemoryWarningSystem;
import builder.SlideBuilder;
import model.Photo;
import model.PhotoPair;
import model.Slide;

import java.util.*;
import java.util.stream.Collectors;

public class RecursiveCalcRatingMegaEagerStep_SolvingD {
    static {
        MemoryWarningSystem.setPercentageUsageThreshold(0.9); ;
    }

    MemoryWarningSystem mws = new MemoryWarningSystem();

    SlideBuilder slideBuilder;
    private Map<String, Integer> calculatedStates;
    private List<Photo> photos = new ArrayList<>();
    private int startSliceNumber = 3;

    private TreeSet<PhotoPair> pairs = new TreeSet<>();

    public RecursiveCalcRatingMegaEagerStep_SolvingD(SlideBuilder pizzaBuilder) {
        this.calculatedStates = SlideBuilder.calculatedStates;
        this.slideBuilder = pizzaBuilder;
        this.photos = pizzaBuilder.getPhotos();

        mws.addListener(new MemoryWarningSystem.Listener() {
            public void memoryUsageLow(long usedMemory, long maxMemory) {
                System.out.println("Memory usage!!!");
                MemoryWarningSystem.timeToStop = true;
            }
        });
    }

    public List<Slide> makeStep() {
        return rate(photos);
    }

    private List<Slide> rate(List<Photo> photos) {
        Set<Slide> slides = transformToSlide(photos);
        List<Slide> result = new LinkedList<>();
        int monitor = 0;
        Slide root = slides.stream().findFirst().get();
        slides.remove(root);
        result.add(root);

        while(slides.size() > 0) {
            monitor++;
            final Slide tale = root;
            Slide maxTale = slides.stream()
                .max((o1, o2) -> PhotoPair.rate(tale, o1)).get();
            slides.remove(maxTale);
            result.add(maxTale);
            root = maxTale;
        }
        return result;
    };

    private Set<Slide> transformToSlide(List<Photo> photos) {
        return photos
            .stream()
            .filter(photo -> photo.getType() == 1)
            .map(photo -> {
                Set<Photo> phots = new TreeSet<>();
                phots.add(photo);

                Set<String> tags = new HashSet<>();
                tags.addAll(photo.getTags());
                return new Slide(phots, 1, tags, null);})
            .collect(Collectors.toSet());
    }
}


