package com.elearning.config;

import com.elearning.entity.Category;
import com.elearning.entity.User;
import com.elearning.repository.CategoryRepository;
import com.elearning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Load Categories if none exist
        if (categoryRepository.count() == 0) {
            Category programming = new Category();
            programming.setName("Programming");
            programming.setSlug("programming");
            programming.setIcon("💻");
            categoryRepository.save(programming);

            Category webDev = new Category();
            webDev.setName("Web Development");
            webDev.setSlug("web-development");
            webDev.setIcon("🌐");
            categoryRepository.save(webDev);

            Category dataScience = new Category();
            dataScience.setName("Data Science");
            dataScience.setSlug("data-science");
            dataScience.setIcon("📊");
            categoryRepository.save(dataScience);

            // Add exam categories
            Category jee = new Category();
            jee.setName("JEE Preparation");
            jee.setSlug("jee");
            jee.setIcon("⚡");
            categoryRepository.save(jee);

            Category neet = new Category();
            neet.setName("NEET Preparation");
            neet.setSlug("neet");
            neet.setIcon("🩺");
            categoryRepository.save(neet);

            Category upsc = new Category();
            upsc.setName("UPSC Preparation");
            upsc.setSlug("upsc");
            upsc.setIcon("🏛️");
            categoryRepository.save(upsc);

            Category dsa = new Category();
            dsa.setName("Data Structures & Algorithms");
            dsa.setSlug("dsa");
            dsa.setIcon("💻");
            categoryRepository.save(dsa);

            Category mhtcet = new Category();
            mhtcet.setName("MHT CET Preparation");
            mhtcet.setSlug("mht-cet");
            mhtcet.setIcon("📝");
            categoryRepository.save(mhtcet);

            System.out.println("✅ Categories loaded successfully!");
        }

        // Create a demo student user if none exists
        if (userRepository.count() == 0) {
            // Create a demo student
            User student = new User();
            student.setFullName("Demo Student");
            student.setEmail("student@elearning.com");
            student.setPasswordHash(passwordEncoder.encode("student123"));
            student.setRole("student");
            student.setIsActive(true);
            userRepository.save(student);

            // Create another student for testing
            User testStudent = new User();
            testStudent.setFullName("Test Student");
            testStudent.setEmail("test@elearning.com");
            testStudent.setPasswordHash(passwordEncoder.encode("test123"));
            testStudent.setRole("student");
            testStudent.setIsActive(true);
            userRepository.save(testStudent);

            System.out.println("✅ Default student users created!");
            System.out.println("Student 1: student@elearning.com / student123");
            System.out.println("Student 2: test@elearning.com / test123");
            System.out.println("⚠️ Note: All users are created as students only.");
        }
    }
}