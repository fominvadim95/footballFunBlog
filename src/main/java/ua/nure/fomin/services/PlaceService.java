package ua.nure.fomin.services;


import com.backendless.geo.GeoPoint;
import ua.nure.fomin.entities.Place;

import java.util.List;

public interface PlaceService {

    void add(Place place, String userName);

    void delete(Place place, String userName);

    List<Place> findByCategory(String category);

    List<Place> findByRadius(double radius, GeoPoint currentPoint);

    List<Place> getAll();






}
