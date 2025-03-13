package Repository;

import java.util.ArrayList;

public interface IRepository<ID,T> {
    public void add(T entity);
    public void modify(ID id,T entity);
    public void delete(ID id);
    public T find_by_id(ID id);
    ArrayList<T> getAll();
    public void display();
    public boolean isId(ID id);
    public boolean isEmpty();
}
