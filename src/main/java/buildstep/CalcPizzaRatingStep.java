package buildstep;

/*
@Data
public class CalcPizzaRatingStep  {
    private Pizza pizza;
    private Map<String, Integer> calculatedStates;
    private TreeSet<Photo> placedSlices;
    private Photo nextSlice;
    private slideBuilder pizzaBuilder;
    private int currentRateBefore;
    private int currentRateAfter;

    public CalcPizzaRatingStep(
        slideBuilder pizzaBuilder,
        Photo nextBuilding,
        TreeSet placedBuildings,
        int currentRate) {
        this.pizzaBuilder = pizzaBuilder;
        this.pizza = pizzaBuilder.getPizza();
        this.calculatedStates = slideBuilder.calculatedStates;
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

        if (rate > this.pizzaBuilder.getMaxRate()) {
            this.pizzaBuilder.setMaxRate(rate);
            this.pizzaBuilder.setMaxKey(currentState);
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
        this.pizzaBuilder.setMaxState(maxState);
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
