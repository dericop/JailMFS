package com.jail.mfs.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jail.mfs.models.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Integer>{
	
	List<Visitor> findByIdCard(int idcard);
	List<Visitor> findByNameContaining(String name);
	List<Visitor> findByLastNameContaining(String lastName);
	
	@Query(value="select * from (select vts.*, coalesce(vss.ct, 0) count from visitor vts join (select v.visitor_id, count(*) ct from visit v where v.prisoner_id IN (select p.id from prisoner p where p.cell_id in (select id from cell where block_id = :bid)) and v.date BETWEEN :sdt and :edt group by v.visitor_id) vss on vts.id = vss.visitor_id) x order by x.count desc", nativeQuery = true)
	List<Visitor> getVisitorsOrderedByVisitsToBlockBetweenDates(@Param("bid") int bid, @Param("sdt") Date sdt, @Param("edt") Date edt);
	
	@Query(value="select * from (select vts.*, coalesce(vss.ct, 0) count from visitor vts join (select v.visitor_id, count(*) ct from visit v where v.date BETWEEN :sdt and :edt group by v.visitor_id) vss on vts.id = vss.visitor_id) x order by x.count desc", nativeQuery = true)
	List<Visitor> getVisitorsOrderedByVisitsBetweenDates(@Param("sdt") Date sdt, @Param("edt") Date edt);
	
}
