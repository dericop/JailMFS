package com.jail.mfs.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jail.mfs.models.Cell;

public interface CellRepository extends JpaRepository<Cell, Integer>{

	  @Query(value = "select x.id, x.capacity, x.block_id from (select c.*, coalesce(cast(p.occ as decimal)/cast(c.capacity as decimal), 0) rate from cell c left outer join (select cell_id, count(*) occ from prisoner group by cell_id) p on p.cell_id=c.id) x where x.rate < 1 order by x.rate asc", nativeQuery = true)
	  List<Cell> getCellsSortedByAvailability();
	
}
