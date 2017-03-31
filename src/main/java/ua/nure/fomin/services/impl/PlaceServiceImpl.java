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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {

    private static final String DEFAULT_CATEGORY = "geoservice_sample";

    @Autowired
    private UserService service;

    public PlaceServiceImpl() {
    }

    public Place getByCity(String city) {
        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setWhereClause("city = '" + city + "'");
        return Backendless.Data.find(Place.class, query).getData().get(0);
    }

    public GeoPoint getByPlace(Place place){
        List<GeoPoint> points = getAll();
        points = points.stream().filter(point->point.getMetadata("place").equals(place)).collect(Collectors.toList());
        return points.get(0);
    }

    @Override
    public void add(GeoPoint point, String userName) {
        List places = new ArrayList();
        BackendlessUser user = service.find(userName);
        try {
            places = (List) user.getProperties().get("places");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (places == null) {
                places = new ArrayList();
            }
            point = Backendless.Geo.savePoint(point);
            places.add(point);
            user.setProperty("places", places);
            Backendless.Data.save(user);
            //Backendless.Data.save(point.getMetadata("place"));
        }
    }

    @Override
    public void delete(GeoPoint point, String userName) {
        List places = new ArrayList();
        BackendlessUser user = service.find(userName);
        try {
            places = (List) user.getProperties().get("places");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (places == null) {
                places = new ArrayList();
            }
            places.remove(point);
            user.setProperty("places", places);
            Place place = (Place) point.getMetadata("place");
            Backendless.Persistence.of(Place.class).remove(getByCity(place.getCity()));
        }
    }

    @Override
    public List<GeoPoint> findByCategory(String category) {
        BackendlessGeoQuery query = new BackendlessGeoQuery();
        query.addCategory(category);
        List<GeoPoint> points = Backendless.Geo.getPoints(query).getData();
        for (GeoPoint point : points) {
            point = Backendless.Geo.loadMetadata(point);
        }
        points = points.stream().filter(point -> point.getMetadata("place") != null).collect(Collectors.toList());
        return points;
    }

    @Override
    public List<GeoPoint> findByRadius(double radius, GeoPoint currentPoint) {
        BackendlessGeoQuery query = new BackendlessGeoQuery();
        query.setRadius(radius);
        query.setLatitude(currentPoint.getLatitude());
        query.setLatitude(currentPoint.getLatitude());
        List<GeoPoint> points = Backendless.Geo.getPoints(query).getData();
        for (GeoPoint point : points) {
            point = Backendless.Geo.loadMetadata(point);
        }
        points = points.stream().filter(point -> point.getMetadata("place") != null).collect(Collectors.toList());
        return points;
    }

    public List<GeoPoint> getAll() {
        List<GeoCategory> categories = Backendless.Geo.getCategories();
        List<GeoPoint> points = new ArrayList<>();
        for (GeoCategory category : categories) {
            String name = category.getName();
            if (!name.equals(DEFAULT_CATEGORY)) {
                BackendlessGeoQuery query = new BackendlessGeoQuery();
                query.addCategory(name);
                List<GeoPoint> searched = Backendless.Geo.getPoints(query).getData();
                points.addAll(searched);
            }
        }

        for (GeoPoint point : points) {
            point = Backendless.Geo.loadMetadata(point);
        }

        points = points.stream().filter(point -> point.getMetadata("place") != null).collect(Collectors.toList());
        return points;
    }
}
