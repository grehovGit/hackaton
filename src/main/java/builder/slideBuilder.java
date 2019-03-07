package builder;

import buildstep.BuildStep;
import buildstep.RecursiveCalcRatingMegaEagerStep_SolvingD;
import buildstep.VisualizeResultStep;
import io.InputOutput;
import io.Parser;
import lombok.Data;
import model.Photo;
import model.PhotoPair;
import model.Slide;
import org.apache.http.client.utils.URIBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.*;

@Data
public class SlideBuilder {
    private int maxRate = Integer.MIN_VALUE;
    private String maxKey = "";
    private List<Photo> photos = new LinkedList<Photo>();
    private TreeSet<Slide> slides = new TreeSet<>();
    private Set<Photo> maxState = new TreeSet<>();
    public static Map<String, Integer> calculatedStates = new HashMap<>(10000000);

    int minIngredientNmber;
    int maxCellsSlice;

    public static void main(String[] args) {
        LinkedList<String> slidePlan = InputOutput.load("C://testSlides/1.txt");
        SlideBuilder slideBuilder = new SlideBuilder(slidePlan);

        RecursiveCalcRatingMegaEagerStep_SolvingD aaa = new RecursiveCalcRatingMegaEagerStep_SolvingD(slideBuilder);
        List<Slide> slides = aaa.makeStep();


        BuildStep visualization = new VisualizeResultStep();
        visualization.makeStep();

        InputOutput.export(Parser.exportResult(slides), "C://testSlides/2.txt");

        System.out.println("Max State:" + slideBuilder.getMaxState());
        System.out.println("Max Rate:" + slideBuilder.getMaxRate());
        System.out.println("Max Key:" + slideBuilder.getMaxKey());
    }

    public SlideBuilder(LinkedList<String> project) {
        this.photos = Parser.getMainEntity(project);
        System.out.println(photos);
        maxState.hashCode();
        Set<String> a = new HashSet<>();
        a.add("aa");
        System.out.println(a.hashCode());
        a.add("bb");
        System.out.println(a.hashCode());
        a.add("cc");
        System.out.println(a.hashCode());


    }

/*    private BuildStrategy getStratgey()
    {
       return new BuildStrategyOptimize(this);

    }*/
}
