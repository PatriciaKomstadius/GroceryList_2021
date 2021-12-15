package se.iths.grocerylist.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import se.iths.grocerylist.entity.*;
import se.iths.grocerylist.repository.*;


    @Component
    public class DataSampleGenerator implements ApplicationRunner {

        private UserRepository userRepository;
        private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        private CompanyRepository companyRepository;
        private DepartmentRepository departmentRepository;
        private GroceryListRepository groceryListRepository;
        private LayoutRepository layoutRepository;
        private ProductRepository productRepository;
        private final RoleRepository roleRepository;


        @Autowired
        public DataSampleGenerator (UserRepository userRepository,CompanyRepository companyRepository, DepartmentRepository departmentRepository, GroceryListRepository groceryListRepository, LayoutRepository layoutRepository, ProductRepository productRepository, RoleRepository roleRepository) {
            this.userRepository = userRepository;
            this.companyRepository = companyRepository;
            this.departmentRepository = departmentRepository;
            this.groceryListRepository = groceryListRepository;
            this.layoutRepository = layoutRepository;
            this.productRepository = productRepository;
            this.roleRepository = roleRepository;
        }

        public void run(ApplicationArguments args) {

            roleRepository.save(new RoleEntity("ROLE_ADMIN"));
            roleRepository.save(new RoleEntity("ROLE_EMPLOYEE"));
            roleRepository.save(new RoleEntity("ROLE_CUSTOMER"));


            userRepository.save(new UserEntity("admin", "email", "admin", "b", passwordEncoder.encode("123")));
            companyRepository.save(new CompanyEntity("ICA", new CompanyInfoEntity("1", "address", "zipcode", "city")));
            layoutRepository.save(new LayoutEntity("Maxi"));
            layoutRepository.save(new LayoutEntity("Nära"));
            layoutRepository.save(new LayoutEntity("Supermarket"));
           departmentRepository.save(new DepartmentEntity("Mejeri"));
            departmentRepository.save(new DepartmentEntity("Frukt och Grönt"));
            departmentRepository.save(new DepartmentEntity("Frys"));
            departmentRepository.save(new DepartmentEntity("Chark"));
            departmentRepository.save(new DepartmentEntity("Bageri"));
            departmentRepository.save(new DepartmentEntity("Godis"));
            ProductEntity product = new ProductEntity("Arla Mjölk", 15.00, "Mjölkprodukter", 100);

            DepartmentEntity findDepartment = departmentRepository.findByDepartmentName("Mejeri");
           findDepartment.addProduct(product);

           departmentRepository.save(findDepartment);
              /*
            DepartmentEntity mejeri = new DepartmentEntity("mejeri");
            ProductEntity mjölk = new ProductEntity("Arla Mjölk", 15.00, "Mjölkprodukter", 100);
            mejeri.addProduct(mjölk);
            departmentRepository.save(mejeri);

               */

            UserEntity findUser = userRepository.findByUsername("admin");
            RoleEntity roleToAdd = roleRepository.findByRoleName("ROLE_ADMIN");

            System.out.println(roleToAdd.getRoleName());

            findUser.setRole(roleToAdd);

            userRepository.save(findUser);


        }

    }

