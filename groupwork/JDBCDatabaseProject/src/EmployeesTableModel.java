import java.util.List;

import javax.swing.table.AbstractTableModel;

class EmployeesTableModel extends AbstractTableModel {
    
    private static final int LAST_NAME_COL = 0;
    private static final int FIRST_NAME_COL = 1;
    private static final int EMAIL_COL = 2;
    private static final int SALARY_COL = 3;
    
    private String[] columnNames = { "Last Name", "First Name", "Email", "Salary" };
    
    private List<Employees> employees;
    
    public EmployeesTableModel(List<Employees> theEmployees)
    {
        employees = theEmployees;
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return employees.size();
    }
    
    @Override
    public String getColumnName(int col) {
        // TODO Auto-generated method stub
        return columnNames[col];
    }


    @Override
    public Object getValueAt(int row, int col) {
        // TODO Auto-generated method stub
        Employees tempEmployee = employees.get(row);
        
        switch(col){
        case LAST_NAME_COL:
            return tempEmployee.getLastName();
        case FIRST_NAME_COL:
            return tempEmployee.getFirstName();
        case EMAIL_COL:
            return tempEmployee.getEmail();
        case SALARY_COL:
            return tempEmployee.getSalary();
        default:
            return tempEmployee.getLastName();
        }
    }
    
    @Override
    public Class getColumnClass(int c) {
        // TODO Auto-generated method stub
        return getValueAt(0, c).getClass();
    }
    
    

}