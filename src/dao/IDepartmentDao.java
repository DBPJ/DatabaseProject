package dao;

import entity.Department;

import java.util.List;

/**
 * Created by alex on 10/12/2016.
 */
public interface IDepartmentDao {
    public boolean addDepartment(Department department);
    public boolean deleteDepartment(String departmentName);
    public List<Department> queryDepartments();
}
