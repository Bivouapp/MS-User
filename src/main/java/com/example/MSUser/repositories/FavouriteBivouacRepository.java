package com.example.MSUser.repositories;

import com.example.MSUser.models.FavouriteBivouacId;
import com.example.MSUser.models.FavouriteBivouacs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteBivouacRepository extends JpaRepository<FavouriteBivouacs, FavouriteBivouacId> {

    @Query("SELECT f FROM favourite_bivouacs f WHERE f.user.user_id = :userId")
    public List<FavouriteBivouacs> findAllByUserId(@Param("userId") Long userId);


}
