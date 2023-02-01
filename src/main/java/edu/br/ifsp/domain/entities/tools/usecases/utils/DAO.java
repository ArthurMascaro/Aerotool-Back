package edu.br.ifsp.domain.entities.tools.usecases.utils;

import java.util.List;
import java.util.Optional;

public interface DAO <T, K> {
    K create(T Type);

    Optional<T> findOne(K  key);

    List<T> findALL();

    boolean update(T type);

    boolean deleteByKey(K key);

    boolean delete(T type);


}
