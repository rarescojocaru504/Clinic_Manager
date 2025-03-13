package Service;

import Domain.Identifiable;
import Repository.FilterRepository;

public class FilterService<ID, T extends Identifiable<ID>>{
    private FilterRepository<ID, T> filterRepository;

    public FilterService(FilterRepository<ID, T> filterRepository) {
        this.filterRepository = filterRepository;
    }

    public Iterable<T> getAllFiltered() {
        return filterRepository.getAll();
    }

    public void displayFiltered() {
        filterRepository.displayFiltered();
    }
}