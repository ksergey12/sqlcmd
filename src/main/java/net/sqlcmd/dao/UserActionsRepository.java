package net.sqlcmd.dao;

import net.sqlcmd.dao.entity.UserAction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by User on 19.04.2017.
 */
public interface UserActionsRepository extends CrudRepository<UserAction, Integer> {

    List<UserAction> findByUserName(String user);
}
