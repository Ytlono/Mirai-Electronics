package com.example.MiraiElectronics.service;

import com.example.MiraiElectronics.Mapper.UserMapper;
import com.example.MiraiElectronics.dto.UpdateUserDataDTO;
import com.example.MiraiElectronics.repository.JPA.interfaces.UserRepository;
import com.example.MiraiElectronics.repository.JPA.realization.User;
import com.example.MiraiElectronics.service.Generic.GenericEntityService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;

@Service
@Primary
public class UserService extends GenericEntityService<User,Long>{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    @Transactional
    public User updateUser(User currentUser, UpdateUserDataDTO updateUserDataDTO) {
        userMapper.updateUserFromDto(updateUserDataDTO, currentUser);
        return userRepository.save(currentUser);
    }

    public boolean isUserExist(String email, String username) {
        return userRepository.existsByEmailOrUsername(email, username);
    }
}
