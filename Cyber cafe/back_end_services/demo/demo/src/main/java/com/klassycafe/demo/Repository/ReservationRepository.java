package com.klassycafe.demo.Repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klassycafe.demo.Entity.Reservation;

// @Qualifier("res")
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
