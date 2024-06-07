package example.mbeans;

import example.db.ResultDAOImpl;

public interface NumberOfPointsMBean {
    int getAllPoints();

    void incrementAllPoints();

    int getMissingPoints();

    void incrementMissingPoints();
}
