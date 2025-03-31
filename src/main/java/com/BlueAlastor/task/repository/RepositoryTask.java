package com.BlueAlastor.task.repository;

import com.BlueAlastor.task.entity.EntityTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryTask extends JpaRepository<EntityTask, Long> {
}