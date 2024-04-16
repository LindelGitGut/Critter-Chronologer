package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;


    //PetService is needed to provide the Dog Object obtained by id when update Customer;
    @Autowired
    PetService petService;


    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = convertFromCustomerDto(customerDTO);
        customer = customerService.save(customer);
        return convertToCustomerDto(customer);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id){
        customerService.removeCustomerByID(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> allCustomerDto = new ArrayList<>();
        List<Customer> allCustomer = customerService.getAll();

        if (!allCustomer.isEmpty()){
            for (Customer customer: allCustomer
                 ) {
                allCustomerDto.add(convertToCustomerDto(customer));
            }
        }
        return allCustomerDto;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        return convertToCustomerDto(customerService.getCustomerByPetId(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertFromEmployeeDto(employeeDTO);
        return convertToEmployeeDto(employeeService.save(employee));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return convertToEmployeeDto(employeeService.findEmployeeById(employeeId));
    }


    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailabilityByID(employeeId, daysAvailable);
    }


    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<EmployeeDTO> availableEmployees = new ArrayList<>();
        for (Employee emploeyee:employeeService.getAvailableEmployeesForService(employeeDTO.getSkills(), employeeDTO.getDate())
             ) {
            availableEmployees.add(convertToEmployeeDto(emploeyee));
        }
        return availableEmployees;
    }


    //DTO Converter Methods
    private Customer convertFromCustomerDto(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        //if ids for Pets are Provided, find Pet From DB and include into Customer
        List<Long> petids = new ArrayList<>();
        petids = customerDTO.getPetIds();
        if (petids != null){
            List<Pet> pets = petService.findAllPetsById(customerDTO.getPetIds());
            customer.setPets(pets);}
        return customer;
    }

    private CustomerDTO convertToCustomerDto(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        //CustomerDTO customerDTO = modelmapper.map(customer, CustomerDTO.class);
        //check if Pets are provided, if so, obtain only ids of Pet
        if (customer.getPets() != null) {
            List<Long> petIds = new ArrayList<>();
            for (Pet pet : customer.getPets()
            ) {
                petIds.add(pet.getId());
            }
            customerDTO.setPetIds(petIds);
        }
        return customerDTO;
    }

    private EmployeeDTO convertToEmployeeDto(Employee employee){
        EmployeeDTO employeeDTO= new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    private Employee convertFromEmployeeDto(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }





}
