package systemApplication.repository;

import systemApplication.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    /* Required Queries */
    //Find all addresses in a specific zip code area.
    List<Address> findByZipCode(String zipCode);

    /*Optional / Advanced Queries */
    //Find all addresses in a specific city.
    List<Address> findByCity(String city);

    //Find addresses by street name.
    List<Address> findByStreetContaining(String street);

    //Count how many customers live in a given zip code.
    long countByZipCode(String zipcode);

    //Find addresses where zip code starts with a prefix.
    List<Address> findByZipCodeStartingWith(String prefix);

}
