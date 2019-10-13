package main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Repository
@Transactional
public interface RangeRepository extends JpaRepository<RangeData, Integer> {
    List<RangeData> findByNet(int net);
}