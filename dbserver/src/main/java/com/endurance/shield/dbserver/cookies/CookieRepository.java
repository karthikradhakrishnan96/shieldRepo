package com.endurance.shield.dbserver.cookies;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by kai on 16/6/17.
 */
public interface CookieRepository extends CrudRepository<Cookie,String> {
    List<Cookie> findByUsernameIn(String username);
}
