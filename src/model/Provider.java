package model;

import vo.Vacancy;

import java.util.Collections;
import java.util.List;

public class Provider {
    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Vacancy> getJavaVacancies(String searchString) {
        if (searchString==null)
            return Collections.emptyList();
        return strategy.getVacancies(searchString);
    }
}
