package Domain;

public interface Identifiable<ID> {
    public ID get_id();
    public void set_id(ID id);
    public String toString();
    public boolean equals(Object obj);
}
