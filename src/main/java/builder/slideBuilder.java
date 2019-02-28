package builder;

import buildstep.BuildStep;
import buildstep.VisualizeResultStep;
import io.InputOutput;
import io.Parser;
import lombok.Data;
import model.Photo;

import java.util.*;

@Data
public class slideBuilder {
    private int maxRate = Integer.MIN_VALUE;
    private String maxKey = "";
    private List<Photo> photos = new LinkedList<Photo>();
    private Set<Photo> maxState = new TreeSet<>();
    public static Map<String, Integer> calculatedStates = new HashMap<>(10000000);

    int minIngredientNmber;
    int maxCellsSlice;

    public static void main(String[] args) {
        LinkedList<String> slidePlan = InputOutput.load("C://testSlides/1.txt");
        slideBuilder slideBuilder = new slideBuilder(slidePlan);


//        BuildStrategy solvingStrategy = slideBuilder.getStratgey();
//        solvingStrategy.build();
        BuildStep visualization = new VisualizeResultStep();
        visualization.makeStep();

//        InputOutput.export(Parser.exportResult(slideBuilder.getMaxState()), "C://testSlides/2.txt");

        System.out.println("Max State:" + slideBuilder.getMaxState());
        System.out.println("Max Rate:" + slideBuilder.getMaxRate());
        System.out.println("Max Key:" + slideBuilder.getMaxKey());
    }

    public slideBuilder(LinkedList<String> project) {
        this.photos = Parser.getMainEntity(project);
        System.out.println(photos);


    }

/*    private BuildStrategy getStratgey()
    {
       return new BuildStrategyOptimize(this);

    }*/
}
