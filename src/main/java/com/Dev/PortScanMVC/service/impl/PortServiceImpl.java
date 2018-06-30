package com.Dev.PortScanMVC.service.impl;

import com.Dev.PortScanMVC.domain.Port;
import com.Dev.PortScanMVC.exception.NotFoundException;
import com.Dev.PortScanMVC.repository.PortRepository;
import com.Dev.PortScanMVC.service.PortService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Kevin Neag
 */
@Slf4j
@Service
public class PortServiceImpl implements PortService {

    private final PortRepository portRepository;

    public PortServiceImpl(PortRepository portRepository) {
        this.portRepository = portRepository;
    }

    @Override
    public Set<Port> getPorts() {

        Set<Port> portSet = new HashSet<>();
        portRepository.findAll().iterator().forEachRemaining(portSet::add);
        return portSet;
    }

    @Override
    public Port findById(Long l) {

        Optional<Port> recipeOptional = portRepository.findById(l);

        if (!recipeOptional.isPresent()) {


            throw new NotFoundException("Port Not Found. For ID value " + l.toString());

        }

        return recipeOptional.get();
    }


    @Override
    public void deleteById(Long idToDelete) {
        portRepository.deleteById(idToDelete);
    }
}
