package com.legend.library.dao;

import com.legend.library.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query(value = "SELECT * FROM member m WHERE m.first_name like %:filterText% or m.last_name like %:filterText% or m.id like %:filterText%", nativeQuery = true)
    List<Member> findMembersByFilterText(@Param("filterText") String filterText);
}
