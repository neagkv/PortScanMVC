package com.Dev.PortScanMVC.service;

import com.Dev.PortScanMVC.domain.Port;

import java.util.Set;

/**
 * @author Kevin Neag
 */
public interface PortService {

    Set<Port> getPorts();

    Port findById(Long l);

    void deleteById(Long idToDelete);
}
