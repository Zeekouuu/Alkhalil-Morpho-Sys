package com.tafssir.tafssir.repository;

import com.tafssir.tafssir.model.Tafssir;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface tafssirRepo extends JpaRepository<Tafssir, Long> {
	@Query("SELECT u FROM Tafssir u WHERE u.naya = ?1 AND u.nsoura = ?2")
	List<Tafssir> findByAllTafssir2(Integer numaya, Integer numsoura);

	@Query("SELECT u FROM Tafssir u WHERE u.ntafsir = ?1 ")
	List<Tafssir> findByAllDakci(Integer NTafsir);
}
