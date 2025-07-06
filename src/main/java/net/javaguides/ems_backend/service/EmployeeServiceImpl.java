package net.javaguides.ems_backend.service;

import net.javaguides.ems_backend.dto.EmployeeDto;
import net.javaguides.ems_backend.entity.Employee;
import net.javaguides.ems_backend.exception.ResourceNotFoundException;
import net.javaguides.ems_backend.mapper.EmployeeMapper;
import net.javaguides.ems_backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.javaguides.ems_backend.exception.ResourceNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{


    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto)
    {
        Employee employee = EmployeeMapper.maptoEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = EmployeeMapper.maptoEmployeeDto(savedEmployee);
        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee foundEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist with the given id: "+id));
        return EmployeeMapper.maptoEmployeeDto(foundEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee>  foundEmployees= employeeRepository.findAll();
        List<EmployeeDto> foundEmployeesDto = foundEmployees.stream().map((employee)-> EmployeeMapper.maptoEmployeeDto(employee)).collect(Collectors.toList());
        return foundEmployeesDto;
    }


}
