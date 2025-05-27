package com.helloevent.backend.service;


import com.helloevent.backend.model.Category;
import com.helloevent.backend.repository.CategoryRepository;
import com.helloevent.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository, JwtService jwtService) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow();    }
}
