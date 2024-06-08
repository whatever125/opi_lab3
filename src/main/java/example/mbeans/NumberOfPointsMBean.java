package example.mbeans;

public interface NumberOfPointsMBean {
    void incrementAllPoints();

    int getAllPoints();

    void incrementMissPoints();

    int getMissPoints();

    void resetConsecutiveMisses();
}
