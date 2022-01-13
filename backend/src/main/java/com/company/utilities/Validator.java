package com.company.utilities;

import com.company.Entities.User;
import com.company.Repositories.userRepository;
import com.company.Services.DTOConverter;

public class Validator {

    public Object loginValidation(String userName, String passWord, userRepository userRepository) {
        User foundUser = userRepository.getUserEntityByUsername(userName);
        if (Encrypter.hash(passWord).equals(foundUser.getPassword())) {
            return new DTOConverter().turnUserIntoDto(foundUser);
        }
        return null;
    }
}
