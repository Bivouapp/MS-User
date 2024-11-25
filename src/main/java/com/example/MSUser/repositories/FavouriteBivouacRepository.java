package com.example.MSUser.repositories;

import com.example.MSUser.models.FavouriteBivouacId;
import com.example.MSUser.models.FavouriteBivouacs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteBivouacRepository extends JpaRepository<FavouriteBivouacs, FavouriteBivouacId> {

    List<FavouriteBivouacs> findAllByUser_id(Long userId);
}
