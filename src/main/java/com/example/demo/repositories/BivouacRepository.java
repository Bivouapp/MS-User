package com.example.demo.repositories;

import com.example.demo.models.Bivouac;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BivouacRepository extends JpaRepository<Bivouac,Long> {
}
