package buildstep;

/*public class RecursiveCalcRatingMegaEagerStep_SolvingD implements BuildStep {
    static {
        MemoryWarningSystem.setPercentageUsageThreshold(0.9); ;
    }

    MemoryWarningSystem mws = new MemoryWarningSystem();

    slideBuilder pizzaBuilder;
    private Pizza pizza;
    private Map<String, Integer> calculatedStates;
    private TreeSet<Photo> placedSlices = new TreeSet<>();
    private int startSliceNumber = 3;

    public RecursiveCalcRatingMegaEagerStep_SolvingD(slideBuilder pizzaBuilder) {
        this.pizza = pizzaBuilder.getPizza();
        this.calculatedStates = slideBuilder.calculatedStates;
        this.pizzaBuilder = pizzaBuilder;

        mws.addListener(new MemoryWarningSystem.Listener() {
            public void memoryUsageLow(long usedMemory, long maxMemory) {
                System.out.println("Memory usage!!!");
                MemoryWarningSystem.timeToStop = true;
            }
        });
    }

    @Override
    public void makeStep() {
        rate(this.placedSlices, 0);
    }

    private void rate(TreeSet<Photo> slices, int currentRate) {
        Photo slice = new Photo(1, 1, 0, 0, startSliceNumber++);
        slices.add(slice);

        if (startSliceNumber == 13) {
            System.out.println(startSliceNumber);
        }

        boolean foundSize = false;

        while(inPizza(slice)) {
            if(foundSize = nextSize(slice)) {
                CalcPizzaRatingStep calcBuildingRatingStep = new CalcPizzaRatingStep(
                    pizzaBuilder,
                    slice,
                    placedSlices,
                    currentRate);
                calcBuildingRatingStep.makeStep();
                int newRate = calcBuildingRatingStep.getCurrentRateAfter();

                if (newRate >= currentRate) {
                    pizza.placeSlice(slice);
//                    System.out.println(pizza);

                    rate(slices, newRate);

                    pizza.removeSlice(slice);
//                    System.out.println(pizza);
                }
            }

            if ((foundSize && pizza.canPlace(slice)) || MemoryWarningSystem.timeToStop)
                break;

            slice.resetSize();
            moveSlice(slice);
        }
        slices.remove(slice);
    }

    private boolean inPizza(Photo slice){
        return slice.getXTopLeft() + slice.getWidth() <= this.pizza.getWidth() &&
            slice.getYTopLeft() + slice.getHeight() <= this.pizza.getHeight();
    }

    private boolean isInEndOfRow(Photo slice){
        return slice.getXTopLeft() + slice.getWidth() == this.pizza.getWidth();
    }

    private void moveToNextRow(Photo slice) {
        slice.setXTopLeft(0);
        slice.setYTopLeft(slice.getYTopLeft() + 1);
    }

    private void moveNext(Photo slice) {
        slice.setXTopLeft(slice.getXTopLeft() + 1);
    }

    private void moveSlice(Photo slice) {
        if (isInEndOfRow(slice)) {
            moveToNextRow(slice);
        } else {
            moveNext(slice);
        }
    }

    private boolean nextSize(Photo slice) {
        for (int i = slice.getWidth(); i < pizza.getWidth() - slice.getXTopLeft() + 1; ++i) {
            for (int j = slice.getHeight(); j < pizza.getHeight() - slice.getYTopLeft() + 1; ++j) {
                Photo newSlice = Photo
                    .builder()
                    .width(i)
                    .height(j)
                    .xTopLeft(slice.getXTopLeft())
                    .yTopLeft(slice.getYTopLeft())
                    .build();
                if (!pizza.canPlace(newSlice)) return false;
                if (!newSlice.isValidCapacity(pizzaBuilder.getMaxCellsSlice())) return false;
                if (newSlice.enoughIngrdients(pizza, pizzaBuilder.getMinIngredientNmber())) {
                    slice.setWidth(newSlice.getWidth());
                    slice.setHeight(newSlice.getHeight());
                    return true;
                }
            }
        }
        return false;
    }
}*/


