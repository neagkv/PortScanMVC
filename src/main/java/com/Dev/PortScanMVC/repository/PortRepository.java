package com.Dev.PortScanMVC.repository;

import com.Dev.PortScanMVC.domain.Port;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kevin Neag
 */
public interface PortRepository extends JpaRepository<Port,Long> {
}
