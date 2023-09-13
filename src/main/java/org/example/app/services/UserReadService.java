package org.example.app.services;

import org.example.app.database.DBCheck;
import org.example.app.entities.User;
import org.example.app.exceptions.DBException;
import org.example.app.repositories.UserReadRepository;
import org.example.app.utils.Constants;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserReadService {

    UserReadRepository repository;

    public UserReadService(UserReadRepository repository) {
        this.repository = repository;
    }

    public String readUsers() {
        if (DBCheck.isDBExists()) {
            try {
                throw new DBException(Constants.DB_ABSENT_MSG);
            } catch (DBException e) {
                return e.getMessage();
            }
        }

        List<User> users = repository.readUsers();

        if (users != null) {
            if (!users.isEmpty()) {
                AtomicInteger count = new AtomicInteger(0);
                StringBuilder stringBuilder = new StringBuilder();
                users.forEach((user) ->
                        stringBuilder.append(count.incrementAndGet())
                                .append(") id - ")
                                .append(user.getId())
                                .append(", ")
                                .append(user.getName())
                                .append(", ")
                                .append(user.getPhone())
                                .append(", ")
                                .append(user.getEmail())
                                .append("\n")
                );
                return stringBuilder.toString();
            } else {
                return Constants.DATA_ABSENT_MSG;
            }
        } else {
            return Constants.DATA_ABSENT_MSG;
        }
    }

}
