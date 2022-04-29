package com.example.fitnessapp.repository;

import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.enumerations.Type;
import com.example.fitnessapp.model.enumerations.Weights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Long> {
    Exercise findByName(String name);
    Exercise findByType(Type type);
    Exercise findByDifficulty(int difficulty);
    Exercise findByLikes(int likes);
    Exercise findByDislikes(int dislikes);
    List<Exercise> findAllByNameLike(String name);
    List<Exercise> findAllByDifficulty(int difficulty);
    List<Exercise> findAllByNameLikeAndDifficulty(String name, int difficulty);
    List<Exercise> findAllByType(Type type);
    List<Exercise> findAllByNameLikeAndType(String name, Type type);
    List<Exercise> findAllByDifficultyAndType(int difficulty, Type type);
    List<Exercise> findAllByTypeAndDifficultyAndNameLike(Type type, int difficulty, String name);
    List<Exercise> findAllByWeights(Weights weights);
    List<Exercise> findAllByWeightsAndType(Weights weights, Type type);
    List<Exercise> findAllByWeightsAndDifficulty(Weights weights, int difficulty);
    List<Exercise> findAllByWeightsAndNameLike(Weights weights, String name);
    List<Exercise> findAllByWeightsAndNameLikeAndDifficulty(Weights weights, String name, int difficulty);
    List<Exercise> findAllByWeightsAndNameLikeAndType(Weights weights, String name, Type type);
    List<Exercise> findAllByWeightsAndDifficultyAndType(Weights weights, int difficulty, Type type);
    List<Exercise> findAllByWeightsAndDifficultyAndTypeAndNameLike(Weights weights, int difficulty, Type type, String name);
}
