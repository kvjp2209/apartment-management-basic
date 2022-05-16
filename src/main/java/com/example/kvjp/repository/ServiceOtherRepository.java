package com.example.kvjp.repository;

import com.example.kvjp.model.ServiceOther;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ServiceOtherRepository extends JpaRepository<ServiceOther, Integer> {
    Set<ServiceOther> findAllByIdIn(Set<Integer> ids);
}
