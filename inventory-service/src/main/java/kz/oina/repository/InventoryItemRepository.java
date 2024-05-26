package kz.oina.repository;

import kz.oina.entity.InventoryItem;
import kz.oina.entity.InventoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, UUID> {
    Optional<InventoryItem> findByToyId(UUID toyId);

    List<InventoryItem> findByIdIn(Collection<UUID> ids);

    @Query(value = "SELECT * FROM inventory_item WHERE toy_id = :toyId AND status = :status LIMIT :count", nativeQuery = true)
    List<InventoryItem> findByToyIdAndStatus(@Param("toyId") UUID toyId, @Param("status") String status, @Param("count") int count);

    @Query("SELECT i.toyId as toyId, COUNT(i) as count FROM InventoryItem i WHERE i.status = :status GROUP BY i.toyId HAVING COUNT(i) > 0")
    List<ToyIdCountProjection> countAvailableToysGroupedByToyId(@Param("status") InventoryStatus status);

    boolean existsByToyId(UUID toyId);

    int countByToyIdAndStatus(UUID toyId, InventoryStatus status);
}
