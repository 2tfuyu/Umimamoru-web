package main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface NetRepository extends JpaRepository<NetData, ModulePK> {
    List<NetData> findByNet(int net);
    List<NetData> findByPref(String pref);
    List<NetData> findByBeach(String beach);
}