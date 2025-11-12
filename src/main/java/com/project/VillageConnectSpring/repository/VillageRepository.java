package com.project.VillageConnectSpring.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.VillageConnectSpring.model.Village;

public interface VillageRepository extends JpaRepository<Village, Integer> {
	Optional<Village> findByVillageName(String villageName);
}
