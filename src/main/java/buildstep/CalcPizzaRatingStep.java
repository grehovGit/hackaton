package buildstep;

/*
@Data
public class CalcPizzaRatingStep  {
    private Pizza pizza;
    private Map<String, Integer> calculatedStates;
    private TreeSet<Photo> placedSlices;
    private Photo nextSlice;
    private SlideBuilder slideBuilder;
    private int currentRateBefore;
    private int currentRateAfter;

    public CalcPizzaRatingStep(
        SlideBuilder slideBuilder,
        Photo nextBuilding,
        TreeSet placedBuildings,
        int currentRate) {
        this.slideBuilder = slideBuilder;
        this.pizza = slideBuilder.getPizza();
        this.calculatedStates = SlideBuilder.calculatedStates;
        this.placedSlices = placedBuildings;
        this.nextSlice = nextBuilding;
        this.currentRateBefore = currentRate;
    }

    @Override
    public void makeStep() {
        String currentState = this.getNewState();
        Integer rate = calculatedStates.get(currentState);

        if (rate == null) {
            rate = currentRateBefore + calculateNextSliceRate();
        }

        remeberState(currentState, rate);

        if (rate > this.slideBuilder.getMaxRate()) {
            this.slideBuilder.setMaxRate(rate);
            this.slideBuilder.setMaxKey(currentState);
            this.updateMaxState();
        }
        this.currentRateAfter = rate;
    }

    private void updateMaxState() {
        TreeSet<Photo> maxState = new TreeSet<>();
        placedSlices.forEach(building -> {
            maxState.add(Photo.builder()
                .width(building.getWidth())
                .height(building.getHeight())
                .xTopLeft(building.getXTopLeft())
                .yTopLeft(building.getYTopLeft())
                .number(building.getNumber())
                .build());});
        this.slideBuilder.setMaxState(maxState);
    }

    private int calculateNextSliceRate() {
        if (pizza.canPlace(nextSlice)) {
            return nextSlice.getCapacity();
        }
        return -1;
    }

    private String getNewState() {
        return  placedSlices.toString();
    }

    private void remeberState(String state, int rate) {
        this.calculatedStates.put(state, rate);
    }
}
*/
