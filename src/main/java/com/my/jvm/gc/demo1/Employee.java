package com.my.jvm.gc.demo1;

public class Employee {
    private int ID;
    private String name;
    private int age;
    private static int nextId=1;
    //它是静态的，因为它在所有对象之间保持通用并由所有对象共享
    public Employee(String name,int age)
    {
        this.name = name;
        this.age = age;
        this.ID = nextId++;
    }
    public void show()
    {
        System.out.println
                ("Id="+ID+"\nName="+name+"\nAge="+age);
    }
    public void showNextId()
    {
        System.out.println
                ("Next employee id will be="+nextId);
    }
    @Override
    protected void finalize()
    {
        --nextId;
        //在这种情况下，gc 会为 2 个对象调用 finalize() 两次。
    }
}
class UseEmployee
{
    public static void main(String []args)
    {
        Employee E=new Employee("GFG1",33);
        Employee F=new Employee("GFG2",45);
        Employee G=new Employee("GFG3",25);
        E.show();
        F.show();
        G.show();
        E.showNextId();
        F.showNextId();
        G.showNextId();

        { //这是保留所有实习生的子块。
            Employee X=new Employee("GFG4",23);
            Employee Y=new Employee("GFG5",21);
            X.show();
            Y.show();
            X.showNextId();
            Y.showNextId();
            X = Y = null;
            System.gc();
            System.runFinalization();
        }
        //这个大括号之后，X 和 Y 将被移除。因此现在它应该显示 nextId 为 4。
        E.showNextId();//这一行的输出应该是 4，但它会给出 6 作为输出。
    }
}
