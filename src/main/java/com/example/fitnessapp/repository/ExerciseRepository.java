package com.example.fitnessapp.repository;

import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.enumerations.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Long> {
    public Exercise findByName(String name);
    public Exercise findByType(Type type);
    public Exercise findByDifficulty(int difficulty);
    public Exercise findByLikes(int likes);
    public Exercise findByDislikes(int dislikes);
}
