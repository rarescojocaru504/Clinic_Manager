package Repository;

import Domain.Identifiable;

public abstract class FileRepository<ID, T extends Identifiable<ID>> extends MemoryRepository<ID,T> {
    String path;

    public FileRepository(String path) {
        this.path = path;
        file_in();
    }

    public String getPath() {
        return path;
    }

    public abstract void file_in();

    public abstract void file_out();

    public abstract void file_out_t(T t);

    public void eraseFile() {

    }

    @Override
    public void add(T t) {
        super.add(t);
        file_out();
    }

    @Override
    public void delete(ID id) {
        super.delete(id);
        file_out();
    }

    @Override
    public void modify(ID id, T t) {
        super.modify(id, t);
        file_out();
    }

    @Override
    public T find_by_id(ID id){
        T t = super.find_by_id(id);
        System.out.println("Found : " + t);
        file_out_t(t);
        return t;
    }

}
