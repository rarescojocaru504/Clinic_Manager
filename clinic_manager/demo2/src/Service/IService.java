package Service;
import Domain.Identifiable;
public interface IService<ID, T extends Identifiable<ID>> {
    void add(T t);
    void modify(ID id, T t);
    void delete(ID id);
    T find_by_id(ID id);
    Iterable<T> getAll();
    void display();
    public boolean isEmpty();
}
