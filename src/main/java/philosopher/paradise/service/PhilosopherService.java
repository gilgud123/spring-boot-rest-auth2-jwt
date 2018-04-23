package philosopher.paradise.service;

import philosopher.paradise.entity.Philosopher;

import java.util.List;
import java.util.Set;


public interface PhilosopherService {

    List<Philosopher> getPhilosophers();

    Philosopher findById(Long id);

    Philosopher findByName(String name);

    Set<Philosopher> getPhilosopherByCategory(String category);

    void deleteById(Long id);
}
