package example.mbeans;

public class PercentageOfMisses implements PercentageOfMissesMBean {
    private double percentage = 0.0;
    private final NumberOfPointsMBean numberOfPoints;

    public PercentageOfMisses(NumberOfPointsMBean numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    @Override
    public double getPercentageOfMisses() {
        int miss = this.numberOfPoints.getMissPoints();
        int allPoints = this.numberOfPoints.getAllPoints();
        if (allPoints != 0) {
            this.percentage = (miss * 100) / (double) allPoints;
        } else {
            percentage = 0.0;
        }
        return this.percentage;
    }
}
