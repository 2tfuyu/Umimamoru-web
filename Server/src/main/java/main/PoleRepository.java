package main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Repository
@Transactional
public interface PoleRepository extends JpaRepository<PoleData, ModulePK> {
    List<PoleData> findByPk_Net(int net);

    List<PoleData> findByPk_NetAndPk_Loc(int net, int loc);
}