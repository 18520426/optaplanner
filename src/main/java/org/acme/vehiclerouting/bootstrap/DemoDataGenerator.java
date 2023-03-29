package org.acme.vehiclerouting.bootstrap;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import io.quarkus.runtime.StartupEvent;
import org.acme.vehiclerouting.domain.Location;
import org.acme.vehiclerouting.domain.VehicleRoutingSolution;
import org.acme.vehiclerouting.persistence.VehicleRoutingSolutionRepository;

@ApplicationScoped
public class DemoDataGenerator {

    private final VehicleRoutingSolutionRepository repository;

    public DemoDataGenerator(VehicleRoutingSolutionRepository repository) {
        this.repository = repository;
    }

    public void generateDemoData(@Observes StartupEvent startupEvent) {
        VehicleRoutingSolution problem = DemoDataBuilder.builder()
                .setMinDemand(1)
                .setMaxDemand(2)
                .setVehicleCapacity(60)
                .setCustomerCount(800)
                .setVehicleCount(32)
                .setDepotCount(4)
                .setSouthWestCorner(new Location(0L, 34.951466, 137.177210))
                .setNorthEastCorner(new Location(0L, 36.809291, 140.290195))
                .build();

        repository.update(problem);
    }
}
