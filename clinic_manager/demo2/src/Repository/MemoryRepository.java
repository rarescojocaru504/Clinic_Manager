package Repository;
import Domain.Identifiable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Domain.Patient;
import MyExceptions.EmptyRepoException;
import MyExceptions.ID_ExceptionNotFound;
import MyExceptions.ID_ExceptionAlready;

public class MemoryRepository<ID, T extends Identifiable<ID>> implements IRepository<ID, T> {
    protected HashMap<ID, T> entities = new HashMap<>();

    @Override
    public void add(T entity) {
        if (entities.containsKey(entity.get_id()))throw new ID_ExceptionAlready();
        entities.put(entity.get_id(), entity);
    }

    @Override
    public void delete(ID id) {
        if(entities.containsKey(id)) {
            entities.remove(id);
        }
        else throw new ID_ExceptionNotFound();
    }

    @Override
    public T find_by_id(ID id) {
        if(entities.containsKey(id)) {
            return entities.get(id);
        }
        else throw new ID_ExceptionNotFound();
    }

    @Override
    public void modify(ID id, T entity) {
        if (!entities.containsKey(id))throw new ID_ExceptionNotFound();
        T ex_entity = this.find_by_id(id);
        if(!(id.equals(ex_entity.get_id())) && entities.containsKey(id))throw new ID_ExceptionAlready();
        entities.put(id, entity);
    }

    @Override
    public ArrayList<T> getAll() {
        return new ArrayList<>(entities.values());
    }


    @Override
    public boolean isId(ID id) {
        return entities.containsKey(id);
    }

    @Override
    public boolean isEmpty(){
        return entities.isEmpty();
    }
    public void display(){
        if (entities.isEmpty())throw new EmptyRepoException();
        for (T entity : entities.values()) {
            System.out.println(entity);
            System.out.println("\n");
        }
    }

}
