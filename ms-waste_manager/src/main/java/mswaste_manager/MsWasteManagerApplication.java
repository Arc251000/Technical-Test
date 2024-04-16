package mswaste_manager;

import mswaste_manager.model.entity.WasteManagerEntity;
import mswaste_manager.repository.WasteManagerJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class MsWasteManagerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MsWasteManagerApplication.class, args);
    }

    @Autowired
    WasteManagerJPARepository wr;


    @Override
    public void run(String... args) throws Exception {
        WasteManagerEntity w = new WasteManagerEntity();
        w.setNif("aedghfj");
        w.setName("test");
        w.setCreatedDate(LocalDate.now());
        w.setLastModifiedDate(LocalDate.now());
        wr.save(w);
    }
}
