package com.example.carsproject.repository;

import com.example.carsproject.dto.response.CarDTO;
import com.example.carsproject.entity.Car;
import com.example.carsproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    @Query("select c from Car c INNER join c.user f ON f.id = c.user.id")
    List<Car> finD();


//    Optional<Car> findCarById(String id);
//    @Modifying
//    @Query("DELETE FROM Car c WHERE c.id = :id")
//    void deleteCarById(@Param("id") String id);
    @Query("SELECT c FROM Car c " +
            "WHERE (:colour is null OR c.colour = :colour) " +
            "AND (:model is null OR c.model = :model) " +
            "AND (:name is null OR c.name = :name) " +
            "AND (:powerOfMotor is null OR c.powerOfMotor = :powerOfMotor) " +
            "AND (:year is null OR c.year = :year) " +
            "AND (:price is null OR c.price = :price)")
    List<Car> getBySpesifiedFields(String name, String model, String colour, String powerOfMotor, Long year, String price);
@Query("select c from Car c inner join User u on u.id = c.user.id " +
        "where c.user.email = :email")
    List<Car> getCarsForOwners(String email);

//    @Query("SELECT c FROM Car c WHERE c.name LIKE :name")
//    List<Car> finByNameOfCars(@Param("name") String name);
//
//    @Query("select c from Car c where c.model like :model")
//    List<Car> findByNameOfModels(@Param("model") String model);

    @Query("select distinct c.name from Car c")
    List<String> getCarsName();
    @Query("select distinct c.model from Car c")
    List<String> getCarsModel();
    @Query("select distinct c.colour from Car c")
    List<String> getCarsColour();

    @Query("select distinct c.year from Car c")
    List<Long> getCarsYear();

//    @Query("select c from Car c where c.colour LIKE :colour")
//    List<Car> findByColour(@Param("colour") String colour);
//
//    @Query("select c from Car c where c.powerOfMotor LIKE :powerOfMotor")
//    List<Car> finBypowerOfMotor(@Param("powerOfMotor") String powerOfMotor);
//
//    @Query("select c from Car c where c.year = :year")
//    List<Car> finByYear(Double year);
//
//    @Query("select c from Car c where c.price like :price")
//    List<Car> finByPrice(@Param("price")  String price);
}
