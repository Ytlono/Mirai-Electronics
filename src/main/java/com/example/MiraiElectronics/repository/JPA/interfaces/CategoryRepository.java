package com.example.MiraiElectronics.repository.JPA.interfaces;

import com.example.MiraiElectronics.repository.JPA.realization.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
