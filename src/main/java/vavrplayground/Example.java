/*
Based on https://github.com/Opalo/stackoverflow/blob/master/65254661/src/main/java/Example.java
To help get matches working, as the vavr examples were incomplete and did not compile as is
 */
package vavrplayground;

import static io.vavr.API.*;

public class Example {

    public static void main(String[] args) {
        Employee person = new Employee("Carl", "89696D8");

        String result = Match(person).of(
                Case(MyPatterns.$Employee($("Carl"), $()), (name, id) -> name + " " + id),
                Case($(), () -> "notfound")
        );
        System.out.println(result);
    }


    public static class Employee {
        private String name;
        private String id;

        public Employee(String name, String id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }
    }

}