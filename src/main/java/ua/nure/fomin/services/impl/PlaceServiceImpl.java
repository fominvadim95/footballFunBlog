package ua.nure.fomin.services.impl;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.geo.BackendlessGeoQuery;
import com.backendless.geo.GeoCategory;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.fomin.entities.Place;
import ua.nure.fomin.services.PlaceService;
import ua.nure.fomin.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private UserService service;

    public PlaceServiceImpl() {
    }

    public Place getByCity(String city) {
        BackendlessDataQuery query = new BackendlessDataQuery();
        city = city.substring(1, city.length() - 1);
        query.setWhereClause("city = '" + city + "'");
        return Backendless.Data.find(Place.class, query).getData().get(0);
    }


    @Override
    public void add(Place place, String userName) {
        List places = new ArrayList();
        BackendlessUser user = service.find(userName);
        try {
            places = (List) user.getProperties().get("places");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            places.add(place);
            user.setProperty("places", places);
            Backendless.Data.save(user);
        }
    }

    @Override
    public void delete(Place place, String userName) {
        List places = new ArrayList();
        BackendlessUser user = service.find(userName);
        try {
            places = (List) user.getProperties().get("places");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            places.remove(place);
            user.setProperty("places", places);
            Backendless.Persistence.of(Place.class).remove(getByCity(place.getCity()));
        }
    }

    @Override
    public List<Place> findByCategory(String category) {
        BackendlessGeoQuery query = new BackendlessGeoQuery();
        query.addCategory(category);
        List<GeoPoint> points = Backendless.Geo.getPoints(query).getData();
        List<Place> places = new ArrayList<>();
        for (GeoPoint point : points) {
            for (Place place : places) {
                if (point.equals(place.getPoint())) {
                    places.add(place);
                    break;
                }
            }
        }

        return places;
    }

    @Override
    public List<Place> findByRadius(double radius, GeoPoint currentPoint) {
        BackendlessGeoQuery query = new BackendlessGeoQuery();
        query.setRadius(radius);
        query.setLatitude(currentPoint.getLatitude());
        query.setLatitude(currentPoint.getLatitude());
        List<GeoPoint> points = Backendless.Geo.getPoints(query).getData();
        List<Place> places = new ArrayList<>();
        for (GeoPoint point : points) {
            for (Place place : places) {
                if (point.equals(place.getPoint())) {
                    places.add(place);
                    break;
                }
            }
        }

        return places;
    }

    public List<Place> getAll() {
        return Backendless.Data.find(Place.class, new BackendlessDataQuery()).getCurrentPage();
    }
}
