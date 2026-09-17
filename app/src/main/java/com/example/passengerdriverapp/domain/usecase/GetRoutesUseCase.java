package com.example.passengerdriverapp.domain.usecase;

import com.example.passengerdriverapp.data.model.Route;
import com.example.passengerdriverapp.data.repository.AppRepository;
import java.util.List;

public class GetRoutesUseCase {
    private final AppRepository repository;

    public GetRoutesUseCase(AppRepository repository) {
        this.repository = repository;
    }

    public List<Route> execute() {
        return repository.getRoutes();
    }
}
