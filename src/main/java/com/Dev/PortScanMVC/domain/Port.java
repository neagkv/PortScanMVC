package com.Dev.PortScanMVC.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Kevin Neag
 */

@Data
@Entity
public class Port {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int portNum;
    private boolean isOpen;

    public Port() {
    }

    public Port(Long id, int portNum, boolean isOpen) {
        this.id = id;
        this.portNum = portNum;
        this.isOpen = isOpen;
    }

    public Port(int portNum, boolean isOpen) {
        this.portNum = portNum;
        this.isOpen = isOpen;
    }
}
