package com.example.carsproject.repository;

import com.example.carsproject.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("""
            select c from Car c INNER join c.fkOwnerId f ON f.id = c.fkOwnerId.id
                        """)
    List<Car> finD();
    @Query("SELECT c FROM Car c WHERE c.name LIKE :name")
    List<Car> finByNameOfCars(@Param("name") String name);

    @Query("select c from Car c where c.model like :model")
    List<Car> findByNameOfModels(@Param("model") String model);

    @Query("select c from Car c where c.colour LIKE :colour")
    List<Car> findByColour(@Param("colour") String colour);

    @Query("select c from Car c where c.powerOfMotor LIKE :powerOfMotor")
    List<Car> finBypowerOfMotor(@Param("powerOfMotor") String powerOfMotor);

    @Query("select c from Car c where c.year = :year")
    List<Car> finByYear(Double year);

    @Query("select c from Car c where c.price like :price")
    List<Car> finByPrice(@Param("price")  String price);
}
