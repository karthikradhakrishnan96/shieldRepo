package com.endurance.shield.dbserver.bio;

import com.endurance.shield.dbserver.todoList.Type;
import com.endurance.shield.dbserver.users.Squad;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by adeshk on 17/6/17.
 */
public interface BioRepository extends CrudRepository<Bio,String> {


    List<Bio> findByType(Type type);
}
