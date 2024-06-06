package example.mbeans;

import example.db.ResultDAOImpl;

public interface NumberOfPointsMBean {
    public int allPoints(ResultDAOImpl resultDAO);

    public int missingPoints();
}
