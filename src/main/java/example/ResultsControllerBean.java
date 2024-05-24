package example;

import example.db.DAOFactory;
import example.db.ResultDAO;
import example.entity.ResultEntity;
import example.utils.PointChecker;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.util.*;

import java.io.Serializable;

/**
 * Managed bean for handling results in JSF application.
 * This bean is responsible for managing operations related to result entities.
 * @author bozhenka
 */
@Named
@ApplicationScoped
@Data
@Slf4j
public class ResultsControllerBean implements Serializable {
    /**
     * Injected dependency on XBean.
     */
    @Inject
    private XBean xBean;

    /**
     * Injected dependency on YBean.
     */
    @Inject
    private YBean yBean;

    /**
     * Injected dependency on RBean.
     */
    @Inject
    private RBean rBean;

    /**
     * Data Access Object for ResultEntity.
     */
    private ResultDAO resultDAO;

    /**
     * List of result entities.
     */
    private ArrayList<ResultEntity> results = new ArrayList<>();

    /**
     * Initializes the results controller bean by fetching all results from the database
     * and storing them in the results list. This method is called after the bean's
     * properties have been injected.
     */
    @PostConstruct
    public void init() {
        resultDAO = DAOFactory.getInstance().getResultDAO();
        var resultsEntities = resultDAO.getAllResults();
        results = new ArrayList<>(resultsEntities);
        Collections.reverse(results);
        log.info("Results initialized with {} entries.", results.size());
    }

    /**
     * Adds a new result to the list of results and stores it in the database.
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     * @param r the R radius
     */
    public void addResult(Double x, Double y, Double r) {
        final long startTime = System.nanoTime();

        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        String sessionId = session.getId();

        boolean success = PointChecker.isInArea(x, y, r);
        final long endTime = System.nanoTime();
        final long executionTime = (endTime - startTime) / (long) Math.pow(10, 3);

        ResultEntity entity = ResultEntity.builder().x(x).y(y).r(r).success(success).sessionId(sessionId).dateTime(ZonedDateTime.now()).executionTime(executionTime).build();
        results.add(0, entity);

        DAOFactory.getInstance().getResultDAO().addNewResult(entity);
//        log.info("Added new result to the db: X={}, Y={}, R={}", x, y, r);

        String script1 = String.format(Locale.US, "window.saveResult(%f, %f, %f, %b);", x, y, r, success);
        FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add(script1);
        String script2 = String.format(Locale.US, "window.drawResult(%f, %f, %f, %b);", x, y, r, success);
        FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add(script2);
    }

    /**
     * Draws the results on the client side by sending JavaScript commands.
     */
    public void drawResults() {
        for (ResultEntity result: getSessionResults()) {
            String script1 = String.format(Locale.US, "window.drawResult(%f, %f, %f, %b);", result.getX(), result.getY(), result.getR(), result.isSuccess());
            FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add(script1);
        }
    }

    /**
     * Retrieves the results associated with the current session.
     *
     * @return a list of result entities associated with the current session
     */
    public List<ResultEntity> getSessionResults() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        String sessionId = session.getId();

        return results.stream()
                .filter(result -> result.getSessionId().equals(sessionId))
                .toList();
    }

    /**
     * Clears the results associated with the current session from the list and the database.
     */
    public void clearSessionResults() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        String sessionId = session.getId();

        results.removeIf(result -> result.getSessionId().equals(sessionId));
        resultDAO.clearSessionResults(sessionId);

        String script1 = "window.clearCanvas();";
        FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add(script1);
    }

    /**
     * Updates the canvas on the client side to reflect the current state.
     */
    public void updateCanvas() {
        String script1 = String.format(Locale.US, "window.updateCanvas(%f);", rBean.getR());
        FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add(script1);

        this.drawResults();
    }
}

