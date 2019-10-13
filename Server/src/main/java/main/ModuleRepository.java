package main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Repository
@Transactional
public interface ModuleRepository extends JpaRepository<ModuleData, ModulePK> {
    List<ModuleData> findByPk_Net(int net);

    List<ModuleData> findByPk_NetAndPk_Loc(int net, int loc);
}