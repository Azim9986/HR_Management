package com.azim.demo.Repository;

import com.azim.demo.DTO.EmployeeSearchCriteria;
import com.azim.demo.Entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

public class EmployeeCustomRepoImpl implements EmployeeCustomRepo{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Employee> searchEmployee(EmployeeSearchCriteria criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> employee = query.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getDepartment() != null && !criteria.getDepartment().isEmpty()) {
            predicates.add(cb.equal(employee.get("department"), criteria.getDepartment()));
        }

        if (criteria.getPosition() != null && !criteria.getPosition().isEmpty()) {
            predicates.add(cb.equal(employee.get("position"), criteria.getPosition()));
        }

        if (criteria.getEmployeeStatus() != null) {
            predicates.add(cb.equal(employee.get("employeeStatus"), criteria.getEmployeeStatus()));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
    }

