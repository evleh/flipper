package at.technikum.visitor;

public interface PointSource {
    int getHitCount();
    int getPointsPerHit();
    int calculatePoints();
}
