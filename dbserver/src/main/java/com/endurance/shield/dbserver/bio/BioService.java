package com.endurance.shield.dbserver.bio;

import com.endurance.shield.dbserver.todoList.Type;
import com.endurance.shield.dbserver.users.Squad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by adeshk on 17/6/17.
 */
@Service
public class BioService {

    @Autowired
    private BioRepository bioRepository;

    public Bio getBio(String username) {
        return bioRepository.findOne(username);
    }


    public void createBio(Bio bio) {
        bioRepository.save(bio);
    }

    public void updateBio(Bio bio) {
        bioRepository.save(bio);
    }

    public List<Bio> getAllBio(Squad squad) {
            List<Type> types = squad.getTypes();
            return this.bioRepository.findByTypeIn(types);
    }
}
