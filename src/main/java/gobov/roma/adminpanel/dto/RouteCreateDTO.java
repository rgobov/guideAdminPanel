package gobov.roma.adminpanel.dto;

import gobov.roma.adminpanel.model.Route.EnvironmentType;
import gobov.roma.adminpanel.model.Route.TourFormat;

import java.util.List;
import java.util.Set;

public class RouteCreateDTO {

    private String title;
    private String description;
    private String city;
    private Integer duration; // В минутах
    private Double distance; // В километрах
    private List<String> categories;
    private Set<EnvironmentType> environmentTypes;
    private TourFormat tourFormat;
    private List<Long> pointIds; // Список ID точек интереса

    // Геттеры и сеттеры
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Set<EnvironmentType> getEnvironmentTypes() {
        return environmentTypes;
    }

    public void setEnvironmentTypes(Set<EnvironmentType> environmentTypes) {
        this.environmentTypes = environmentTypes;
    }

    public TourFormat getTourFormat() {
        return tourFormat;
    }

    public void setTourFormat(TourFormat tourFormat) {
        this.tourFormat = tourFormat;
    }

    public List<Long> getPointIds() {
        return pointIds;
    }

    public void setPointIds(List<Long> pointIds) {
        this.pointIds = pointIds;
    }
}