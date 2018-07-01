package com.Dev.PortScanMVC.bootstrap;

import com.Dev.PortScanMVC.domain.Port;
import com.Dev.PortScanMVC.repository.PortRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Kevin Neag
 */

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {


    private PortRepository portRepository;

    public Bootstrap(PortRepository portRepository) {
        this.portRepository = portRepository;
    }



    @Override
    public void run(String... args) throws Exception {

        log.debug("Start time: " + LocalDateTime.now().toString());
        //Executor service with 20 threads
        final ExecutorService es = Executors.newFixedThreadPool(20);
        //home ip
        final String ip = "127.0.0.1";
        //timeout of 200ms
        final int timeout = 200;
        //Arraylist of future ports
        final List<Future<Port>> futures = new ArrayList<>();
        //Arraylist of future open ports
        final List<Future<Port>> openFutures = new ArrayList<>();
        log.debug("Starting Scan");
        //for each port
        for (int port = 1; port <=  65536; port++) {
            //futures add to see if port is open
            futures.add(portIsOpen(es, ip, port, timeout));
        }
        //terminate threads after 200ms
        es.awaitTermination(200L, TimeUnit.MILLISECONDS);
        int openPorts = 0;
        //for each future port
        for (final Future<Port> f : futures)
            //if the port is open
            if (f.get().isOpen()) {
                //save all open ports to repository
                portRepository.save(f.get());

                //print out the value of port
                log.debug(String.valueOf(f.get().getPortNum()));
                //
                openPorts++;

            }

        log.debug("There are " + openPorts + " open ports on host " + ip + " (probed with a timeout of "
                + timeout + "ms)");
        log.debug("End Time: " + LocalDateTime.now().toString());



        }


    //check if port is open and return
    public static Future<Port> portIsOpen(final ExecutorService es, final String ip, final int port,
                                          final int timeout) {
        return es.submit(new Callable<Port>() {
            @Override
            public Port call() {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), timeout);
                    socket.close();
                    return new Port(port,true);
                } catch (Exception ex) {
                    return new Port(port,false);
                }
            }
        });

    }
}
