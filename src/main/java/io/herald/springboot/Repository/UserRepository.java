package io.herald.springboot.Repository;

import io.herald.springboot.Model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Integer> {

    boolean existsByUsernameAndPassword(String un, String pwd);

    void deleteById(int id);
    //existsBy function can be found already in our repo, but username and
    // password cant be detected directly by existsBy function.
    //Hence, if our user table has columns named "username" and "password"
    //we can suggest our repository to look for it, if the value exists or not.

    //Custom Syntaxes Signature


}
