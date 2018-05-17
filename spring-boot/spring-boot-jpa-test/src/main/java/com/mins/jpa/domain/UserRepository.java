package com.mins.jpa.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/5/17
 * No struggle, talent how to match the willfulness.
 * Description:
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
