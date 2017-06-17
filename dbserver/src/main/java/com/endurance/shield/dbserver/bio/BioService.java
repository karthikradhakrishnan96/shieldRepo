package com.endurance.shield.dbserver.bio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by adeshk on 17/6/17.
 */
@Service
public class BioService {

    @Autowired
    private BioRepository bioRepository;

    public Bio getBio(int id) {
        return bioRepository.findOne(id);
    }


    public void createBio(Bio bio) {
        bioRepository.save(bio);
    }

    public void updateBio(Bio bio) {
        bioRepository.save(bio);
    }
}
