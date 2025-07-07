package repository;

import java.util.ArrayList;
import java.util.Optional;

public interface ICrudOperations {
    void insert(Object object);
    void delete(String title);
    Optional<ArrayList<Object>>  selectAll();
    Optional<Object> update(String title, Object object);

}
