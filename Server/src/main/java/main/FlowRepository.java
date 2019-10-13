package main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@org.springframework.stereotype.Repository
@Transactional
public interface FlowRepository extends JpaRepository<FlowData, FlowPK> {
    List<FlowData> findByPk_Net(int net);
}