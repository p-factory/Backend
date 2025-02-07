package com.api.pactory.Member.repository;

import com.api.pactory.Member.enums.Authority;
import com.api.pactory.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByAuthority(Authority authority);
}
