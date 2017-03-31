package ua.nure.fomin.services;


import com.backendless.geo.GeoPoint;
import ua.nure.fomin.entities.Place;

import java.util.List;

public interface PlaceService {

    void add(GeoPoint point, String userName);

    void delete(GeoPoint point, String userName);

    List<GeoPoint> findByCategory(String category);

    List<GeoPoint> findByRadius(double radius, GeoPoint currentPoint);

    List<GeoPoint> getAll();






}
