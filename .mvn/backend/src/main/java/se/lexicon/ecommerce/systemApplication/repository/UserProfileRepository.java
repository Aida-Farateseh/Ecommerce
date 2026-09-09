package systemApplication.repository;

import systemApplication.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    /* Required Queries */
    //Find a profile by nickname.
    Optional<UserProfile> findByNickname(String nickname);

    //Search for profiles by a partial phone number.
    List<UserProfile> findByPhoneNumberContaining(String phoneNumber);

    /* Optional / Advanced Queries*/
    //Find profiles where bio is not null.
    List<UserProfile> findByBioIsNotNull();

    //Find profiles by nickname starting with a prefix.
    List<UserProfile> findByNicknameStartingWith(String prefix);

    //Find profiles created after a specific date (if applicable).
    List<UserProfile> findByCustomer_CreatedAtAfter(Instant date);

    //Count profiles with a specific phone number prefix.
    long countByPhoneNumberStartingWith(String prefix);
}