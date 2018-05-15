package nl.openvalue.workshops.kubernetes.userservice.data;

import nl.openvalue.workshops.kubernetes.userservice.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
}
