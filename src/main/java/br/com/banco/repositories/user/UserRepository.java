package br.com.banco.repositories.user;

import br.com.banco.models.UserModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModal, Long> {
}
