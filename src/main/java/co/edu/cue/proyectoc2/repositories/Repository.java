package co.edu.cue.proyectoc2.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository <T>{
    List<T> listar()throws SQLException;
    T porId(Long id) throws SQLException;
    void save(T t) throws SQLException;
    void delet(Long id) throws SQLException;

}
