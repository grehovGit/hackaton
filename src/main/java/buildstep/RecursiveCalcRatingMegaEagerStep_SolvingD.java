package buildstep;

import builder.MemoryWarningSystem;
import builder.SlideBuilder;
import model.Photo;
import model.PhotoPair;
import model.Slide;

import java.util.*;

public class RecursiveCalcRatingMegaEagerStep_SolvingD implements BuildStep {
    static {
        MemoryWarningSystem.setPercentageUsageThreshold(0.9); ;
    }

    MemoryWarningSystem mws = new MemoryWarningSystem();

    SlideBuilder slideBuilder;
    private Map<String, Integer> calculatedStates;
    private List<Photo> photos = new ArrayList<>();
    private TreeSet<Slide> slides = new TreeSet<>();
    private int startSliceNumber = 3;

    private TreeSet<PhotoPair> pairs = new TreeSet<>();

    public RecursiveCalcRatingMegaEagerStep_SolvingD(SlideBuilder pizzaBuilder) {
        this.calculatedStates = SlideBuilder.calculatedStates;
        this.slideBuilder = pizzaBuilder;
        this.slides = pizzaBuilder.getSlides();

        mws.addListener(new MemoryWarningSystem.Listener() {
            public void memoryUsageLow(long usedMemory, long maxMemory) {
                System.out.println("Memory usage!!!");
                MemoryWarningSystem.timeToStop = true;
            }
        });
    }

    @Override
    public void makeStep() {
        rate(photos);
    }

    private void rate(List<Photo> photos) {
        List<Slide> slides = transformToSlide(photos);

        for (int i = 0; i < slides.size(); ++i) {
            for (int j = 0; j < slides.size(); ++j) {

                Slide sl1 = slides.get(i);
                Slide sl2 = slides.get(j);

                PhotoPair pair = new PhotoPair(i, j, PhotoPair.rate(sl1, sl2));
                Set a = new HashSet();

                if (pairs.contains(pair)) {
//                    PhotoPair gotPair = pairs.g
                }



                pairs.add()
            }
        }

    };

    private List<Slide> transformToSlide(List<Photo> photos) {
        List<Slide> slides = new ArrayList<>(1000000);
        Collections.sort(photos, new Comparator<Photo>() {
            @Override
            public int compare(Photo o1, Photo o2) {
                return o1.getType() - o2.getType();
            }
        });

        for (int i = 0; i < photos.size(); ++i) {
            Photo photo = photos.get(i);

            if (photo.getType() == 1) {
                Set<Photo> phots = new TreeSet<>();
                phots.add(photo);

                Set<String> tags = new HashSet<>();
                tags.addAll(photo.getTags());
                slides.add(new Slide(phots, 1, tags));
            } else if (photo.getType() == 2) {
                Set<Photo> phots = new TreeSet<>();
                phots.add(photo);

                Set<String> tags = new HashSet<>();
                tags.addAll(photo.getTags());

                i++;
                if (i < photos.size()) {
                    Photo nextPhoto = photos.get(i);
                    phots.add(nextPhoto);
                    tags.addAll(nextPhoto.getTags());
                    slides.add(new Slide(phots, 1, new HashSet<String>()));
                }
            }
        }
        return slides;
    }



}


