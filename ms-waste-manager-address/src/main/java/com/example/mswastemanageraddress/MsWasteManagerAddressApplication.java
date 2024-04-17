package com.example.mswastemanageraddress;

import com.example.mswastemanageraddress.model.entity.WasteManagerAddressEntity;
import com.example.mswastemanageraddress.repository.WasteManagerAddressJPARepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class MsWasteManagerAddressApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MsWasteManagerAddressApplication.class, args);
    }

    @Autowired
    WasteManagerAddressJPARepository wr;

    @Override
    public void run(String... args) throws Exception {

        WasteManagerAddressEntity w = new WasteManagerAddressEntity();
        w.setAddress("aedghfj");
        w.setIsEnabled(true);
        w.setCreatedDate(LocalDate.now());
        w.setLastModifiedDate(LocalDate.now());
        wr.save(w);

    }
}
