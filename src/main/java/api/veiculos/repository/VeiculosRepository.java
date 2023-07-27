package api.veiculos.repository;

import api.veiculos.core.entity.VeiculosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculosRepository extends JpaRepository<VeiculosEntity, Long> {
}
