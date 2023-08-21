/**
 * 
 */
package com.wibmo.test;

/**
 * 
 */
public class dump {
    // refer the instance here 

    EmployeeService empBusinessLogic=null;

    Employee employee=null;


    // JUNIT life cycle method
// before the test case


    @Before
    public void setUp() throws Exception {

        // before
        // craete the object before execution of test
        // case
        System.out.println("before");

        empBusinessLogic = new EmployeeServiceImpl();
        employee = new Employee();
        }



    // after the test case
    @After
    public void tearDown() throws Exception {
        // after
        System.out.println("after");
    }



    @Test
    public void test() {
        fail("Not yet implemented");
    }


    // write two test cases here 

    // test to check yearly salary
       @Test
       public void testCalculateYearlySalary() {
          employee.setName("Amit");
          employee.setAge(25);
          employee.setMonthlySalary(8000);

          double salary = empBusinessLogic.calculateYearlySalary(employee);
          assertEquals(96000, salary, 0.0);
       }

       //test to check appraisal
       @Test
       public void testCalculateAppriasal() {
          employee.setName("Amit");
          employee.setAge(25);
          employee.setMonthlySalary(8000);

          double appraisal = empBusinessLogic.calculateAppraisal(employee);
          assertEquals(500, appraisal, 0.0);
       }

}
