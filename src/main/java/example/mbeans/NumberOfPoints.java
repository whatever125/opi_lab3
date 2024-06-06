package example.mbeans;

import example.db.DAOFactory;
import example.db.ResultDAO;
import example.db.ResultDAOImpl;
import example.entity.ResultEntity;

import javax.management.NotificationBroadcasterSupport;
import java.util.Collection;

public class NumberOfPoints extends NotificationBroadcasterSupport implements NumberOfPointsMBean{
    private final ResultDAO resultDAO;

    public NumberOfPoints() {
        this.resultDAO = DAOFactory.getInstance().getResultDAO();
    }


    @Override
    public int allPoints(ResultDAOImpl resultDAO) {
        return resultDAO.getAllResults().size();
    }

    @Override
    public int missingPoints() {
        int countMissed = 0;
        Collection<ResultEntity> results = resultDAO.getAllResults();
        for (ResultEntity result : results) {
            if (!result.isSuccess()) {
                countMissed += 1;
            }
        }
        return countMissed;
    }
}
