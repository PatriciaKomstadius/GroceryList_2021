package se.iths.grocerylist.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import se.iths.grocerylist.entity.*;
import se.iths.grocerylist.repository.*;

import javax.transaction.Transactional;
import java.util.Optional;


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
    public DataSampleGenerator(UserRepository userRepository, CompanyRepository companyRepository, DepartmentRepository departmentRepository, GroceryListRepository groceryListRepository, LayoutRepository layoutRepository, ProductRepository productRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.departmentRepository = departmentRepository;
        this.groceryListRepository = groceryListRepository;
        this.layoutRepository = layoutRepository;
        this.productRepository = productRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void run(ApplicationArguments args) {

        roleRepository.save(new RoleEntity("ROLE_ADMIN"));
        roleRepository.save(new RoleEntity("ROLE_EMPLOYEE"));
        roleRepository.save(new RoleEntity("ROLE_CUSTOMER"));


        userRepository.save(new UserEntity("admin", "email", "admin", "b", passwordEncoder.encode("123")));
        userRepository.save(new UserEntity("customer", "email", "customer", "b", passwordEncoder.encode("123")));
        userRepository.save(new UserEntity("employee", "email", "employee", "b", passwordEncoder.encode("123")));
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
        ProductEntity arlamjolk = new ProductEntity("Arla Mjölk", 15.00, "Mjölkprodukter", 100);
        ProductEntity apelsin = new ProductEntity("Apelsin", 10.00, "Frukt", 100);
        ProductEntity lax = new ProductEntity("Lax", 150.00, "Fisk", 30);
        ProductEntity banan = new ProductEntity("Banan", 15.00, "Frukt", 70);
        ProductEntity salami = new ProductEntity("Salami", 45.00, "Chark", 10);
        ProductEntity brod = new ProductEntity("Brod", 32.00, "Bageri", 500);
        ProductEntity benAndJerry = new ProductEntity("BenAndJerry", 45.00, "Godis", 50);
        GroceryListEntity groceryList1 = new GroceryListEntity("Frukost");

        productRepository.save(brod);
        productRepository.save(benAndJerry);


        DepartmentEntity mejeri = departmentRepository.findByDepartmentName("Mejeri");
        mejeri.addProduct(arlamjolk);

        DepartmentEntity fruktOchGront = departmentRepository.findByDepartmentName("Frukt och Grönt");
        fruktOchGront.addProduct(apelsin);
        fruktOchGront.addProduct(banan);

        DepartmentEntity frys = departmentRepository.findByDepartmentName("Frys");
        frys.addProduct(lax);

        DepartmentEntity chark = departmentRepository.findByDepartmentName("Chark");
        chark.addProduct(salami);

        LayoutEntity maxi = layoutRepository.findByType("Maxi");
        maxi.addDepartment(mejeri);
        maxi.addDepartment(fruktOchGront);
        maxi.addDepartment(frys);
        maxi.addDepartment(chark);
        layoutRepository.save(maxi);

        LayoutEntity nara = layoutRepository.findByType("Nära");
        nara.addDepartment(mejeri);
        nara.addDepartment(fruktOchGront);
        layoutRepository.save(nara);


        Optional<CompanyEntity> ica = companyRepository.findById(7L);
        ica.get().addLayout(maxi);
        ica.get().addLayout(nara);

        UserEntity adm = userRepository.findByUsername("admin");
        RoleEntity roleToAdd = roleRepository.findByRoleName("ROLE_ADMIN");
        adm.setRole(roleToAdd);

        UserEntity cust = userRepository.findByUsername("customer");
        roleToAdd = roleRepository.findByRoleName("ROLE_CUSTOMER");
        cust.setRole(roleToAdd);

        UserEntity emp = userRepository.findByUsername("employee");
        roleToAdd = roleRepository.findByRoleName("ROLE_EMPLOYEE");
        emp.setRole(roleToAdd);

        userRepository.save(adm);
        userRepository.save(emp);
        userRepository.save(cust);

        groceryList1.addProduct(arlamjolk);
        groceryList1.addProduct(apelsin);
        groceryList1.addProduct(lax);
        groceryList1.addProduct(banan);

        groceryList1.addUser(adm);
        groceryList1.addUser(cust);
        groceryList1.addUser(emp);

        groceryListRepository.save(groceryList1);

    }

}

