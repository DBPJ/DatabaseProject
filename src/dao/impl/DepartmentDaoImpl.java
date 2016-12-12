package dao.impl;

import dao.IDepartmentDao;
import entity.Department;

import java.util.List;

/**
 * Created by Jindiwei on 2016/12/13.
 */
public class DepartmentDaoImpl implements IDepartmentDao {
    @Override
    public boolean addDepartment(Department department) {
        return false;
    }

    @Override
    public boolean deleteDepartment(String departmentName) {
        return false;
    }

    @Override
    public List<Department> queryDepartments() {
        return null;
    }
}
