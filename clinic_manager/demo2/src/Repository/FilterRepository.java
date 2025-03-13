package Repository;

import Domain.Identifiable;
import Filters.AbstractFilter;
import java.util.ArrayList;
import java.util.List;

public class FilterRepository<ID, T extends Identifiable<ID>> extends MemoryRepository<ID, T> {
    private AbstractFilter<T> filter;

    public FilterRepository(AbstractFilter<T> filter) {
        this.filter = filter;
    }

    public void setFilter(AbstractFilter<T> filter) {
        this.filter = filter;
    }

    @Override
    public ArrayList<T> getAll() {
        List<T> filteredEntities = new ArrayList<>();
        for (T entity : super.getAll()) {
            if (filter.accept(entity)) {
                filteredEntities.add(entity);
            }
        }
        return (ArrayList<T>) filteredEntities;
    }

    public void displayFiltered() {
        Iterable<T> filteredEntities = getAll();
        if (!filteredEntities.iterator().hasNext()) {
            System.out.println("No filtered entities found.");
        } else {
            for (T entity : filteredEntities) {
                System.out.println(entity);
            }
        }
    }
}