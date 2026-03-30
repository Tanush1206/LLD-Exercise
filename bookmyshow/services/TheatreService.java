package bookmyshow.services;

import bookmyshow.models.Theatre;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class TheatreService {

    private final Map<String, Theatre> theatreDB = new HashMap<>();

    public Theatre addTheatre(Theatre theatre) {
        theatreDB.put(theatre.getTheatreId(), theatre);
        return theatre;
    }

    public Theatre getTheatre(String theatreId) {
        return theatreDB.get(theatreId);
    }

    public List<Theatre> listTheatresInCity(String cityId) {
        return theatreDB.values().stream()
                .filter(t -> t.getCity().getCityId().equals(cityId))
                .collect(Collectors.toList());
    }

    public Collection<Theatre> getAllTheatres() {
        return theatreDB.values();
    }
}
