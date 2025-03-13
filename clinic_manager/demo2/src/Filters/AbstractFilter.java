package Filters;
import Domain.Identifiable;
import Domain.Patient;

public interface AbstractFilter<T> {
    public boolean accept(T entity);
}
