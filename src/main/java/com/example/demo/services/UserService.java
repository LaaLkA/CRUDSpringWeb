package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    /**
     * Объект репозитория для работы с БД
     */
    private final UserRepository userRepository;

    /**
     * Конструктор класса.
     * @param userRepository репозиторий пользователей
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Получение списка всех пользователей
     * @return список пользователей
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Сохранение пользователей
     * @param user объект пользователя для сохранения
     * @return сохранённый объект пользователя
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Удаление пользователя по идентификатору
     * @param id идентификатор для удаления
     */
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    /**
     * Обновление пользователя
     * @param user объект обновлённого пользователя
     * @return объект, который был обновлён
     */
    public User updateUser(User user) {
        return userRepository.update(user);
    }

    /**
     * Поиск по идентификатору
     * @param id идентификатор пользователя, которого ищем
     * @return найденный пользователь
     */
    public User findById(int id) {
        return userRepository.findById(id);
    }

}
