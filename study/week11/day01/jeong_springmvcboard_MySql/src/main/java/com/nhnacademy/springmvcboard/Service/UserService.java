package com.nhnacademy.springmvcboard.Service;

import com.nhnacademy.springmvcboard.common.CommonPropertiesConfig;
import com.nhnacademy.springmvcboard.common.pagination.Page;
import com.nhnacademy.springmvcboard.domain.User;
import com.nhnacademy.springmvcboard.exception.DuplicateUserIdException;
import com.nhnacademy.springmvcboard.exception.UserNotFoundException;
import com.nhnacademy.springmvcboard.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserService{

    private final UserRepository userRepository;
    private final CommonPropertiesConfig commonPropertiesConfig;

    public UserService(UserRepository userRepository, CommonPropertiesConfig commonPropertiesConfig) {
        this.userRepository = userRepository;
        this.commonPropertiesConfig = commonPropertiesConfig;
    }

    public void register(User user) {
//        MultipartFile file = user.getProfileFile();
//
//        if( file.isEmpty()) {
//            try {
//                file.transferTo(Paths.get(commonPropertiesConfig.getUploadPath() + File.separator + file.getOriginalFilename()));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
        if (userRepository.existById(user.getUserId())) {
            throw new DuplicateUserIdException(user.getUserId());
        }

        userRepository.add(user);
    }

    public void modify(User user) {
        userRepository.modify(user);
    }

    public void removeById(String id) {
        userRepository.remove(id);
    }

    public User getUserById(String id) {
        User user = userRepository.getUserById(id);
        if(Objects.isNull(user)) {
            throw new UserNotFoundException(id);
        }
        return userRepository.getUserById(id);
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public Page<User> getUserList(int page, int size) {
        return userRepository.getPagedList(page, size);
    }

    public String getProfileImagePath(String id){
        User user = getUserById(id);
        if(Objects.nonNull(user)){
            if(StringUtils.hasText(user.getProfileFileName())){
                return  user.getProfileFileName();
            }
        }
        return "/resources/no-image.png";
    }
}
