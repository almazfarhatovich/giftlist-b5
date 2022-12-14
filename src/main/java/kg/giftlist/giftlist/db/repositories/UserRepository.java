package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("select case when count(u)>0 then true else false end from User u where u.email like :email")
    boolean existsByEmail(@Param(value = "email") String email);

    @Query("select f from User u join u.friends f where f.isBlock=false and u.id=?1")
    List<User> findAllFriends(Long userId);

    @Query("select f from User u join u.requestToFriends f where f.isBlock=false and u.id=?1")
    List<User> findAllRequestToFriends(Long userId);

    @Query("select u from User u where upper(u.firstName) like %?1% or upper(u.lastName) like %?1%")
    List<User> searchAllByFirstNameAndLastName(String name);

    @Query("select u from User u where u.isBlock=false and upper(u.firstName) like %?1% or upper(u.lastName) like %?1%")
    List<User> searchUnblockUsersByFirstNameAndLastName(String name);

    @Query("select u from User u where u.role = 'USER' ")
    List<User> getAll();

    @Query("select u from User u where u.role='ADMIN'")
    Optional<User> findByRole();

}