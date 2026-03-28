package com.playtab.stamptourservice.spot;

import org.springframework.data.jpa.repository.JpaRepository;

// Spot 엔티티용 JPA Repository
public interface SpotRepository extends JpaRepository<Spot, Long> {
}