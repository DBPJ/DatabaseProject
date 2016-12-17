package manager;

import dao.impl.StaffDaoImpl;
import entity.Gender;
import entity.Staff;
import entity.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 17/12/2016.
 */
public class StaffManagerTest {

    public void testAddStaff(){

    }


    @Test
    public void addStaffInfos(){
        String filepath = "test/data/初始员工信息.xls";
        StaffDaoImpl staffDao = new StaffDaoImpl();
        List<User> users = new ArrayList<>();
        InputStream inp = null;
        try {
            inp = new FileInputStream(filepath);
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);
            for (int i = sheet.getFirstRowNum()+1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Staff staff = new Staff();
                staff.setNumber(row.getCell(0).getStringCellValue());
                staff.setName(row.getCell(1).getStringCellValue());
                staff.setGender(getGender(row.getCell(2).getStringCellValue()));
                staff.setDepartmentName(row.getCell(3).getStringCellValue());
                staff.setWorkAge((int)(row.getCell(4).getNumericCellValue()));
                staff.setLocation(row.getCell(5).getStringCellValue());
                staff.setSalary(Double.valueOf(row.getCell(6).getNumericCellValue()));
                staff.setAdditionRate(Double.valueOf(row.getCell(7).getNumericCellValue()));
                staffDao.addStaff(null, staff);
            }
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    private Enum getGender(String gender){
        if(gender.equals("男")){
            return Gender.MALE;
        }
        else{
            return Gender.FEMALE;
        }
    }
}
