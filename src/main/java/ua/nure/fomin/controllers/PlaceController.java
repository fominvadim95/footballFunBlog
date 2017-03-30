package ua.nure.fomin.controllers;

import com.backendless.geo.GeoPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.nure.fomin.entities.Place;
import ua.nure.fomin.services.PlaceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "/funBlog/createPlace", method = RequestMethod.POST)
    public String addPlace(HttpServletRequest request) {
        String name = request.getParameter("placeName");
        String category = request.getParameter("categoryName");
        String city = request.getParameter("city");
        double x = Double.parseDouble(request.getParameter("x"));
        double y = Double.parseDouble(request.getParameter("y"));
        String description = request.getParameter("description");
        Place place = new Place();
        place.setName(name);
        place.setCity(city);
        place.setDescription(description);
        GeoPoint point = new GeoPoint(x, y);
        point.addCategory(category);
        place.setPoint(point);
        placeService.add(place, (String) request.getSession().getAttribute("name"));
        return "redirect:/funBlog";
    }


    @RequestMapping(value = "/funBlog/deletePlace", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deletePlace(@RequestBody String city, HttpSession session) {
        Place place = new Place();
        place.setCity(city);
        placeService.delete(place, (String) session.getAttribute("name"));
        return "redirect:/funBlog";
    }


}
