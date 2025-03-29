package org.silli.sillibackend.repositories;

import org.springframework.data.jdbc.repository.query.Query;

public interface EntityRepository {
    int findOwner(int entityId);

}
